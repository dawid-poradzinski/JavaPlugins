package com.rootekstudio.Events;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttack implements Listener {

    Guilds plugin;
    public PlayerAttack(Guilds plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player target = (Player) e.getEntity();
            Player p = (Player) e.getDamager();
            if(SettingsManager.getPlayers().get(p.getName()+".name") == null || SettingsManager.getPlayers().get(target.getName()+".name") == null){
                return;
            }
            Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name"));
            Guild guildtarget = new Guild(SettingsManager.getPlayers().getString(target.getName()+".name"));
            if(guild.getname().equals(guildtarget.getname())){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Friendly Fire jest wyłączony");
                return;
            }else if(guild.getally().contains(guildtarget.getname().toLowerCase())){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Friendly Fire jest wyłączony");
                return;
            }
        }   
       }
}