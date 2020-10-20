package br.com.hype_mc.pvp.rank;

public enum Ranks {

	DONO("dono","§4Dono", "§4§lDONO§4", 1),
	DIRETOR("diretor","§4Diretor", "§4§lDIRETOR§4", 2),
	ADMIN("admin","§cAdmin", "§c§lADMIN§c", 3),
	MODERADOR("mod","§5Mod", "§5§lMOD§5", 4),
	TRIAL("trial","§dTrial", "§d§lTRIAL§d", 5),
	YOUTUBERPLUS("yt+","§3Youtuber+", "§3§lYT+§3", 5),
	YOUTUBER("yt","§bYoutuber", "§b§lYT§b", 10),
	BETA("beta","§1Beta", "§1§lBETA§1", 11),
	MEMBER("membro","§7Membro", "§7", 30);
	
	private String name2, name, prefix;
	private int id;
	
	private Ranks(String name2,String name,String prefix, int id) {
		this.name2=name2;this.name=name;this.prefix=prefix;this.id=id;
	}
	
	public int getRankId() {
		return id;
	}
	public String getName() {
		return name2;
	}
	public String getRankName() {
		return name;
	}
	public String getRankPrefix() {
		return prefix;
	}
	
}
