package com.rootekstudio.Commands;


import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllyAns {
    
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if(args.length != 1){
            sender.sendMessage(ChatColor.RED + "/gl allyaccept || /gl allydeny");
            return true;
        }
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
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
        Guild target = new Guild(AllyGuild.invally.get(guild.getname().toLowerCase()));
        if(target.getally().size() == 2){
            p.sendMessage(ChatColor.RED + "gildia "+target.getname()+" ma już max sojuszników");
            return true;
        }

        ///
        if(args[0].equalsIgnoreCase("allyaccept")){
            guild.setAlly(target.getname().toLowerCase());
            target.setAlly(guild.getname().toLowerCase());
            SettingsManager.saveGuilds();
            for(int i=0;i<guild.getmembers().size();i++){
                if(Bukkit.getPlayer(guild.getmembers().get(i)) != null){
            Bukkit.getPlayer(guild.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Twoja gildia zawarła sojusz z: "+target.getname());
            }
            }
            for(int i=0;i<target.getmembers().size();i++){
                if(Bukkit.getPlayer(target.getmembers().get(i)) != null){
            Bukkit.getPlayer(target.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Twoja gildia zawarła sojusz z: "+guild.getname());    
            }
            } 
            AllyGuild.invally.put(guild.getname(),null);
            return true;
        }else if(args[0].equalsIgnoreCase("deny")){
            p.sendMessage(ChatColor.RED + "Odrzuciłeś sojusz z gildią: "+guild.getname());
            Bukkit.getPlayer(target.getowner()).sendMessage(ChatColor.RED + "Gildia "+guild.getname()+" odrzuciła twoje zaproszenie");
            AllyGuild.invally.put(guild.getname(),null);
            return true;
        }
        
        return true;
    }
}