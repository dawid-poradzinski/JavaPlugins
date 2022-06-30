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

public class Kick {
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
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "/gl kick <gracz>");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        if (!p.getName().equals(guild.getowner())) {
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem");
            return true;
        }
        if(SettingsManager.getPlayers().get(args[1]) == null){
            p.sendMessage(ChatColor.RED + "Upewnij się że podajesz dobrą nazwę");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[1]);
        if (!guild.getname().equals(SettingsManager.getPlayers().get(target.getName() + ".name"))) {
            p.sendMessage(ChatColor.RED + "Gracz nie jest z tobą w gildi");
            return true;
        }
        if(target.getName().equals(p.getName())){
            p.sendMessage(ChatColor.RED +"Nie możesz wyrzucić siebie samego");
            return true;
        }
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
            ProtectedRegion w = regions.getRegion(guild.getname().toLowerCase());
            DefaultDomain members = w.getMembers();
            members.removePlayer(target.getName());
            w.setMembers(members);
        guild.removeMembers(target.getName());
        SettingsManager.saveGuilds();
        SettingsManager.getPlayers().set(target.getName()+".name", null);
        SettingsManager.savePlayers();
        p.sendMessage(ChatColor.GREEN + "Wyrzuciłeś gracza: "+target.getName());
        if(target != null){
        target.sendMessage(ChatColor.RED + "Zostałeś wyrzucony z gildi");
        }
        return true;
    }
}