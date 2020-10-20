package br.com.hype_mc.pvp.account.listeners;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.account.Account;
import br.com.hype_mc.pvp.league.League;

public class PlayerJoinAccount implements Listener{
	
	private Hype instance;

	public PlayerJoinAccount(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}
	
	@EventHandler
	void accountJoin(PlayerJoinEvent event) {
		try {
			setDefaultAccount(event.getPlayer().getUniqueId());
			setDefaultLeague(event.getPlayer().getUniqueId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultAccount(UUID id) throws SQLException {
		Account acc = this.instance.getAccountManager().playerAccount(id);
		if(acc==null||id==null) this.instance.getAccountManager().createPlayerAccount(id);
		this.instance.getAccountManager().playerUpdateAccount(id);
	}
	public void setDefaultLeague(UUID id) throws SQLException {
		League liga = this.instance.getLeagueManager().playerLeague(id);
		if(liga==null||id==null) this.instance.getLeagueManager().createPlayerLeague(id);
		this.instance.getLeagueManager().playerUpdateLeague(id);
	}
	


}
