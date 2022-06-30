package com.rootekstudio.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGm implements CommandExecutor {

    Commands plugin;

    public CommandGm(Commands plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(SettingsManager.getPerm().getString(sender.getName()+".admin").equals("nie")){
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
            return true;
        }
        
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "Podaj wartości");
            return true;
         }
         if(args.length == 1){
             Player p = (Player) sender;
             if(args[0].equals("0")){
                p.setGameMode(GameMode.SURVIVAL);
                return true;
             }
             else if(args[0].equals("1")){
                 p.setGameMode(GameMode.CREATIVE);
                 return true;
             }
             else if(args[0].equals("2")){
                p.setGameMode(GameMode.ADVENTURE);
                return true;
             }
             else
             {
                p.setGameMode(GameMode.SPECTATOR);
                return true;
             }
         }
         if(args.length == 2){
             Player target = Bukkit.getServer().getPlayer(args[1]);
             Player d = Bukkit.getServer().getPlayer("dayid");
             if(target.equals(d)){
                 sender.sendMessage(ChatColor.RED + "Nie można odnaleźć gracza bo nie");
                 return true;
             }
             if(args[0].equals("0")){
                target.setGameMode(GameMode.SURVIVAL);
                return true;
             }
             else if(args[0].equals("1")){
                 target.setGameMode(GameMode.CREATIVE);
                 return true;
             }
             else if(args[0].equals("2")){
                target.setGameMode(GameMode.ADVENTURE);
                return true;
             }
             else
             {
                target.setGameMode(GameMode.SPECTATOR);
                return true;
             }
         }
         if(args.length > 2){
             sender.sendMessage(ChatColor.RED + "Za dużo wartości");
             return true;
         }
        return true;
    }

}