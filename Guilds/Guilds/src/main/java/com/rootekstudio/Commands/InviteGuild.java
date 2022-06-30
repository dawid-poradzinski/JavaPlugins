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


public class InviteGuild {
    static HashMap<Player, String> inv = new HashMap<Player, String>();
    static Guilds plugin;

    public InviteGuild(Guilds plugin) {
        InviteGuild.plugin = plugin;
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
            p.sendMessage(ChatColor.RED + "/gl invite <gracz>");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        if (!p.getName().equals(guild.getowner())) {
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[1]);
        if (target == null) {
            p.sendMessage(ChatColor.RED + "Nie można odnaleźć gracza");
            return true;
        }
        if (SettingsManager.getPlayers().get(target.getName() + ".name") != null) {
            p.sendMessage(ChatColor.RED + "Gracz jest już w gildi");
            return true;
        }
        if(inv.get(target.getPlayer()) != null){
            p.sendMessage(ChatColor.RED + "Gracz posiada aktualnie zaproszenie");
            return true;
        }
        if(guild.getattack() || guild.getdefense()){
            p.sendMessage(ChatColor.RED + "Twoja gildia jest aktualnie w czasie wojny");
            return true;
        }
        p.sendMessage(ChatColor.GREEN + "Zaprosiłeś gracza: "+target.getName()+" do gildi");
        target.sendMessage(ChatColor.GOLD + "Zostałeś zaproszony do gildi: "+guild.getname());
        inv.put(target.getPlayer(), guild.getname());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable() {

			@Override
			public void run() {
				inv.put(target.getPlayer(), null);
			}
        },200);
        return true;
    }
}