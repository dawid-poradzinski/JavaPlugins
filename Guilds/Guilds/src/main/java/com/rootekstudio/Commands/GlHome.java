package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlHome {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "/gl home");
            return true;
        }
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        World w = Bukkit.getWorld("world");
        Double x = SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase() + ".heart." + "x");
        Double y = SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase() + ".heart." + "y");
        Double z = SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase() + ".heart." + "z");
        p.sendMessage(ChatColor.GREEN + "Zostaniesz przeniesony za 3s");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable(){

			@Override
			public void run() {
				p.teleport(new Location(w, x, y, z));
			}
            
        },60L);
        return true;
    }
}