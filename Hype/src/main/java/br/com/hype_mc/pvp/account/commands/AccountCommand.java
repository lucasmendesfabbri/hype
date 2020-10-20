package br.com.hype_mc.pvp.account.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.rank.Rank;

public class AccountCommand implements CommandExecutor {

	private Hype instance;

	public AccountCommand(Hype instance) {
		this.instance=instance;
		this.instance.getCommand("acc").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {
			if(command.getName().equalsIgnoreCase("acc")) {
				Player playerBy = (Player) sender;
				Rank playerByRank = this.instance.getRankManager().playerRank(playerBy.getName());

				if(args.length == 0) {
                   this.instance.getAccountManager().sendMessageAccount(playerBy.getName());
                   return true;
				}
				if(args.length >=0) {

					if(playerByRank.playerRank().getRankId() >= 6) {
						playerBy.sendMessage("§cSem permissão.");
						return true;
					}
				}

			}
		}

		return true;
	}

}
