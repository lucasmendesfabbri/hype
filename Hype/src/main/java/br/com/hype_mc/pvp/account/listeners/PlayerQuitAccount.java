package br.com.hype_mc.pvp.account.listeners;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.hype_mc.pvp.Hype;

public class PlayerQuitAccount implements Listener {

	private Hype instance;

	public PlayerQuitAccount(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}
	
	@EventHandler
	void accountQuit(PlayerQuitEvent event) {
		try {
			this.instance.getAccountManager().playerUpdateAccount(event.getPlayer().getUniqueId());
			this.instance.getLeagueManager().playerUpdateLeague(event.getPlayer().getUniqueId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
