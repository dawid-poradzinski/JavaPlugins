package com.rootekstudio.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabExe implements TabCompleter{
    Commands plugin;
    WarpList type;
    LogIn t;
    public TabExe(Commands plugin){
        this.plugin = plugin;
    }


    public enum WarpList{
        sklep, swiat, arena,
    }

    public enum LogIn{
         ,
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (cmd.getName().equalsIgnoreCase("warp")) {
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
            if (cmd.getName().equalsIgnoreCase("login") || cmd.getName().equalsIgnoreCase("register") || cmd.getName().equalsIgnoreCase("tpaccept") || cmd.getName().equalsIgnoreCase("tpdeny")
            || cmd.getName().equalsIgnoreCase("r") || cmd.getName().equalsIgnoreCase("spawn")) {
                try{
                    t = LogIn.valueOf(args[0]);
                }
                    catch (Exception e) {
                }
                    if (args.length == 1) {
                            ArrayList<String> LogInList = new ArrayList<String>();
                           
                            if (!args[0].equals("")) {
                                    for (LogIn t : LogIn.values()) {
                                            if (t.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                                                LogInList.add(t.name());
                                            }
                                    }
                            }
                           
                            else{
                                    for (LogIn t : LogIn.values()) {
                                        LogInList.add(t.name());
                                    }
                            }
                           
                            Collections.sort(LogInList);
                           
                            return LogInList;
                    }
            }
            return null;
    }
}