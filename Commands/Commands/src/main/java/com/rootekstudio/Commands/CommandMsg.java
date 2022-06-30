package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor {

    Commands plugin;

    public CommandMsg(Commands plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
         if(!(sender instanceof Player)){
             sender.sendMessage(ChatColor.RED + "Musisz być graczem");
             return true;
         }
         Player p = (Player) sender;
         if(SettingsManager.getPerm().getString(p.getName()+".commands").equals("nie")){
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
            return true;
        }
         if(cmd.getName().equalsIgnoreCase("r")){
            if(Commands.msg.get(p) == null){
                sender.sendMessage(ChatColor.RED + "Nie rozpoczołeś/odpisałeś na żadną konwersacje");
                return true;
            }
            else
            {
                String message = "";
                for(int i = 0; i != args.length; i++)
                    message += args[i] + " ";
    
                Commands.msg.get(p).sendMessage(ChatColor.GREEN + sender.getName() + " >>> " + Commands.msg.get(p).getName() + " " + message);
    
                sender.sendMessage(ChatColor.GREEN + sender.getName() + " >>> " + Commands.msg.get(p).getName() + " " + message);
                return true;
            }
        }

		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED+"Podaj gracza");
			return true;
        }
        Player target = sender.getServer().getPlayer(args[0]);
        Player d = Bukkit.getServer().getPlayer("dayid");
        if(target == null || target.equals(d)){        
            sender.sendMessage(ChatColor.RED+"Gracz nie jest online");         
        }
        else {
            if(Commands.msg.get(p) != null) {
                //if its not working change to remove and only p
                Commands.msg.put(p, null);
            }
            Commands.msg.put(p, target);
            String message = "";
            for(int i = 1; i != args.length; i++)
                message += args[i] + " ";

            target.sendMessage(ChatColor.GREEN + sender.getName() + " >>> " + target.getName() + " " + message);

            sender.sendMessage(ChatColor.GREEN + sender.getName() + " >>> " + target.getName() + " " + message);
        }


        return true;
    }

}