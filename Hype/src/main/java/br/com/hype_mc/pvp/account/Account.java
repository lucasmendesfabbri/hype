package br.com.hype_mc.pvp.account;

import java.util.UUID;

public class Account {
	
	private UUID id;
	private int arena_kills, arena_deaths, arena_killstreaks,oneVSone_kills, oneVSone_deaths, oneVsOne_winStreak;;
	
	public Account(UUID id, int arena_kills, int arena_deaths, int arena_killstreaks, int oneVSone_kills, int oneVSone_deaths, int oneVsOne_winStreak) {
		this.id=id;this.arena_kills=arena_kills;this.arena_deaths=arena_deaths;this.arena_killstreaks=arena_killstreaks;
		this.oneVSone_deaths=oneVSone_deaths;this.oneVSone_kills=oneVSone_kills;this.oneVsOne_winStreak=oneVsOne_winStreak;
	
	}
	
	public UUID playerUniqueId() {
		return id;
	}
	public int playerDeathsInArena() {
		return arena_deaths;
	}
	public int playerKillsInArena() {
		return arena_kills;
	}
	public int playerKillStreakMaxInArena() {
		return arena_killstreaks;
	}
	public int playerOneVsOneDeaths() {
		return oneVSone_deaths;
	}
	public int playerOneVsOneKills() {
		return oneVSone_kills;
	}
	public int playerOneVsOneWinStreak() {
		return oneVsOne_winStreak;
	}
	public void updateDeathsInOneVsOne(int oneVSone_deaths) {
		this.oneVSone_deaths = oneVSone_deaths;
	}
	public void updateKillsInOneVsOne(int oneVSone_kills) {
		this.oneVSone_kills = oneVSone_kills;
	}
	public void updateWinStreakInOneVsOne(int oneVsOne_winStreak) {
		this.oneVsOne_winStreak = oneVsOne_winStreak;
	}
	
	public void updateDeathsInArena(int arena_deaths) {
		this.arena_deaths = arena_deaths;
	}
	public void updateKillsInArena(int arena_kills) {
		this.arena_kills = arena_kills;
	}
	public void updateKillStreakMaxInArena(int arena_killstreaks) {
		this.arena_killstreaks = arena_killstreaks;
	}
	

}
