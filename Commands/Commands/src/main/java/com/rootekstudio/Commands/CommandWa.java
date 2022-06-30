package com.rootekstudio.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWa implements CommandExecutor{
    Commands plugin;
    public CommandWa(Commands plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("warplist")){
            List<String> s = Commands.getPlugin(Commands.class).getConfig().getStringList("warp");
                sender.sendMessage(ChatColor.GOLD +"Lista dostępnych warpów: "+s);
            return true;
        }
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(SettingsManager.getPerm().getString(p.getName()+".commands").equals("nie")){
                sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("sethome")){
                SettingsManager.getCords().set(p.getName()+"agas34aefdas.world", p.getLocation().getWorld().getName());
                        SettingsManager.getCords().set(p.getName()+"agas34aefdas.x", p.getLocation().getX());
                        SettingsManager.getCords().set(p.getName()+"agas34aefdas.y", p.getLocation().getY());
                        SettingsManager.getCords().set(p.getName()+"agas34aefdas.z", p.getLocation().getZ());
                        SettingsManager.getCords().set(p.getName()+"agas34aefdas.pitch", p.getLocation().getPitch());
                        SettingsManager.getCords().set(p.getName()+"agas34aefdas.yaw", p.getLocation().getYaw());
                        SettingsManager.saveCord();
                        sender.sendMessage(ChatColor.GREEN + "Utworzyłeś Home");
                        return true;
            }
            else if(cmd.getName().equalsIgnoreCase("home")){
                World w = Bukkit.getServer().getWorld(SettingsManager.getCords().getString(p.getName()+"agas34aefdas.world"));
                double x = SettingsManager.getCords().getDouble(p.getName()+"agas34aefdas.x");
                double y = SettingsManager.getCords().getDouble(p.getName()+"agas34aefdas.y");
                double z = SettingsManager.getCords().getDouble(p.getName()+"agas34aefdas.z");
                p.teleport(new Location(w,x,y,z));
                return true;
            }
            else if(cmd.getName().equalsIgnoreCase("spawn")){
                World w = Bukkit.getServer().getWorld(SettingsManager.getCords().getString("spawn.world"));
                double x = SettingsManager.getCords().getDouble("spawn.x");
                double y = SettingsManager.getCords().getDouble("spawn.y");
                double z = SettingsManager.getCords().getDouble("spawn.z");
                p.teleport(new Location(w,x,y,z));
                return true;
                }
            if(args.length == 0){
                sender.sendMessage(ChatColor.RED + "Musisz podać nazwę");
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("setwarp")){
                if(SettingsManager.getPerm().getString(p.getName()+".admin").equals("nie")){
                    sender.sendMessage(ChatColor.RED + "Nie masz uprawnień");
                    return true;
                }
                        SettingsManager.getCords().set(args[0]+".world", p.getLocation().getWorld().getName());
                        SettingsManager.getCords().set(args[0]+".x", p.getLocation().getX());
                        SettingsManager.getCords().set(args[0]+".y", p.getLocation().getY());
                        SettingsManager.getCords().set(args[0]+".z", p.getLocation().getZ());
                        SettingsManager.getCords().set(args[0]+".pitch", p.getLocation().getPitch());
                        SettingsManager.getCords().set(args[0]+".yaw", p.getLocation().getYaw());
                        SettingsManager.saveCord();
                        sender.sendMessage(ChatColor.GREEN + "Utworzyłeś warpa: " + args[0]);
                        List<String> s = Commands.getPlugin(Commands.class).getConfig().getStringList("warp");
                        s.add(args[0]);
                        Commands.getPlugin(Commands.class).getConfig().set("warp", s);
                        Commands.getPlugin(Commands.class).saveConfig();
                        return true;
            }
            else
            {
                if(SettingsManager.getCords().getConfigurationSection(args[0]) == null || args[0].equals("dayidagas34aefdas")) {
                    sender.sendMessage(ChatColor.RED + "Nie istnieje taki warp");
                    return true;
                }
                World w = Bukkit.getServer().getWorld(SettingsManager.getCords().getString(args[0]+".world"));
                double x = SettingsManager.getCords().getDouble(args[0]+".x");
                double y = SettingsManager.getCords().getDouble(args[0]+".y");
                double z = SettingsManager.getCords().getDouble(args[0]+".z");
                p.teleport(new Location(w,x,y,z));
                sender.sendMessage(ChatColor.GREEN + "Przeteleportowałeś się do: " + args[0]);
                return true;
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
    }
}