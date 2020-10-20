package br.com.hype_mc.pvp.league;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import br.com.hype_mc.pvp.Hype;


public class LeagueManager {

	private Hype instance;
	private PreparedStatement ps = null; private ResultSet rs = null;

	public HashMap<UUID, League> leagues = new HashMap<UUID, League>();

	public LeagueManager(Hype instance) {
		this.instance=instance;

		try {

			this.ps = this.instance.getDatabaseSQL().connection.prepareStatement("SELECT * FROM `league_global`");
			this.rs=this.ps.executeQuery();
			while(this.rs.next()) {
				this.leagues.put(UUID.fromString(rs.getString("uuid")), new League(UUID.fromString(rs.getString("uuid")), Leagues.valueOf(rs.getString("liga")), rs.getInt("xp"), rs.getLong("firstLogin")));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public League playerLeague(UUID id) {
		if(this.leagues.get(id)==null || !this.leagues.containsKey(id))return null;

		return this.leagues.get(id);
	}
	public void playerUpdateLeague(UUID id) throws SQLException {

		if(this.leagues.get(id)==null || !this.leagues.containsKey(id))return;

		League league = this.leagues.get(id)!=null?this.leagues.get(id):new League(id, Leagues.UNRANKED, 0, System.currentTimeMillis());
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("UPDATE `league_global` SET `liga`='"+league.playerLeague()+"',`xp`='"+league.playerXP()+"' WHERE `uuid`='"+id+"';");
		this.ps.executeUpdate();
		this.ps.close();
		
		this.leagues.remove(id);
		this.leagues.put(id, league);

	}
	public void createPlayerLeague(UUID id) throws SQLException {
		if(this.leagues.get(id)!=null || this.leagues.containsKey(id))return;
		
		League league = new League(id, Leagues.UNRANKED, 0, System.currentTimeMillis());
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("INSERT INTO `league_global`(`uuid`, `liga`, `xp`, `firstLogin`) VALUES ('"+league.playerUniqueId().toString()+"','"+league.playerLeague().toString()+"','"+league.playerXP()+"', '"+league.playerFirstLogin()+"');");
		this.ps.executeUpdate();
		this.leagues.put(id, league);
		this.ps.close();
	}
	public void playerDeleteLeague(UUID id) throws SQLException{
		if(this.leagues.get(id)==null || !this.leagues.containsKey(id))return;
		
		this.leagues.remove(id);
		this.ps=this.instance.getDatabaseSQL().connection.prepareStatement("DELETE FROM `league_global` WHERE `uuid`='"+id+"'");
		this.ps.executeUpdate();
		this.ps.close();
	}

	public String formatDate(League league) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MMMMMMMM/yyyy | HH:mm:ss");
		String date = format.format(new Date(league.playerFirstLogin()));
		return "" + date + "";
	}


}
