package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp implements CommandExecutor {

	Commands plugin;

    public CommandTp(Commands plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        
        if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Musisz byc graczem");
			return true;
		}
		if(SettingsManager.getPerm().getString(sender.getName()+".commands").equals("nie")){
			sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
			return true;
		}
		Player p = (Player) sender;
			if(args.length == 0) {
				p.sendMessage(ChatColor.RED + "Musisz podac gracza");
				return true;
			}
			else {
				Player target = p.getServer().getPlayer(args[0]);
				Player d = Bukkit.getServer().getPlayer("dayid");
				if(target == null || target .equals(d)) {
					p.sendMessage(ChatColor.RED + "Nie Mozna odnalezc gracza");
					return true;
				}
				else {
					Commands.tp.put(target, p);
					p.sendMessage(ChatColor.GREEN + "Wyslales zapytanie");
					target.sendMessage(ChatColor.GREEN + "Gracz " + p.getName() + " Chce sie do ciebie przeteleportowac");
					target.sendMessage(ChatColor.GREEN + "Zaakceptuj /tpaccept lub odrzuc /tpdeny");
					return true;
				}
        }
    }

}