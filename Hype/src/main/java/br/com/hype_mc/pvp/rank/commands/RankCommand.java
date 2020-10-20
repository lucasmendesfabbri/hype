package br.com.hype_mc.pvp.rank.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.hype_mc.pvp.Hype;
import br.com.hype_mc.pvp.rank.Rank;
import br.com.hype_mc.pvp.rank.Ranks;

public class RankCommand implements CommandExecutor {

	private Hype instance;

	public RankCommand(Hype instance) {
		this.instance=instance;
		this.instance.getCommand("group").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(command.getName().equalsIgnoreCase("group")) {

			if(sender instanceof Player) {
				Player player = (Player)sender;
				Rank playerBy = this.instance.getRankManager().playerRank(player.getName());
				if(playerBy.playerRankId()>3) {
				    player.sendMessage("§cVocê não possui §lPERMISSÃO§c para usar o comando §l/GROUP§c!");
					return true;
				}
			}
			
			if(args.length == 0) {
				sender.sendMessage("§e§lGRUPO §6§l/GROUP <usuário> <grupo> <tempo>".toUpperCase());
				return true;
			}
			if(args.length == 1) {
				sender.sendMessage("§e§lGRUPO §6§l/GROUP <usuário> <grupo> <tempo>".toUpperCase());
				return true;
			}
			if(args.length > 3) {
				sender.sendMessage("§e§lGRUPO §6§l/GROUP <usuário> <grupo> <tempo>".toUpperCase());
				return true;
			}

			String target = args[0];
			String rankName = args.length == 1 ? "§E§lGRUPO §6§l/GROUP <usuário> <grupo> <tempo>".toUpperCase() : args[1].toUpperCase();

			if(!this.instance.getRankManager().ranks.containsKey(target)) {
				sender.sendMessage("§cA §lCONTA§c §8(§c"+args[0]+"§8)§c está §lNÃO FOI ENCONTRADA§c.");
				return true;
			}
			if(verifyRank(rankName)==false) {
				sender.sendMessage("§cO §lGRUPO§c §8(§c"+rankName.toUpperCase()+"§8) §cnão foi §lENCONTRADO§c.");
				return true;
			}


			Rank targetRank = this.instance.getRankManager().playerRank(target);
			
		
			if(sender instanceof Player) {
				Player p = (Player)sender;
				Rank playerByRank = this.instance.getRankManager().playerRank(p.getName());
                if(targetRank.playerRankId()<playerByRank.playerRankId())sender.sendMessage("§cVocê não pode alterar esse Grupo§c!");
                if(targetRank.playerRank().getName() == rankName)sender.sendMessage("§cO Grupo §8(§7"+rankName.toUpperCase()+"§8) §cjá pertence à conta dê §8(§7"+target+"§8)§c!");
                if(targetRank.playerName()==playerByRank.playerName())sender.sendMessage("§cVocê não pode alterar grupo de sí próprio.");
                return true;
			}
			if(args.length == 2) {
				targetRank.setRank(Ranks.valueOf(rankName.toUpperCase()));
				targetRank.setRankExpire(-1);
				targetRank.setRankTimer(System.currentTimeMillis());
				targetRank.setRankId(Ranks.valueOf(rankName.toUpperCase()).getRankId());
				this.instance.getRankManager().ranks.remove(target);this.instance.getRankManager().ranks.put(target, targetRank);
				PreparedStatement ps;
				try {
					ps = this.instance.getDatabaseSQL().connection.prepareStatement("UPDATE `rank_global` SET `rank`='"+targetRank.playerRank().toString()+"',`rankId`='"+targetRank.playerRankId()+"',`rankExpire`='"+targetRank.playerRankExpire()+"',`rankTimer`='"+targetRank.playerRankTimer()+"' WHERE `name`='"+targetRank+"';");
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sender.sendMessage("§aO Grupo §8(§7"+rankName.toUpperCase()+"§8)§a foi adicionado à conta §8(§7"+target+"§8)§a com sucesso!");
				return true;
			}

			//TEM TEMPORARIO FÁCIL, PORÉM TO COM PREGUIÇA DO CARALHO DE FAZER!
			
			
			return true;

		}

		return true;
	}

	public boolean verifyPlayer(String target) {
		if(!this.instance.getRankManager().ranks.containsKey(target)&&this.instance.getRankManager().playerRank(target)==null) return true;
		return false;
	}

	public boolean verifyRank(String rankName) {
		for(Ranks ranks:Ranks.values()) if(ranks.getName().equalsIgnoreCase(rankName))return true;
		return false;
	}



}
