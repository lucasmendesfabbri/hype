package br.com.hype_mc.pvp.rank.listeners;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.hype_mc.pvp.Hype;

public class PlayerQuitRank implements Listener{

	private Hype instance;

	public PlayerQuitRank(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}

	@EventHandler
	void rankQuit(PlayerQuitEvent eventRank) {
		Player p = eventRank.getPlayer();
		try {
			this.instance.getRankManager().playerUpdateRank(p.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
