package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandMu implements CommandExecutor {

    Commands plugin;

    public CommandMu(Commands plugin){
        this.plugin = plugin;
    }

    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
        if(sender instanceof Player){
            if(SettingsManager.getPerm().getString(sender.getName()+".admin").equals("nie")){
                sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
                return true;
            }
        }
            if(args.length == 0){
                sender.sendMessage(ChatColor.RED + "Musisz podać gracza");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            Player d = Bukkit.getServer().getPlayer("dayid");
            if(target == null || target.equals(d)){
                sender.sendMessage(ChatColor.RED + "Nie można odnaleźć gracza");
                return true;
            }
            if(Commands.muted.contains(target.getName())){
                if(cmd.getName().equalsIgnoreCase("mute")){
                    sender.sendMessage(ChatColor.RED + "Gracz jest już zmutowany");
                    return true;
                }
                else{
                    Commands.muted.remove(target.getName());
                    sender.sendMessage(ChatColor.GREEN + "Odmutowałeś gracza "+target.getName());
                    target.sendMessage(ChatColor.GREEN + "Zostałeś odmutowany");
                    return true;
                }
            }
            else{
                if(cmd.getName().equalsIgnoreCase("mute")){
                    Commands.muted.add(target.getName());
                    sender.sendMessage(ChatColor.GREEN + "Zmutowałeś gracza "+target.getName());
                    target.sendMessage(ChatColor.RED + "Zostałeś zmutowany");
                    return true;
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Gracz nie jest zmutowany");
                    return true;
                }
        }
    }
}