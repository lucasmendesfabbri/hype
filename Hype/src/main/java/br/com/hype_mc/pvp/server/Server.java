package br.com.hype_mc.pvp.server;

import java.util.ArrayList;

public class Server {

	private String stats;
	private boolean chat, pvp, build, dano, drops, whitelist;
	
	public ArrayList<Integer> playersCountInArena = new ArrayList<Integer>();
	public ArrayList<Integer> playersCountInOneVsOne = new ArrayList<Integer>();

	public Server() {
		this.stats="ONLINE";
		this.chat=true;
		this.pvp=false;
		this.build=false;
		this.drops=false;
		this.whitelist=false;
	}
	
	public String getStats() {
		return stats;
	}
	public boolean isChat() {
		return chat;
	}
	public boolean isPvp() {
		return pvp;
	}
	public boolean isBuild() {
		return build;
	}
	public boolean isDano() {
		return dano;
	}
	public boolean isDrops() {
		return drops;
	}
	public boolean isWhitelist() {
		return whitelist;
	}
	public void setBuild(boolean build) {
		this.build = build;
	}
	public void setChat(boolean chat) {
		this.chat = chat;
	}
	public void setDano(boolean dano) {
		this.dano = dano;
	}
	public void setDrops(boolean drops) {
		this.drops = drops;
	}
	
	
	
}
