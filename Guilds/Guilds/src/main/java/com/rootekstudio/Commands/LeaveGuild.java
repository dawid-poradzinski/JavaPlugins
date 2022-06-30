package com.rootekstudio.Commands;


import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveGuild {

    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if(args.length != 1){
            p.sendMessage(ChatColor.RED + "/gl leave");
            return true;
        }
        if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name"));
        if(p.getName().equals(guild.getowner())){
            p.sendMessage(ChatColor.RED + "Jako właściciel nie możesz opuścić gildi");
            return true;
        }
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
            DefaultDomain members = regions.getRegion(guild.getname().toLowerCase()).getMembers();
            members.removePlayer(sender.getName());
            regions.getRegion(guild.getname().toLowerCase()).setMembers(members);
        guild.removeMembers(p.getName());
        SettingsManager.saveGuilds();
        SettingsManager.getPlayers().set(p.getName()+".name", null);
        SettingsManager.savePlayers();
        p.sendMessage(ChatColor.GREEN + "Opuściłeś Gildię");
        return true;
    }
}