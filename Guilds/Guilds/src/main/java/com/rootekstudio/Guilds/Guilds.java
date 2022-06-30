package com.rootekstudio.Guilds;

import java.io.File;

import com.rootekstudio.Commands.GlChat;
import com.rootekstudio.Events.HeartDestroy;
import com.rootekstudio.Events.PlayerAttack;
import com.rootekstudio.Events.TabCompletr;
import com.rootekstudio.Recipe.BreakRecipe;
import com.rootekstudio.Recipe.ClickRecipe;
import com.rootekstudio.Recipe.PlaceRecipe;
import com.rootekstudio.Recipe.RecipeCraft;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class Guilds extends JavaPlugin 
{
    private static Guilds instance;
    public static Guilds getInstance(){
        return instance;
    }
    public static File perm;
    public static FileConfiguration permconf;
    @Override
    public void onEnable(){
        SettingsManager.setup();
        SettingsManager.reloadGuilds();
        SettingsManager.saveGuilds();
        SettingsManager.reloadPlayers();
        SettingsManager.savePlayers();
        instance = this;
        Recipe.RecipeStart();
        perm = new File("plugins/Commands/permissions.yml");
        permconf = YamlConfiguration.loadConfiguration(perm);
        this.getCommand("gl").setExecutor(new Menu(this));
        this.getCommand("gl").setTabCompleter(new TabCompletr(this));
        this.getCommand("glchat").setExecutor(new GlChat());
        Bukkit.getServer().getPluginManager().registerEvents(new RecipeCraft(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlaceRecipe(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BreakRecipe(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickRecipe(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HeartDestroy(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerAttack(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BreakRecipe(this), this);
    }
}
