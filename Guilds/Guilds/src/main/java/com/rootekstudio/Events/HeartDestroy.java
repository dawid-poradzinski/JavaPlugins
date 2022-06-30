package com.rootekstudio.Events;


import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.event.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class HeartDestroy implements Listener{

    Guilds plugin;
    public HeartDestroy(Guilds plugin){
        this.plugin=plugin;
    }

        @EventHandler
        public void onPlayerHeartDestroy(BlockBreakEvent e){
            if(e.getBlock().getType() == Material.JUKEBOX){
                Double xl = e.getBlock().getLocation().getX();
                Double yl = e.getBlock().getLocation().getY();
                Double zl = e.getBlock().getLocation().getZ();
                if(e.getBlock().getWorld().getName().equals("world")){
                Player p = e.getPlayer();
                if(SettingsManager.getPlayers().get(p.getName()+".name") != null){
                    Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName()+".name").toLowerCase());
                        if(SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase()+".heart.x") == xl &&
                        SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase()+".heart.y") == yl &&
                        SettingsManager.getGuilds().getDouble(guild.getname().toLowerCase()+".heart.z") == zl
                        ){
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + " Nie niszcz tego serca");
                        return;
                        }
                        if(guild.getally().size()!=0){

                            if(guild.getally().size()==1){
                                if(SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.x") == xl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.y") == yl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.z") == zl
                                ){
                                e.setCancelled(true);
                                p.sendMessage(ChatColor.RED + " Nie niszcz tego serca");
                                return;
                                }
                            }
                            if(guild.getally().size()==2){
                                if(SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.x") == xl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.y") == yl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(0)+".heart.z") == zl
                                ){
                                e.setCancelled(true);
                                p.sendMessage(ChatColor.RED + " Nie niszcz tego serca");
                                return;
                                }else if(SettingsManager.getGuilds().getDouble(guild.getally().get(1)+".heart.x") == xl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(1)+".heart.y") == yl &&
                                SettingsManager.getGuilds().getDouble(guild.getally().get(1)+".heart.z") == zl
                                ){
                                e.setCancelled(true);
                                p.sendMessage(ChatColor.RED + " Nie niszcz tego serca");
                                return;
                                }
                            }
                         }
                    if(guild.getattack()){
                        if(SettingsManager.getGuilds().getDouble(guild.getawar().toLowerCase()+".heart.x") == xl &&
                        SettingsManager.getGuilds().getDouble(guild.getawar().toLowerCase()+".heart.y") == yl &&
                        SettingsManager.getGuilds().getDouble(guild.getawar().toLowerCase()+".heart.z") == zl) {
                            e.setCancelled(true);
                            Guild target = new Guild(guild.getawar().toLowerCase());
                            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                            RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                            DefaultDomain owners = regions.getRegion(target.getname().toLowerCase()).getMembers();
                            for(int i=0;i<target.getmembers().size();i++){
                                owners.addPlayer(target.getmembers().get(i));
                            }
                            regions.getRegion(target.getname().toLowerCase()).setMembers(owners);

                            for(int i=0;i<target.getdwar().size();i++){
                                Guild guild2 = new Guild(target.getdwar().get(i).toLowerCase());
                                for(int j=0;j<guild2.getmembers().size();j++){
                                    Player playertarget = Bukkit.getPlayer(guild2.getmembers().get(j));
                                    if(playertarget != null){
                                        playertarget.sendMessage(ChatColor.GREEN + "Gracz "+p.getName()+" zniszczył wrogie serce. Wojna została wygrana!!!");
                                    }
                                }
                            }
                            for(int i=0;i<target.getmembers().size();i++){
                                Player targetplayer = Bukkit.getPlayer(target.getmembers().get(i));
                                if(targetplayer != null){
                                    targetplayer.sendMessage(ChatColor.RED + "Gracz "+p.getName()+" zniszczył wrogie serce. Wojna została przegrana!!!");
                                }
                            }
                            for(int i=0;i<target.getally().size();i++){
                                Guild guild2 = new Guild(target.getally().get(i).toLowerCase());
                                for(int j=0;j<guild2.getmembers().size();j++){
                                    Player playertarget = Bukkit.getPlayer(guild2.getmembers().get(j));
                                    if(playertarget != null){
                                        playertarget.sendMessage(ChatColor.RED + "Serce gildi "+guild.getname()+" zostało zniszczone. Przegrali wojne!!!");
                                    }
                                }
                            }
                            guild.setAttack(false);
                            guild.setAWar(null);
                            guild.setXp(30);
                            SettingsManager.saveGuilds();
                            target.setDefense(false);
                            SettingsManager.getGuilds().set(guild.getname().toLowerCase()+".dwar", null);
                            target.setMwar(null);
                            SettingsManager.saveGuilds();
                            target.setGuard(true);
                            SettingsManager.saveGuilds();
                            target.removeHearts();
                            SettingsManager.saveGuilds();
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable(){
                            
                                @Override
                                public void run() {
                                    target.setGuard(false);
                                    SettingsManager.saveGuilds();
                                }
                            },72000L);
                            }
                        }
                    }
                }
            }
        }
    }
