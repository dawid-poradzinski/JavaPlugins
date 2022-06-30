package com.rootekstudio.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventMu implements Listener {

    Commands plugin;

    public EventMu(Commands plugin) {
        this.plugin = plugin;
    }
	@EventHandler
	public void onPlayerCgat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(Commands.muted.contains(p.getName())) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED+"Jesteś zmutowany");
		}
	}
}