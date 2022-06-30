package com.rootekstudio.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventFr implements Listener{

    Commands plugin;

    public EventFr(Commands plugin){
        this.plugin = plugin;
    }
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(Commands.freeze.contains(p.getName())) {
			e.setTo(e.getFrom());
			p.sendMessage(ChatColor.RED+"Nie możesz się ruszać");
		}
	}
}