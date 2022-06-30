package com.rootekstudio.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsManager {

    private static Commands plugin = Commands.getPlugin(Commands.class);
    public static FileConfiguration cordscfg;
    public static File cordsfile;
    public static FileConfiguration permcfg;
    public static File permfile;
    public static FileConfiguration logincfg;
    public static File loginfile;

    public static void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        cordsfile = new File(plugin.getDataFolder(), "cords.yml");
        permfile = new File(plugin.getDataFolder(), "permissions.yml");
        loginfile = new File(plugin.getDataFolder(), "logowanie.yml");
        if(!cordsfile.exists()){
            try{
                cordsfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Udało się swtorzyć plik");
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udało się stworzyć pliku");
            }
        }
        cordscfg = YamlConfiguration.loadConfiguration(cordsfile);
        if(!permfile.exists()){
            try{
                permfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Udało się swtorzyć plik");
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udało się stworzyć pliku");
            }
        }
        permcfg = YamlConfiguration.loadConfiguration(permfile);
        if(!loginfile.exists()){
            try{
                loginfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Udało się swtorzyć plik");
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udało się stworzyć pliku");
            }
        }
        logincfg = YamlConfiguration.loadConfiguration(loginfile);
    }
    

    public static FileConfiguration getCords(){
        return cordscfg;
    }
    public static void saveCord(){
        try
        {
            cordscfg.save(cordsfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
    public static void reloadCords(){
        cordscfg = YamlConfiguration.loadConfiguration(cordsfile);
    }
    

     public static FileConfiguration getPerm(){
        return permcfg;
    }
    public static void savePerm(){
        try
        {
            permcfg.save(permfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
    public static void reloadPerm(){
        permcfg = YamlConfiguration.loadConfiguration(permfile);
    }
    public static FileConfiguration getLogin(){
        return logincfg;
    }
    public static void saveLogin(){
        try
        {
            logincfg.save(loginfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
    public static void reloadLogin(){
        logincfg = YamlConfiguration.loadConfiguration(loginfile);
    }
}