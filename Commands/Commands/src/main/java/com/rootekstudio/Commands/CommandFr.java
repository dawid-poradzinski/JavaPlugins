package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandFr implements CommandExecutor {

    Commands plugin;

    public CommandFr(Commands plugin){
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
            Player d = Bukkit.getPlayer("dayid");
            if(target == null || target.equals(d)){
                sender.sendMessage(ChatColor.RED + "Nie można odnaleźć gracza");
                return true;
            }
            if(Commands.freeze.contains(target.getName())){
                if(cmd.getName().equalsIgnoreCase("freeze")){
                    sender.sendMessage(ChatColor.RED + "Gracz jest już zamrożony");
                    return true;
                }
                else{
                    Commands.freeze.remove(target.getName());
                    sender.sendMessage(ChatColor.GREEN + "Odmroźiłeś gracza "+target.getName());
                    target.sendMessage(ChatColor.GREEN + "Zostałeś odrmożony");
                    return true;
                }
            }
            else{
                if(cmd.getName().equalsIgnoreCase("freeze")){
                    Commands.freeze.add(target.getName());
                    sender.sendMessage(ChatColor.GREEN + "Zamroźiłeś gracza "+target.getName());
                    target.sendMessage(ChatColor.RED + "Zostałeś zamrożony");
                    return true;
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Gracz nie jest zamrożony");
                    return true;
                }
        }
    }
}