package br.com.hype_mc.pvp.rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;

import br.com.hype_mc.pvp.Hype;

public class RankManager {

	private Hype instance;
	private PreparedStatement ps = null; private ResultSet rs = null;
	
	public RankManager(Hype instance) {
		this.instance = instance;
		
		try {
			
			this.ps = this.instance.getDatabaseSQL().connection.prepareStatement("SELECT * FROM `rank_global`;");
			this.rs=this.ps.executeQuery();
			while(this.rs.next()) {
				this.ranks.put(rs.getString("name"), new Rank(rs.getString("name"), Ranks.valueOf(rs.getString("rank")), rs.getInt("rankId"), rs.getLong("rankExpire"), rs.getLong("rankTimer")));
	
			}
		    for(Map.Entry<String, Rank> players : this.ranks.entrySet()) {
		    	Bukkit.getLogger().info("INFO: " + players.getKey());
		    }
			this.ps.close();
			this.rs.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public HashMap<String, Rank> ranks = new HashMap<String, Rank>();
	
	public Rank playerRank(String id) {
		
		if(this.ranks.get(id)==null)return null;
		
		return this.ranks.get(id);
	}
	
	public void playerUpdateRank(String id) throws SQLException {
		
		if(this.ranks.get(id)==null)return;
		
		Rank rank = this.playerRank(id);
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("UPDATE `rank_global` SET `rank`='"+rank.playerRank().toString()+"',`rankId`='"+rank.playerRankId()+"',`rankExpire`='"+rank.playerRankExpire()+"',`rankTimer`='"+rank.playerRankTimer()+"' WHERE `name`='"+id+"';");
		this.ps.executeUpdate();
		this.ps.close();
		this.ranks.remove(id);
		this.ranks.put(id, rank);
		
	}
	public void createPlayerRank(String id) throws SQLException {
		
		if(this.ranks.containsKey(id))return;
		
		Rank rank = new Rank(id, Ranks.MEMBER, Ranks.MEMBER.getRankId(), -1, -1);
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("INSERT INTO `rank_global`(`name`, `rank`, `rankId`, `rankExpire`, `rankTimer`) VALUES ('"+id+"','"+rank.playerRank().toString()+"','"+rank.playerRankId()+"','"+rank.playerRankExpire()+"','"+rank.playerRankTimer()+"');"); 
		this.ps.executeUpdate();
		this.ranks.put(id, rank);
		this.ps.close();
	}
	public void playerDeleteRank(String id) throws SQLException{
		
		if(this.ranks.get(id)==null)return;
		
		this.ranks.remove(id);
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("DELETE FROM `rank_global` WHERE `name`='"+id+"'");
		this.ps.executeUpdate();
		this.ps.close();
	}
	
	public void playerRankExpire(Rank p) {

		if(p.playerRankExpire() <= System.currentTimeMillis() && p.playerRankExpire() > 0 && p.playerRank()!=Ranks.MEMBER) {
			Bukkit.getPlayer(p.playerName()).sendMessage("§cOh droga! Seu §lRANK§8("+p.playerRank().getRankPrefix()+"§8)§c expirou!");
			Bukkit.getPlayer(p.playerName()).sendMessage("§cAdquira novamente: §ehttps://mc-hype.com.br/loja?servidor=1385&categoria=2301");
			p.setRank(Ranks.MEMBER);
			p.setRankId(Ranks.MEMBER.getRankId());
			p.setRankExpire(-1);
			p.setRankTimer(-1);
			try {
				this.playerUpdateRank(p.playerName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String formatDateExpire(Rank rank) {
		SimpleDateFormat format = new SimpleDateFormat("EEEE, (dd)/(MMMMMMMM)/(yyyy) | HH:mm:ss )");
		String date = format.format(new Date(rank.playerRankExpire()));
		if(rank.playerRankExpire() <= -1) return "§7Permamente";
		return "§7" + date + "";
	}
	

	
}
