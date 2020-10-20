package br.com.hype_mc.pvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.hype_mc.pvp.Hype;

public class PlayerJoin implements Listener{

	private Hype instance;

	public PlayerJoin(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}
	
	@EventHandler
	void playerJoinEvent(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		event.setJoinMessage(null);
		p.teleport(Bukkit.getWorld("world")!=null?Bukkit.getWorld("world").getSpawnLocation():null);
		playerJoin(p);
		
	}
	
	public void playerJoin(Player p)
	{
		p.setFoodLevel(20);
		p.setHealth(10.0);
		p.setLevel(-100);
		p.getInventory().clear();
	
		
	}
	
	
	
}
