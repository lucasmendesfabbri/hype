package br.com.hype_mc.pvp.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.league.League;
import br.com.hype_mc.pvp.rank.Rank;

public class AccountManager {

	private Hype instance;
	private PreparedStatement ps = null; private ResultSet rs = null;

	public HashMap<UUID, Account> accounts = new HashMap<UUID, Account>();
	public int playersAccount;

	public AccountManager(Hype instance) {
		this.instance=instance;

		try {

			this.ps = this.instance.getDatabaseSQL().connection.prepareStatement("SELECT * FROM `stats_global`");
			this.rs = this.ps.executeQuery();

			while(this.rs.next()) {
				this.accounts.put(UUID.fromString(rs.getString("uuid")), new Account(UUID.fromString(rs.getString("uuid")), rs.getInt("arena_kills"), rs.getInt("arena_deaths"), rs.getInt("arena_killstreak"), rs.getInt("onevsone_kills"), rs.getInt("onevsone_deaths"), rs.getInt("onevsone_wins")));
				playersAccount++;
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Account playerAccount(UUID id) {
		if(this.accounts.get(id)==null|| !this.accounts.containsKey(id))return null;
		return this.accounts.get(id);
	}
	public void playerUpdateAccount(UUID id) throws SQLException {

		if(this.accounts.get(id)==null || !this.accounts.containsKey(id))return;

		Account acc = this.accounts.get(id)!=null?this.accounts.get(id):new Account(id, 0, 0, 0, 0, 0, 0);

		this.ps = this.instance.getDatabaseSQL().connection.prepareStatement("UPDATE `stats_global` SET `arena_kills`='"+acc.playerKillsInArena()+"',`arena_deaths`='"+acc.playerDeathsInArena()+"',`arena_killstreak`='"+acc.playerKillStreakMaxInArena()+"',`onevsone_kills`='"+acc.playerOneVsOneKills()+"',`onevsone_deaths`='"+acc.playerOneVsOneDeaths()+"',`onevsone_wins`='"+acc.playerOneVsOneWinStreak()+"' WHERE `uuid`='"+id+"';");
		this.ps.executeUpdate();
		this.ps.close();

		this.accounts.remove(this.accounts.get(id)!=null?id:null);
		this.accounts.put(id, acc);

	}
	public void createPlayerAccount(UUID id) throws SQLException {

		if(this.accounts.get(id)!=null || this.accounts.containsKey(id))return;

		Account a = new Account(id, 0, 0, 0, 0, 0, 0);
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("INSERT INTO `stats_global`(`uuid`, `arena_kills`, `arena_deaths`, `arena_killstreak`, `onevsone_kills`, `onevsone_deaths`, `onevsone_wins`) VALUES ('"+a.playerUniqueId()+"','"+a.playerKillsInArena()+"','"+a.playerDeathsInArena()+"','"+a.playerKillStreakMaxInArena()+"','"+a.playerOneVsOneKills()+"','"+a.playerOneVsOneDeaths()+"','"+a.playerOneVsOneWinStreak()+"');"); 
		this.ps.executeUpdate();
		this.accounts.put(this.accounts.get(id)!=null?id:null, a);
		this.ps.close();
	}
	public void playerDeleteAccount(UUID id) throws SQLException{

		if(this.accounts.get(id)==null) return;
		this.accounts.remove(id);

		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("DELETE FROM `stats_global` WHERE `uuid`='"+id+"'");
		this.ps.executeUpdate();
		this.ps.close();

	}

	public void sendMessageAccount(String id) {

		Player playerBy = Bukkit.getPlayer(id); 
		Rank playerByRank = this.instance.getRankManager().playerRank(id);
		League playerByLeague = this.instance.getLeagueManager().playerLeague(playerBy.getUniqueId());

		playerBy.sendMessage("");
		playerBy.sendMessage("§fUsuário: §a"+playerBy.getName());
		playerBy.sendMessage("§fXP: §6" + playerByLeague.playerXP());
		playerBy.sendMessage("§fRanking: §a"+ playerByLeague.playerLeague().getName());
		playerBy.sendMessage("§fPrimeiro login: §7"+ this.instance.getLeagueManager().formatDate(playerByLeague));
		playerBy.sendMessage("§fGrupo: " + playerByRank.playerRank().getRankName() + " §7| " + this.instance.getRankManager().formatDateExpire(playerByRank));
		playerBy.sendMessage("");
		if(playerByRank.playerRankId()<6) {
			playerBy.sendMessage("");
			playerBy.sendMessage("§c/account <player>");
		}
	}

}
