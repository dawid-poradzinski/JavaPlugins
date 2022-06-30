package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QandA {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "/gl guild");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase());
        p.sendMessage(ChatColor.GOLD + "======="+guild.getname()+"=======");
        p.sendMessage(ChatColor.GOLD + "   Lv  : "+guild.getlv());
        p.sendMessage(ChatColor.GOLD + "  Cech : "+guild.getcech());
        p.sendMessage(ChatColor.GOLD + "Właściciel : "+guild.getowner());
        p.sendMessage(ChatColor.GOLD + "Członkowie : "+guild.getmembers());
        p.sendMessage(ChatColor.GOLD + "Ochrona : "+guild.getguard());
        p.sendMessage(ChatColor.GOLD + "Misja Weekendowa : "+guild.getweek());
        p.sendMessage(ChatColor.GOLD + "Xp do next lv : "+guild.getxp()+"/"+guild.getnextlv());
        return true;
    }
}