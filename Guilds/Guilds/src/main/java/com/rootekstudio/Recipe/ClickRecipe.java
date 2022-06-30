package com.rootekstudio.Recipe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickRecipe implements Listener {
    Guilds plugin;
    public ClickRecipe(Guilds plugin){
        this.plugin=plugin;
    }
    Date now = new Date();
    Date date = new Date();
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    int item;
    public Player p;
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        p = e.getPlayer();
        if(e.getItem() == null){
            return;
        }
        if(e.getClickedBlock() == null){
            return;
        }
        if(!e.getClickedBlock().getWorld().getName().equals("world")){
            return;
        }
        if(SettingsManager.getPlayers().get(p.getName()+".name") != null){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if(e.getItem().getType() == diamond.getType()){
                    Double cx = e.getClickedBlock().getLocation().getX();
                    Double cy = e.getClickedBlock().getLocation().getY();
                    Double cz = e.getClickedBlock().getLocation().getZ();
                    String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();
                    int i = SettingsManager.getGuilds().getInt(guilds+".lv");
                    if(SettingsManager.getGuilds().getString(guilds+".cech").equals("miecznik")){
                        //PICAXE
                        if(e.getClickedBlock().getType() == Material.SEA_LANTERN){
                            item = 1;
                            if(SettingsManager.getGuilds().get(guilds+".picaxeblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".picaxeblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".picaxeblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".picaxeblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.picaxe"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                                            item.setItemMeta(testEnchantMeta);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
                                            item.setItemMeta(testEnchantMeta);
                                            return;
                                        }
                                    }    
                                }
                                else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //SWORD
                        }else if(e.getClickedBlock().getType() == Material.SMOOTH_QUARTZ){
                            item = 2;
                            if(SettingsManager.getGuilds().get(guilds+".swordblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".swordblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".swordblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".swordblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.sword"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD,1));
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_SWORD,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.SWEEPING_EDGE, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //AXE
                        }else if(e.getClickedBlock().getType() == Material.SMOOTH_RED_SANDSTONE){
                            item = 3;
                            if(SettingsManager.getGuilds().get(guilds+".axeblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".axeblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".axeblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".axeblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.axe"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_AXE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 1,true);
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_AXE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 3,true);
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //SHOVEL
                        }else if(e.getClickedBlock().getType() == Material.SMOOTH_SANDSTONE){
                            item = 4;
                            if(SettingsManager.getGuilds().get(guilds+".shovelblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".shovelblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".shovelblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".shovelblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.shovel"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_SHOVEL,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.DIG_SPEED,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.WATER_WORKER, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                        }//
                    }else if(SettingsManager.getGuilds().getString(guilds+".cech").equals("zbrojmistrz")){
                        //helm
                        if(e.getClickedBlock().getType() == Material.SMOOTH_STONE){
                            item = 1;
                            if(SettingsManager.getGuilds().get(guilds+".helmblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".helmblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".helmblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".helmblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.picaxe"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_HELMET,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true); 
                                            item.setItemMeta(testEnchantMeta);
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_HELMET,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }
                                else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //SWORD
                        }else if(e.getClickedBlock().getType() == Material.STRIPPED_ACACIA_WOOD){
                            item = 2;
                            if(SettingsManager.getGuilds().get(guilds+".chestplateblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".chestplateblock.x")) && 
                                   !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".chestplateblock.y")) &&
                                   !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".chestplateblock.z"))
                                ){
                                    return;
                                }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.sword"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //AXE
                        }else if(e.getClickedBlock().getType() == Material.TUBE_CORAL_BLOCK){
                            item = 3;
                            if(SettingsManager.getGuilds().get(guilds+".legginsblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".legginsblock.x")) && 
                                !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".legginsblock.y")) &&
                                !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".legginsblock.z"))
                             ){
                                 return;
                             }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.axe"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                            //SHOVEL
                        }else if(e.getClickedBlock().getType() == Material.YELLOW_SHULKER_BOX){
                            item = 4;
                            if(SettingsManager.getGuilds().get(guilds+".bootsblock") != null){
                                if(!cx.equals(SettingsManager.getGuilds().getDouble(guilds+".bootsblock.x")) && 
                                !cy.equals(SettingsManager.getGuilds().getDouble(guilds+".bootsblock.y")) &&
                                !cz.equals(SettingsManager.getGuilds().getDouble(guilds+".bootsblock.z"))
                             ){
                                 return;
                             }
                                //DATE
                                try {
                                    date = dateFormatter.parse(SettingsManager.getPlayers().getString(p.getName()+".czas.shovel"));
                                } catch (ParseException x) {
                                    x.printStackTrace();
                                }

                                if(now.after(date)){ 
                                    switch(i){
                                        case 1:{
                                            restart();
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS,1));
                                            return;
                                        }
                                        case 2:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_BOOTS,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,1,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 2,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true); 
                                            item.setItemMeta(testEnchantMeta);                   
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                        case 3:{
                                            restart();
                                            ItemStack item = new ItemStack(Material.DIAMOND_BOOTS,1);
                                            ItemMeta testEnchantMeta = item.getItemMeta();
                                            testEnchantMeta.addEnchant(Enchantment.THORNS,3,true);
                                            testEnchantMeta.addEnchant(Enchantment.DURABILITY, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_FIRE, 3,true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, true);
                                            testEnchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true); 
                                            item.setItemMeta(testEnchantMeta); 
                                            p.getInventory().remove(new ItemStack(Material.DIAMOND, 1));
                                            p.getInventory().addItem(item);
                                            return;
                                        }
                                    }    
                                }else{
                                    p.sendMessage(ChatColor.RED + "Nie możesz jeszcze wytworzyć przedmiotu");
                                    return;
                                }
                            }
                        }//
                    }
                }
            }
        }
    }

    public void restart(){
        // convert date to localdatetime
        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                
        // plus one
        localDateTime = localDateTime.plusDays(1);

        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(currentDatePlusOneDay);

        p.sendMessage("Pomyślnie stworzono przedmiot");
        switch(item){
          case 1:{
               SettingsManager.getPlayers().set(p.getName()+".czas.picaxe", dateStr);
               SettingsManager.savePlayers();
               break;
          }case 2:{
            SettingsManager.getPlayers().set(p.getName()+".czas.sword", dateStr);
            SettingsManager.savePlayers();
            break;
          }case 3:{
            SettingsManager.getPlayers().set(p.getName()+".czas.axe", dateStr);
            SettingsManager.savePlayers();
            break;
          }case 4:{
            SettingsManager.getPlayers().set(p.getName()+".czas.shovel", dateStr);
            SettingsManager.savePlayers();
            break;
          }
        }
    }
}