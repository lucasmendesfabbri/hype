package br.com.hype_mc.pvp.rank.listeners;

import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.rank.Rank;

public class PlayerJoinRank implements Listener{

	private Hype instance;

	public PlayerJoinRank(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}

	@EventHandler
	void rankJoin(PlayerJoinEvent rankEvent) {
		Player p = rankEvent.getPlayer();
		setMember(p.getName());
		this.instance.getRankManager().playerRankExpire(this.instance.getRankManager().playerRank(p.getName()));
		
	}

	public void setMember(String id) {
		Rank rank=this.instance.getRankManager().playerRank(id);
		try {
			if(rank == null) {
				this.instance.getRankManager().createPlayerRank(id);
			}else {
                this.instance.getRankManager().playerUpdateRank(id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


}
