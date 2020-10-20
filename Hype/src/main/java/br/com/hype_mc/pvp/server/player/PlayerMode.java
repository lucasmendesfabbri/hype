package br.com.hype_mc.pvp.server.player;

public enum PlayerMode {

	ARENA("§6§lARENA"), 
	ONEVSONE("§6§l1V1"), 
	SPAWN("§6§lSPAWN");
	
	private String playerMode;
	
	private PlayerMode(String playerMode) {
		this.playerMode=playerMode;
	}
	public String getPlayerMode() {
		return playerMode;
	}
	
}
