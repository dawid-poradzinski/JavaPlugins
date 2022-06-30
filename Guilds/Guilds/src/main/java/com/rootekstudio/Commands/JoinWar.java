package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
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

public class JoinWar {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "/gl joinwar <sojusznik>");
            return true;
        }
        if(SettingsManager.getGuilds().get(args[2].toLowerCase()) == null){
            p.sendMessage(ChatColor.RED + " Nie można odnaleźć gildi");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        Guild target = new Guild(args[2].toLowerCase());
        if(!guild.getally().contains(args[2])){
            p.sendMessage(ChatColor.RED + " Gildia nie znajduje się z tobą w sojuszu");
            return true;
        }
        if(target.getdefense() || target.getattack()){

                if(target.getdefense()){
                    for(int i=0;i<guild.getmembers().size();i++){
                        Player targetp = Bukkit.getPlayer(guild.getmembers().get(i));
                        if(targetp != null){
                             p.sendMessage(ChatColor.RED + "Twoja gildia dołączyła do obrony gildi "+target.getname()+" przeciwko gildi "+target.getMwar());   
                        }
                    }
                    Bukkit.getPlayer(target.getowner()).sendMessage(ChatColor.GREEN + "Gidlia "+guild.getname()+" dołączyła do twojej oborony");
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                    DefaultDomain owners = regions.getRegion(target.getname().toLowerCase()).getMembers();
                    for(int i=0;i<guild.getmembers().size();i++){
                        owners.addPlayer(guild.getmembers().get(i));
                    }
                    regions.getRegion(target.getname().toLowerCase()).setMembers(owners);
                    return true;
                }else if(target.getattack()){
                    for(int i=0;i<guild.getmembers().size();i++){
                        Player targetp = Bukkit.getPlayer(guild.getmembers().get(i));
                        if(targetp != null){
                             p.sendMessage(ChatColor.RED + "Twoja gildia dołączyła do ataku gildi "+target.getname()+" przeciwko gildi "+target.getawar());
                             p.sendMessage(ChatColor.RED + "Wojna rozpoczyna się za 10min");
                        }
                    }
                }
                Guild attacked = new Guild(target.getawar().toLowerCase());
                Bukkit.getPlayer(attacked.getowner()).sendMessage(ChatColor.RED + "Gildia "+guild.getname()+"dołączyła do wojny przeciwko tobie");
                attacked.setDWar(guild.getname());
                SettingsManager.saveGuilds();
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable() {

                    @Override
                    public void run() {
                        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                        RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                        DefaultDomain owners = regions.getRegion(target.getname().toLowerCase()).getMembers();
                        for(int i=0;i<guild.getmembers().size();i++){
                            owners.addPlayer(guild.getmembers().get(i));
                        }
                        regions.getRegion(target.getname().toLowerCase()).setMembers(owners);
                    }
                },12000L);
            return true;
        }
        p.sendMessage(ChatColor.RED + "Gildia nie potrzebuje pomocy");
        return true;
    }
}