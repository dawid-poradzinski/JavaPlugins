package com.rootekstudio.Commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands extends JavaPlugin implements Listener {
    static Plugin plugin;

    public static ArrayList<String> freeze = new ArrayList<String>();
    public static ArrayList<String> muted = new ArrayList<String>();
    public static ArrayList<String> warp = new ArrayList<String>();
    public static ArrayList<String> vanish = new ArrayList<String>();
    public static ArrayList<String> logowanie = new ArrayList<String>();
    public static HashMap<Player, Player> tp = new HashMap<Player, Player>();
    public static HashMap<Player, Player> msg = new HashMap<Player, Player>();

    public void onEnable() {
        loadConfigManager();
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(this, this);
        plugin = this;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getCommand("freeze").setExecutor(new CommandFr(this));
        this.getCommand("unfreeze").setExecutor(new CommandFr(this));
        this.getCommand("sethome").setExecutor(new CommandWa(this));
        this.getCommand("home").setExecutor(new CommandWa(this));
        this.getCommand("mute").setExecutor(new CommandMu(this));
        this.getCommand("unmute").setExecutor(new CommandMu(this));
        this.getCommand("tpa").setExecutor(new CommandTp(this));
        this.getCommand("tpaccept").setExecutor(new CommandTa(this));
        this.getCommand("tpaccept").setTabCompleter(new TabExe(this));
        this.getCommand("tpdeny").setExecutor(new CommandTa(this));
        this.getCommand("tpdeny").setTabCompleter(new TabExe(this));
        this.getCommand("setwarp").setExecutor(new CommandWa(this));
        this.getCommand("warp").setExecutor(new CommandWa(this));
        this.getCommand("warp").setTabCompleter(new TabExe(this));
        this.getCommand("warplist").setExecutor(new CommandWa(this));
        this.getCommand("msg").setExecutor(new CommandMsg(this));
        this.getCommand("r").setExecutor(new CommandMsg(this));
        this.getCommand("r").setTabCompleter(new TabExe(this));
        this.getCommand("vanish").setExecutor(new CommandV(this));
        this.getCommand("v").setExecutor(new CommandV(this));
        this.getCommand("spawn").setExecutor(new CommandWa(this));
        this.getCommand("spawn").setTabCompleter(new TabExe(this));
        this.getCommand("gm").setExecutor(new CommandGm(this));
        this.getCommand("register").setExecutor(new LogIn(this));
        this.getCommand("register").setTabCompleter(new TabExe(this));
        this.getCommand("login").setExecutor(new LogIn(this));
        this.getCommand("login").setTabCompleter(new TabExe(this));
		Bukkit.getServer().getPluginManager().registerEvents(new EventFr(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EventMu(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LogIn(this), this);
        
    }


    public void loadConfigManager(){
        SettingsManager.setup();
        SettingsManager.saveCord();
        SettingsManager.reloadCords();
        SettingsManager.savePerm();
        SettingsManager.reloadPerm();
        SettingsManager.saveLogin();
        SettingsManager.reloadLogin();
    }
}
