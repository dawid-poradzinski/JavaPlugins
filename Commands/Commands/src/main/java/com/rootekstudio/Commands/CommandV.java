package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandV implements CommandExecutor {
    Commands plugin;

    public CommandV(Commands plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        if(args.length != 0){
            sender.sendMessage(ChatColor.RED + "Nie można podawać tu wartości");
            return true;
        }
        Player p = (Player) sender;
        if(SettingsManager.getPerm().getString(p.getName()+".admin").equals("nie")){
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
            return true;
        }
        if(!Commands.vanish.contains(p.getName())){
            for(Player online : Bukkit.getOnlinePlayers()){
                online.hidePlayer(p);
            }
            Commands.vanish.add(p.getName());
            sender.sendMessage(ChatColor.GREEN + "Stałeś się niewidzialny");
        return true;
        }
        else
        {
            for(Player online : Bukkit.getOnlinePlayers()){
                online.showPlayer(p);
            }
            Commands.vanish.remove(p.getName());
            sender.sendMessage(ChatColor.GREEN + "Stałeś się widzialny");
        }
        return true;
    }
}
