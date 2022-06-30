package com.rootekstudio.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTa implements CommandExecutor {

    Commands plugin;
 
    public CommandTa(Commands plugin){
        this.plugin = plugin;
    }

    @Override
    public  boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if(SettingsManager.getPerm().getString(p.getName()+".commands").equals("nie")){
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("tpaccept")) {
		if(Commands.tp.get(p) == null) {
			p.sendMessage(ChatColor.RED + "Nie Masz zadnej prosby");
		}
		else {
			Commands.tp.get(p).teleport(p);
			p.sendMessage(ChatColor.GREEN + "Zaakceptowales prosbe");
			Commands.tp.get(p).sendMessage(ChatColor.DARK_GREEN + "Gracz zaakceptowal twoja prosbe");
			Commands.tp.put(p, null);
            }
        }
        else
        {
            if(Commands.tp.get(p) == null) {
                p.sendMessage(ChatColor.RED + "Nie Masz zadnej prosby");	
            }
            else {
                p.sendMessage(ChatColor.GREEN + "Odrzuciles prosbe");
                Commands.tp.get(p).sendMessage(ChatColor.GREEN + "Gracz odrzucil twoja prosbe");
                Commands.tp.put(p, null);
            }
            return true;   
        }
		return true;
    }
}