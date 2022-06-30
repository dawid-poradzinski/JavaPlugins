package com.rootekstudio.Guilds;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsManager {

    private static Guilds plugin = Guilds.getPlugin(Guilds.class);
    public static FileConfiguration Guildscfg;
    public static File Guildsfile;
    public static FileConfiguration Playerscfg;
    public static File Playersfile;


    public static void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        Guildsfile = new File(plugin.getDataFolder(), "Guilds.yml");
        Playersfile = new File(plugin.getDataFolder(), "Players.yml");

        if(!Guildsfile.exists()){
            try{
                Guildsfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Udało się swtorzyć plik");
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udało się stworzyć pliku");
            }
        }
        Guildscfg = YamlConfiguration.loadConfiguration(Guildsfile);

        if(!Playersfile.exists()){
            try{
                Playersfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Udało się swtorzyć plik");
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udało się stworzyć pliku");
            }
        }
        Playerscfg = YamlConfiguration.loadConfiguration(Playersfile);
    }

    public static FileConfiguration getGuilds(){
        return Guildscfg;
    }
    public static void saveGuilds(){
        try
        {
            Guildscfg.save(Guildsfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
    public static void reloadGuilds(){
        Guildscfg = YamlConfiguration.loadConfiguration(Guildsfile);
    }
    



    public static FileConfiguration getPlayers(){
        return Playerscfg;
    }
    public static void savePlayers(){
        try
        {
            Playerscfg.save(Playersfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
    public static void reloadPlayers(){
        Playerscfg = YamlConfiguration.loadConfiguration(Playersfile);
    }
}