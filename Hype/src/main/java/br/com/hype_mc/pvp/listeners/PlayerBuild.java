package br.com.hype_mc.pvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import br.com.hype_mc.pvp.Hype;

public class PlayerBuild implements Listener{

	private Hype instance;
	
	public PlayerBuild(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}
	@EventHandler
	void buildPlace(BlockPlaceEvent event) {
	     if(this.instance.getServerManager().isBuild()==true&&event.getPlayer().getGameMode()==GameMode.CREATIVE)event.setCancelled(false);
	     event.setCancelled(true);
	}
	@EventHandler
	void buildBreak(BlockBreakEvent event) {
	     if(this.instance.getServerManager().isBuild()==true&&event.getPlayer().getGameMode()==GameMode.CREATIVE)event.setCancelled(false);
	     event.setCancelled(true);
	}
	
}
