package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreakAlly {
    Guilds plugin;
    public BreakAlly(Guilds p){
        this.plugin=p;
    }
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
            p.sendMessage(ChatColor.RED + "/gl breakally <gildia>");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        if (!p.getName().equals(guild.getowner())) {
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem");
            return true;
        }
        if(SettingsManager.getGuilds().get(args[1].toLowerCase()) == null){
            p.sendMessage(ChatColor.RED + "Podana gildia nie istnieje");
            return true;
        }
        if(!guild.getally().contains(args[1])){
            p.sendMessage(ChatColor.RED + "Nie jesteś w sojuszy z: "+args[1]);
            return true;
        }
        Guild target = new Guild(args[1].toLowerCase());
        guild.removeAlly(target.getname().toLowerCase());
        target.removeAlly(guild.getname().toLowerCase());
        SettingsManager.saveGuilds();
            for(int i=0;i<guild.getmembers().size();i++){
                if(Bukkit.getPlayer(guild.getmembers().get(i)) != null){
            Bukkit.getPlayer(guild.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Twoja gildia zerwała sojusz z: "+target.getname());
            }
            }
            for(int i=0;i<target.getmembers().size();i++){
                if(Bukkit.getPlayer(target.getmembers().get(i)) != null){
            Bukkit.getPlayer(target.getmembers().get(i)).sendMessage(ChatColor.GREEN + guild.getname()+" zerwała sojusz z twoją gildią");    
            }
            }       
            return true;
    }
}