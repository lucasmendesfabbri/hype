package br.com.hype_mc.pvp.league;

import java.util.UUID;

public class League {

	private UUID id;
	private Leagues leagues;
	private int xp;
	private long FirstLogin;
	
	public League(UUID id, Leagues leagues, int xp, long firstLogin) {
		this.id=id;this.leagues=leagues;this.xp=xp;this.FirstLogin=firstLogin;
	}
	
	public long playerFirstLogin() {
		return FirstLogin;
	}
	public UUID playerUniqueId() {
		return id;
	}
	public Leagues playerLeague() {
		return leagues;
	}
	public int playerXP() {
		return xp;
	}

	public void setLeague(Leagues leagues) {
		this.leagues = leagues;
	}
	public void setXP(int xp) {
		this.xp = xp;
	}
	
}
