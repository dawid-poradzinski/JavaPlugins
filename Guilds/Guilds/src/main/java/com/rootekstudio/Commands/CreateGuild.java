package com.rootekstudio.Commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Menu;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGuild{
    Menu plugin;
    public CreateGuild(Menu plugin){
        this.plugin=plugin;
    }
    

    
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(!(sender instanceof Player)){
           sender.sendMessage(ChatColor.RED + "Musisz być graczem");
           return true;
       }
       Player p = (Player) sender;
       if(args.length != 3){
           sender.sendMessage(ChatColor.RED + "/gl create <nazwa> <cech>");
           return true;
       }
       if(SettingsManager.getPlayers().get(p.getName()+".name") != null){
           p.sendMessage(ChatColor.RED + "Nie możesz być w gildi");
           return true;
       }
       if(args[1].contains(".") || args[1].contains(",") || args[1].contains(">") || args[1].contains("<") || args[1].contains("&") || args[1].contains("*")|| args[1].contains("_")|| args[1].contains("-")){
        sender.sendMessage(ChatColor.RED + "Nazwa nie może posiadać znaków specjalnych");
        return true;
       }
       if(SettingsManager.getGuilds().get(args[1].toLowerCase()) != null){
        p.sendMessage(ChatColor.RED + "Gildia o takiej nazwie już istnieje");
        return true;
       }
       if(!(args[2].equals("miecznik") || args[2].equals("zbrojmistrz"))){
            sender.sendMessage(ChatColor.RED + "Podano zły cech");
            sender.sendMessage(ChatColor.RED + "Wprowadź miecznik || zbrojmistrz");
            return true;
       }
       if(!p.getInventory().contains(Material.DIAMOND_BLOCK, 1) ||
          !p.getInventory().contains(Material.GOLD_BLOCK, 1) ||
          !p.getInventory().contains(Material.EMERALD_BLOCK, 1) ||
          !p.getInventory().contains(Material.IRON_BLOCK, 1) ||
          !p.getInventory().contains(Material.LAPIS_BLOCK, 1) ||
          !p.getInventory().contains(Material.REDSTONE_BLOCK, 1)){
            sender.sendMessage(ChatColor.RED + "/gl help");
            return true;
        }
        List<ProtectedRegion> overlapping;
        BlockVector3 min = BlockVector3.at(p.getLocation().getX()-50, 256,p.getLocation().getZ()-50);
        BlockVector3 max = BlockVector3.at(p.getLocation().getX()+50, 0,p.getLocation().getZ()+50);
        ProtectedCuboidRegion region = new ProtectedCuboidRegion(args[1], min, max);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));
        DefaultDomain owners = new DefaultDomain();
        //LOOKING IF OVERLAP
        ApplicableRegionSet set = regions.getApplicableRegions(region);
        List<ProtectedRegion> candidates = Lists.newArrayList(set);
        owners.addPlayer(p.getName());
        region.setMembers(owners);
        region.setOwners(owners);
        region.setFlag(Flags.GREET_MESSAGE, "Wszedłeś na teren gildi: "+ChatColor.RED + args[1]);
        regions.addRegion(region);
        overlapping = region.getIntersectingRegions(candidates);
        if(overlapping.size() > 0){
            sender.sendMessage(ChatColor.RED + "Inna gildia posiada niedaleko cuboid");
            regions.removeRegion(args[0]);
            return true;
        }

       Guild guild = new Guild(args[1].toLowerCase());
       SettingsManager.getGuilds().set(args[1].toLowerCase()+".name", args[1]);
       SettingsManager.getGuilds().set(args[1].toLowerCase()+".hearts", 5);
       guild.setLv(1);
       guild.setOwner(p.getName());
       guild.setWeek(false);
       guild.setGuard(false);
       guild.setAttack(false);
       guild.setDefense(false);
       guild.setCech(args[2]);
       SettingsManager.getGuilds().set(args[1].toLowerCase()+".nextlv", 100);
       SettingsManager.getGuilds().set(args[1].toLowerCase()+".xp", 0);
       List<String> s = new ArrayList<String>();
       SettingsManager.getGuilds().set(args[1].toLowerCase()+".ally", s);
        guild.setMembers(p.getName());


       Date now = new Date();
       LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                    
            // convert LocalDateTime to date
            Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            ;
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //If you need time just put specific format for time like 'HH:mm:ss'
            String dateStr = formatter.format(currentDatePlusOneDay);
            SettingsManager.getPlayers().set(p.getName()+".name",args[1]);
            SettingsManager.getPlayers().set(p.getName()+".czas.picaxe",dateStr);
            SettingsManager.getPlayers().set(p.getName()+".czas.axe",dateStr);
            SettingsManager.getPlayers().set(p.getName()+".czas.shovel",dateStr);
            SettingsManager.getPlayers().set(p.getName()+".czas.sword",dateStr);
            SettingsManager.savePlayers();


            p.getLocation().add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
            SettingsManager.getGuilds().set(args[1].toLowerCase()+".heart.w", p.getLocation().getWorld().getName());
            SettingsManager.getGuilds().set(args[1].toLowerCase()+".heart.x", p.getLocation().add(0,-1,0).getBlockX());
            SettingsManager.getGuilds().set(args[1].toLowerCase()+".heart.y", p.getLocation().add(0, -1, 0).getBlockY());
            SettingsManager.getGuilds().set(args[1].toLowerCase()+".heart.z", p.getLocation().add(0,-1,0).getBlockZ());
            SettingsManager.saveGuilds();
            p.sendMessage(ChatColor.GREEN + "Utworzyłeś Gildie: "+args[1]);
        return true;
    }

}