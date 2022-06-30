package com.rootekstudio.Events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rootekstudio.Guilds.Guilds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompletr implements TabCompleter {
    Guilds plugin;
    WarpList type;

    public TabCompletr(Guilds plugin) {
        this.plugin = plugin;
    }


    public enum WarpList{
        help, create, invite, accept, deny, kick, destroy, setowner, leave, guild, ally, allyaccept, allydeny, breakally, war, joinwar
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (cmd.getName().equalsIgnoreCase("gl")) {
                try{
                    type = WarpList.valueOf(args[0]);
                }
                    catch (Exception e) {
                }
                    if (args.length == 1) {
                            ArrayList<String> WarpLists = new ArrayList<String>();
                           
                            if (!args[0].equals("")) {
                                    for (WarpList type : WarpList.values()) {
                                            if (type.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                                                    WarpLists.add(type.name());
                                            }
                                    }
                            }
                           
                            else {
                                    for (WarpList type : WarpList.values()) {
                                                WarpLists.add(type.name());
                                    }
                            }
                           
                            Collections.sort(WarpLists);
                           
                            return WarpLists;
                    }
                }
                return null;
        }
}