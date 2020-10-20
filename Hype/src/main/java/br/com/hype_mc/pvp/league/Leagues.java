package br.com.hype_mc.pvp.league;

public enum Leagues {

	UNRANKED("Unranked", "-", 200);
	
	private String name, symbol;
	private int xpSuficient;
	
	private Leagues(String name, String symbol, int xp) {
		this.name=name;this.symbol=symbol;this.xpSuficient=xp;
	}
	
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public int getXpSuficient() {
		return xpSuficient;
	}
	
}
