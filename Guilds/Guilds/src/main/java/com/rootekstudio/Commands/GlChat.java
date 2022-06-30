package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        String message = "";
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name"));
        for(int i=0;i<args.length;i++){
            message=message+args[i]+" ";
        }
        for(int i=0;i<guild.getmembers().size();i++){
            if(Bukkit.getPlayer(guild.getmembers().get(i)) != null){
        Bukkit.getPlayer(guild.getmembers().get(i)).sendMessage(ChatColor.DARK_GRAY +"["+p.getName()+"]"+" "+message);
        }
        }
        return true;
    }
}