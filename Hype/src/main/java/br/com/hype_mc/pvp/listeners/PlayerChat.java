package br.com.hype_mc.pvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.rank.Rank;
import br.com.hype_mc.pvp.rank.Ranks;

public class PlayerChat implements Listener {

	private Hype instance;

	public PlayerChat(Hype instance) {
		this.instance=instance;
		Bukkit.getPluginManager().registerEvents(this, this.instance);
	}

	@EventHandler
	void playerAsync(AsyncPlayerChatEvent event) {
		
		Player p = event.getPlayer(); Rank playerRank = this.instance.getRankManager().playerRank(p.getName());

		String messageFormat = playerRank.playerRankId()>20?"§7" + event.getMessage() : "§f" + event.getMessage();
		String format = playerRank.playerRank()!=Ranks.MEMBER?""+playerRank.playerRank().getRankPrefix()+" " + p.getName()+": "+messageFormat:"§7"+p.getName()+": "+ messageFormat;
		event.setFormat(format);

		if(this.instance.getServerManager().isChat()==false) {
			if(playerRank.playerRankId()<6) {
				event.setCancelled(false);
			}
			event.setCancelled(true);
			p.sendMessage("§cO bate-papo está desabilitado.");
		}
	}

}
