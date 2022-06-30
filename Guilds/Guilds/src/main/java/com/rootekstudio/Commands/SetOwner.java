package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetOwner {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if(args.length != 2){
            p.sendMessage(ChatColor.RED + "/gl setowner <gracz>");
            return true;
        }
        if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name"));
        if(!p.getName().equals(guild.getowner())){
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem gildi");
            return true;
        }
        if(!SettingsManager.getPlayers().get(args[1]+".name").equals(guild.getname().toLowerCase())){
            p.sendMessage(ChatColor.RED + "Gracz nie istnieje/Nie jest z tobą w gildi");
            return true;
        }
        guild.setOwner(args[1]);
        SettingsManager.saveGuilds();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
            ProtectedRegion w = regions.getRegion(guild.getname().toLowerCase());
            DefaultDomain members = w.getMembers();
            members.addPlayer(sender.getName());
            w.setMembers(members);
            DefaultDomain owner = new DefaultDomain();
            owner.addPlayer(args[1]);
            w.setOwners(owner);
            sender.sendMessage(ChatColor.GREEN + "Przekazałeś dowódctwo graczowi: "+args[1]);    
            Player target = Bukkit.getPlayer(args[1]);
            if(target != null){
            target.sendMessage(ChatColor.GREEN + "Zostałeś przywódcą gildi");    
            }
        return true;
    }
}