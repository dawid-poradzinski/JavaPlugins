package com.rootekstudio.Recipe;

import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.Recipe;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlaceRecipe implements Listener{
    int i;
    Guilds p;
    public PlaceRecipe(Guilds p){
        this.p=p;

    }
    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e){
        String w = e.getBlockPlaced().getWorld().getName();
        Player p = e.getPlayer();
        if(e.getItemInHand().getItemMeta().equals(shmeta)||
           e.getItemInHand().getItemMeta().equals(pxmeta)||
           e.getItemInHand().getItemMeta().equals(swmeta)||
           e.getItemInHand().getItemMeta().equals(axmeta)
        ){
            if(!w.equals("world")){
                p.sendMessage(ChatColor.GOLD + "Aktualny świat: "+w);
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "jest niedpowiednim światem");
                return;
            }
            if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
                p.sendMessage(ChatColor.RED + "Nie masz uprawnień");
                e.setCancelled(true);
                return;
            }
            String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();
            if(!SettingsManager.getGuilds().get(guilds+".cech").equals("miecznik")){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Masz nieodpowiedni cech");
                return;
            }
            if(!SettingsManager.getGuilds().getString(guilds+".owner").equals(p.getName())){
                p.sendMessage(ChatColor.RED + "Tylko właściciele gildi mogą stawiać ten block");
                e.setCancelled(true);
                return;
            }
            if(e.getItemInHand().getItemMeta().equals(shmeta)){
                i = 1;
            }
            else if(e.getItemInHand().getItemMeta().equals(pxmeta)){
                i=2;
            }
            else if( e.getItemInHand().getItemMeta().equals(swmeta)){
                i=3;
            }
            else{
                i=4;
            }
            Double x = SettingsManager.getGuilds().getDouble(guilds+".heart.x");
            Double x1 = x+1;
            Double x2 = x-1;
            Double y = SettingsManager.getGuilds().getDouble(guilds+".heart.y");
            Double z = SettingsManager.getGuilds().getDouble(guilds+".heart.z");
            Double z1 = z+1;
            Double z2 = z-1;

            Double px = e.getBlockPlaced().getLocation().getX();
            Double py = e.getBlockPlaced().getLocation().getY();
            Double pz = e.getBlockPlaced().getLocation().getZ();
            String pw = e.getBlockPlaced().getLocation().getWorld().getName();

            switch(i){
                case 1:{
                    if(SettingsManager.getGuilds().get(guilds+".shovelblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".shovelblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".shovelblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".shovelblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".shovelblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".shovelblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 2:{
                    if(SettingsManager.getGuilds().get(guilds+".picaxeblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".picaxeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".shovelblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".picaxeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".picaxeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".picaxeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 3:{
                    if(SettingsManager.getGuilds().get(guilds+".swordblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".swordblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".swordblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".swordblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".swordblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".swordblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".swordblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".swordblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".swordblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".swordblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 4:{
                    if(SettingsManager.getGuilds().get(guilds+".axeblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".axeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".axeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".axeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".axeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".axeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".axeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".axeblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".axeblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".axeblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
            }

        }
        //ANOTHER ONE
        else if(
            e.getItemInHand().getItemMeta().equals(hlmeta)||
           e.getItemInHand().getItemMeta().equals(cpmeta)||
           e.getItemInHand().getItemMeta().equals(lgmeta)||
           e.getItemInHand().getItemMeta().equals(btmeta)
        ){
            if(!w.equals("world")){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Nieodpowiedni świat");
                return;
            }
            if(SettingsManager.getPlayers().get(p.getName()+".name") == null){
                p.sendMessage(ChatColor.RED + "Nie masz uprawnień");
                e.setCancelled(true);
                return;
            }
            String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();
            if(!SettingsManager.getGuilds().get(guilds+".cech").equals("zbrojmistrz")){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Masz nieodpowiedni cech");
                return;
            }
            if(!SettingsManager.getGuilds().getString(guilds+".owner").equals(p.getName())){
                p.sendMessage(ChatColor.RED + "Tylko właściciele gildi mogą stawiać ten block");
                e.setCancelled(true);
                return;
            }
            if(e.getItemInHand().getItemMeta().equals(hlmeta)){
                i = 1;
            }
            else if(e.getItemInHand().getItemMeta().equals(cpmeta)){
                i=2;
            }
            else if( e.getItemInHand().getItemMeta().equals(lgmeta)){
                i=3;
            }
            else{
                i=4;
            }
            Double x = SettingsManager.getGuilds().getDouble(guilds+".heart.x");
            Double x1 = x+1;
            Double x2 = x-1;
            Double y = SettingsManager.getGuilds().getDouble(guilds+".heart.y");
            Double z = SettingsManager.getGuilds().getDouble(guilds+".heart.z");
            Double z1 = z+1;
            Double z2 = z-1;

            Double px = e.getBlockPlaced().getLocation().getX();
            Double py = e.getBlockPlaced().getLocation().getY();
            Double pz = e.getBlockPlaced().getLocation().getZ();
            String pw = e.getBlockPlaced().getLocation().getWorld().getName();

            switch(i){
                case 1:{
                    if(SettingsManager.getGuilds().get(guilds+".helmblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".helmblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".helmblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".helmblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".helmblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".helmblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".helmblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".helmblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".helmblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".helmblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else{
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 2:{
                    if(SettingsManager.getGuilds().get(guilds+".chestplateblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".chestplateblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".chestplateblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".chestplateblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".chestplateblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".chestplateblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else{
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 3:{
                    if(SettingsManager.getGuilds().get(guilds+".legginsblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".legginsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".legginsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".legginsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".legginsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".legginsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else{
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
                case 4:{
                    if(SettingsManager.getGuilds().get(guilds+".bootsblock") != null){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED +"Juz macie to ulepszenie");
                        return;
                    }
                    else
                    {
                        if(px.equals(x) && py.equals(y) && pz.equals(z1)){
                            SettingsManager.getGuilds().set(guilds+".bootsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.z", pz);
                                SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x) && py.equals(y) && pz.equals(z2)){
                            SettingsManager.getGuilds().set(guilds+".bootsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x1) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".bootsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }else if(px.equals(x2) && py.equals(y) && pz.equals(z)){
                            SettingsManager.getGuilds().set(guilds+".bootsblock", "tak");
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.w", pw);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.x", px);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.y", py);
                                    SettingsManager.getGuilds().set(guilds+".bootsblock.z", pz);
                                    SettingsManager.saveGuilds();
                                return;
                        }
                        else{
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED +"Niewłaściwe położenie bloku");
                            }
                            return;
                    }
                }
            }

        }
    }

    
    ItemStack shresult = Recipe.shovelrecipe.getResult();
    ItemStack pxresult = Recipe.picaxerecipe.getResult();
    ItemStack swresult = Recipe.swordrecipe.getResult();
    ItemStack axresult = Recipe.axerecipe.getResult();
    //Armmor
    ItemStack hlresult = Recipe.helmrecipe.getResult();
    ItemStack cpresult = Recipe.chestplaterecipe.getResult();
    ItemStack lgresult = Recipe.legginsrecipe.getResult();
    ItemStack btresult = Recipe.bootsrecipe.getResult();

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
}   