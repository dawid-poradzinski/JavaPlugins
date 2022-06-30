package com.rootekstudio.Commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

public class InvAns {
    
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        if(args.length != 1){
            sender.sendMessage(ChatColor.RED + "/gl accept || /gl deny");
            return true;
        }
        Player p = (Player) sender;
        if (SettingsManager.getPlayers().get(p.getName() + ".name") != null) {
            p.sendMessage(ChatColor.RED + "Jesteś już w gildi");
            return true;
        }
        if(InviteGuild.inv.get(p.getPlayer()) == null){
            p.sendMessage(ChatColor.RED + "Nie masz żadnego zaproszenia");
            return true;
        }
        Guild guild = new Guild(InviteGuild.inv.get(p.getPlayer()));
        if(args[0].equalsIgnoreCase("accept")){
            guild.setMembers(p.getName());
            SettingsManager.saveGuilds();
            SettingsManager.getPlayers().set(p.getName()+".name",guild.getname());
            if(SettingsManager.getPlayers().get(p.getName()+".czas") == null){
                Date now = new Date();
                LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                             
                     // convert LocalDateTime to date
                     Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                     ;
                     DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //If you need time just put specific format for time like 'HH:mm:ss'
                     String dateStr = formatter.format(currentDatePlusOneDay);
                     SettingsManager.getPlayers().set(p.getName()+".czas.picaxe",dateStr);
                     SettingsManager.getPlayers().set(p.getName()+".czas.axe",dateStr);
                     SettingsManager.getPlayers().set(p.getName()+".czas.shovel",dateStr);
                     SettingsManager.getPlayers().set(p.getName()+".czas.sword",dateStr);
            }
            SettingsManager.savePlayers();

            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
            DefaultDomain owners = regions.getRegion(guild.getname().toLowerCase()).getMembers();
            owners.addPlayer(p.getName());
            regions.getRegion(guild.getname().toLowerCase()).setMembers(owners);

            Bukkit.getPlayer(guild.getowner()).sendMessage(ChatColor.GREEN + "Gracz: "+p.getName() + " dołączył do gildi");
            p.sendMessage(ChatColor.GREEN + "Dołączyłeś do gildi: "+guild.getname());
            InviteGuild.inv.put(p.getPlayer(),null);
            return true;
        }else if(args[0].equalsIgnoreCase("deny")){
            p.sendMessage(ChatColor.RED + "Odrzuciłeś członkostwo w gildi: "+guild.getname());
            InviteGuild.inv.put(p.getPlayer(),null);
            return true;
        }
        return true;
    }
}