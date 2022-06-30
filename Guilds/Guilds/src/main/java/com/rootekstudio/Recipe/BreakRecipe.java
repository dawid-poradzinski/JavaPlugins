package com.rootekstudio.Recipe;

import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakRecipe implements Listener {
    int i;
    Guilds plugin;

    public BreakRecipe(Guilds p) {
        this.plugin = p;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(!e.getBlock().getWorld().getName().equals("world")){
            return;
        }
        if(e.getBlock().getType() != Material.SEA_LANTERN)
        {
            if(e.getBlock().getType() != Material.SMOOTH_QUARTZ){
                if(e.getBlock().getType() != Material.SMOOTH_RED_SANDSTONE){
                    if(e.getBlock().getType() != Material.SMOOTH_SANDSTONE){
                        if(e.getBlock().getType() != Material.SMOOTH_STONE){
                            if(e.getBlock().getType() != Material.STRIPPED_ACACIA_LOG){
                                if(e.getBlock().getType() != Material.TUBE_CORAL_BLOCK){
                                    if(e.getBlock().getType() != Material.YELLOW_SHULKER_BOX){
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Double px = e.getBlock().getLocation().getX();
            Double py = e.getBlock().getLocation().getY();
            Double pz = e.getBlock().getLocation().getZ();
        Player p = e.getPlayer();
        if(SettingsManager.getPlayers().get(p.getName()+".name") != null){
            String guilds = SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase();            
        
            for(String l : SettingsManager.getGuilds().getKeys(false)){
                //1
                if(SettingsManager.getGuilds().getDouble(l+".shovelblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".shovelblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".shovelblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(guilds+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(guilds+".shovelblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
                //2
                else if(SettingsManager.getGuilds().getDouble(l+".picaxeblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".picaxeblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".picaxeblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".picaxeblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                    //3
                }else if(SettingsManager.getGuilds().getDouble(l+".swordblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".swordblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".swordblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".swordblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
                //4
                else if(SettingsManager.getGuilds().getDouble(l+".axeblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".axeblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".axeblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".axeblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
                //5
                else if(SettingsManager.getGuilds().getDouble(l+".helmblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".helmblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".helmblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".helmblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
                //6
                else if(SettingsManager.getGuilds().getDouble(l+".chestplateblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".chestplateblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".chestplateblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".chestplateblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                    //7
                }else if(SettingsManager.getGuilds().getDouble(l+".legginsblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".legginsblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".legginsblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".legginsblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
                //8
                else if(SettingsManager.getGuilds().getDouble(l+".bootsblock.x") == px &&
                SettingsManager.getGuilds().getDouble(l+".bootsblock.y") == py &&
                SettingsManager.getGuilds().getDouble(l+".bootsblock.z") == pz
                ){
                    if(l.equals(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase())){
                        if(!SettingsManager.getGuilds().getString(l+".owner").equals(p.getName())){
                            p.sendMessage(ChatColor.RED + "Nie jesteś w stanie tego niszczyć");
                            e.setCancelled(true);
                            return;
                        }
                    }
                    SettingsManager.getGuilds().set(l+".bootsblock", null);
                    SettingsManager.saveGuilds();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "Zniszczyłeś ulepszenie gildi: "+l);
                    return;
                }
            }
        }
    }
}