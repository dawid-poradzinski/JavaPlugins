package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DestroyGuild {

    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if(args.length != 1){
            p.sendMessage(ChatColor.RED + "/gl destroy");
            return true;
        }
        if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase());
        if(!p.getName().equals(guild.getowner())){
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem gildi");
            return true;
        }
        if(guild.getdefense() || guild.getattack()){
            p.sendMessage(ChatColor.RED + "Nie możesz niszczyć gildi w czasie wojny");
            return true;
        }
        if(guild.getally().size()!=0){
            p.sendMessage(ChatColor.RED + "Musisz usunąć wszystkie sojusze");
            return true;
        }
        for(int i=0;i<guild.getmembers().size();i++){
            SettingsManager.getPlayers().set(guild.getmembers().get(i)+".name", null);
            if(Bukkit.getPlayer(guild.getmembers().get(i)) != null){
                Player target = Bukkit.getPlayer(guild.getmembers().get(i));
                target.sendMessage(ChatColor.RED + "Twoja gildia: "+guild.getname()+" została rozwiązana");
            }
        }
        SettingsManager.savePlayers();
        String na = guild.getname().toLowerCase();
        World w = Bukkit.getServer().getWorld(SettingsManager.getGuilds().getString(na+".heart.w"));
        Double x = SettingsManager.getGuilds().getDouble(na+".heart.x");
        Double y = SettingsManager.getGuilds().getDouble(na+".heart.y");
        Double z = SettingsManager.getGuilds().getDouble(na+".heart.z");
        Location l = new Location(w,x,y,z);
        l.getBlock().setType(Material.AIR);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        regions.removeRegion(guild.getname().toLowerCase());
        SettingsManager.getGuilds().set(guild.getname().toLowerCase(), null);
        SettingsManager.saveGuilds();
        
        return true;
    }
}