package com.rootekstudio.Commands;

import java.util.HashMap;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class AllyGuild {
    static HashMap<String, String> invally = new HashMap<String, String>();
    static Guilds plugin;

    public AllyGuild(Guilds plugin) {
        AllyGuild.plugin = plugin;
    }

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
            p.sendMessage(ChatColor.RED + "/gl ally <gildia>");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        if (!p.getName().equals(guild.getowner())) {
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem");
            return true;
        }
        if(guild.getally().size() == 2){
            p.sendMessage(ChatColor.RED + "Twoja gildia ma już max sojuszników");
            return true;
        }
        if (SettingsManager.getGuilds().get(args[1].toLowerCase()) == null) {
            p.sendMessage(ChatColor.RED + "Nie można odnaleźć gildi");
            return true;
        }
        Guild target = new Guild(args[1].toLowerCase());
        if(target.getally().size() == 2){
            p.sendMessage(ChatColor.RED + "gildia "+args[1]+" ma już max sojuszników");
            return true;
        }
        if(guild.getally().contains(target.getname().toLowerCase())){
            p.sendMessage(ChatColor.RED + " Już masz sojusz z tą gildią");
            return true;
        }
        Player playertarget = Bukkit.getPlayer(target.getowner());
        if(invally.get(target.getname().toLowerCase()) != null){
            p.sendMessage(ChatColor.RED + "Gildia posiada aktualnie zaproszenie");
            return true;
        }
        p.sendMessage(ChatColor.GREEN + "Wysłałeś zaproszenie gildi: "+args[1]+" do sojuszników");
        if(playertarget != null){
        playertarget.sendMessage(ChatColor.GREEN + "Gildia: "+guild.getname()+ " zaprosiła twoją gildię do sojuszników");
        }
        invally.put(target.getname(), guild.getname());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable() {

			@Override
			public void run() {
				invally.put(target.getname(), null);
			}
        },1200);
        return true;
    }
}