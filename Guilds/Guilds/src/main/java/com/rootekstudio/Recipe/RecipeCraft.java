package com.rootekstudio.Recipe;

import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.Recipe;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipeCraft implements Listener {
    Guilds plugin;

    public RecipeCraft(Guilds p) {
        this.plugin = p;
    }
    //Items
    ItemStack shresult = Recipe.shovelrecipe.getResult();
    ItemStack pxresult = Recipe.picaxerecipe.getResult();
    ItemStack swresult = Recipe.swordrecipe.getResult();
    ItemStack axresult = Recipe.axerecipe.getResult();
    //Armmor
    ItemStack hlresult = Recipe.helmrecipe.getResult();
    ItemStack cpresult = Recipe.chestplaterecipe.getResult();
    ItemStack lgresult = Recipe.legginsrecipe.getResult();
    ItemStack btresult = Recipe.bootsrecipe.getResult();

     int i;
    // //Items meta
     ItemMeta shmeta = shresult.getItemMeta();
    ItemMeta pxmeta = pxresult.getItemMeta();
    ItemMeta swmeta = swresult.getItemMeta();
    ItemMeta axmeta = axresult.getItemMeta();
    //Arrmor meta
    ItemMeta hlmeta = hlresult.getItemMeta();
    ItemMeta cpmeta = cpresult.getItemMeta();
    ItemMeta lgmeta = lgresult.getItemMeta();
    ItemMeta btmeta = btresult.getItemMeta();

//Check if the meta's lore and/or display name is equal to the custom stick
    @EventHandler
    public void CraftEvent(CraftItemEvent e){
        Player p = (Player) e.getWhoClicked();
        if( e.getRecipe().getResult().getItemMeta().equals(shmeta) ||
            e.getRecipe().getResult().getItemMeta().equals(pxmeta) ||
            e.getRecipe().getResult().getItemMeta().equals(swmeta)||
            e.getRecipe().getResult().getItemMeta().equals(axmeta)
        )
        {
            if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
                p.sendMessage(ChatColor.RED + "Nie jesteÅ› w Gildi");
                e.setCancelled(true);
                return;
            }
            String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();
            if(!SettingsManager.getGuilds().getString(guilds+".cech").equals("miecznik")){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Nie jesteÅ› w odpowiednim cechu");
                return;
            }
            //Settler of int of a somethginfg
            if(e.getRecipe().getResult().getItemMeta().equals(shmeta)){
                i = 1;
            }else if(e.getRecipe().getResult().getItemMeta().equals(pxmeta)){
                i = 2;
            }else if(e.getRecipe().getResult().getItemMeta().equals(swmeta)){
                i = 3;
            }else{
                i = 4;
            }

            switch(i){
                case 1:
                {
                    if(SettingsManager.getGuilds().get(guilds+".shovelblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                }
                case 2:
                {
                    if(SettingsManager.getGuilds().get(guilds+".picaxeblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                }
                case 3:
                {
                    if(SettingsManager.getGuilds().get(guilds+".swordblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                }
                case 4:
                {
                    if(SettingsManager.getGuilds().get(guilds+".axeblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                }
            }

        }else if( e.getRecipe().getResult().getItemMeta().equals(hlmeta) ||
            e.getRecipe().getResult().getItemMeta().equals(cpmeta) ||
            e.getRecipe().getResult().getItemMeta().equals(lgmeta) ||
            e.getRecipe().getResult().getItemMeta().equals(btmeta))
            {
                if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
                    p.sendMessage(ChatColor.RED + "Nie jesteÅ› w Gildi");
                    e.setCancelled(true);
                    return;
                }
                String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();
                if(!SettingsManager.getGuilds().getString(guilds+".cech").equals("zbrojmistrz")){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Masz nieodpowiedni cech");
            return;
        }
            if(e.getRecipe().getResult().getItemMeta().equals(hlmeta)){
                i = 1;
            }else if(e.getRecipe().getResult().getItemMeta().equals(cpmeta)){
                i = 2;
            }else if(e.getRecipe().getResult().getItemMeta().equals(lgmeta)){
                i = 3;
            }else{
                i = 4;
            }

                switch(i){
                    case 1:{
                        if(SettingsManager.getGuilds().get(guilds+".helmblock") != null){
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                            return;
                        }
                    }
                    case 2:{
                        if(SettingsManager.getGuilds().get(guilds+".chestplateblock") != null){
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                            return;
                        }
                    }
                    case 3:{
                        if(SettingsManager.getGuilds().get(guilds+".legginsblock") != null){
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                            return;
                        }
                    }
                    case 4:{
                        
                        if(SettingsManager.getGuilds().get(guilds+".bootsblock") != null){
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                            return;
                    }
                }
            }
        }
    }
}