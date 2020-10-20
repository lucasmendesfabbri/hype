package br.com.hype_mc.pvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.hype_mc.pvp.account.AccountManager;
import br.com.hype_mc.pvp.account.commands.AccountCommand;
import br.com.hype_mc.pvp.account.listeners.PlayerJoinAccount;
import br.com.hype_mc.pvp.account.listeners.PlayerQuitAccount;
import br.com.hype_mc.pvp.database.Database;
import br.com.hype_mc.pvp.league.LeagueManager;
import br.com.hype_mc.pvp.listeners.PlayerBuild;
import br.com.hype_mc.pvp.listeners.PlayerChat;
import br.com.hype_mc.pvp.listeners.PlayerJoin;
import br.com.hype_mc.pvp.rank.RankManager;
import br.com.hype_mc.pvp.rank.commands.RankCommand;
import br.com.hype_mc.pvp.rank.listeners.PlayerJoinRank;
import br.com.hype_mc.pvp.rank.listeners.PlayerQuitRank;
import br.com.hype_mc.pvp.server.Server;

public class Hype extends JavaPlugin{

	public Database databaseSQL;
	public RankManager rankManager;
	public AccountManager accountManager;
	public LeagueManager leagueManager;
	public Server serverManager;


	public void onEnable() {

		databaseSQL = new Database();
		
		for(Entity entity : Bukkit.getWorld("world").getEntities()) entity.remove();
		
		this.serverManager = new Server();

		this.rankManager = new RankManager(this);
		this.accountManager = new AccountManager(this);
		this.leagueManager = new LeagueManager(this);
		
		new RankCommand(this);
		new AccountCommand(this);
		
		new PlayerJoinRank(this);new PlayerQuitRank(this);
		new PlayerJoinAccount(this);new PlayerQuitAccount(this);
		new PlayerJoin(this); new PlayerChat(this); new PlayerBuild(this);
		
	}
	
	public void onDisable() {

		databaseSQL.closeConnection();
		rankManager.ranks.clear(); rankManager = null;
		accountManager.accounts.clear(); accountManager = null;
		leagueManager.leagues.clear(); leagueManager = null;
		this.serverManager=null;

	}

	public RankManager getRankManager() {
		return rankManager;
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}
	public LeagueManager getLeagueManager() {
		return leagueManager;
	}
	public Server getServerManager() {
		return serverManager;
	}

	public Database getDatabaseSQL() {
		return databaseSQL;
	}

}
