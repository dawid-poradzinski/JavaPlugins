package com.rootekstudio.Events;

import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e){
        e.getPlayer().setCustomName(ChatColor.GREEN+"["+SettingsManager.getPlayers().getString(e.getPlayer().getName()+".name")+"]"+e.getPlayer().getName());
        e.getPlayer().setCustomNameVisible(true);
    }
}