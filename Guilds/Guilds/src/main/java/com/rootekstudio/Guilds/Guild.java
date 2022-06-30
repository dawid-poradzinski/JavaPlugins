package com.rootekstudio.Guilds;

import java.util.ArrayList;
import java.util.List;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Guild {
    Guilds plugin;
    public Guild(Guilds plugin){
        this.plugin=plugin;
    }
    private int lv;
    private String owner;
    private List<String> members;
    private List<String> ally;
    private List<String> dwar;
    private String name;
    private String awar;
    private String cech;
    private String mwar;
    private Boolean week;
    private Boolean guard;
    private Boolean attack;
    private Boolean defense;
    private int xp;
    private int nextlv;
    private int heart;

    public Guild(String name){
        this.name=name;
        mwar=SettingsManager.getGuilds().getString(name+".mwar");
        lv=SettingsManager.getGuilds().getInt(name+".lv");
        owner=SettingsManager.getGuilds().getString(name+".owner");
        members=SettingsManager.getGuilds().getStringList(name+".members");
        ally=SettingsManager.getGuilds().getStringList(name+".ally");
        dwar=SettingsManager.getGuilds().getStringList(name+".dwar");
        cech=SettingsManager.getGuilds().getString(name+".cech");
        awar=SettingsManager.getGuilds().getString(name+".awar");
        week=SettingsManager.getGuilds().getBoolean(name+".week");
        guard=SettingsManager.getGuilds().getBoolean(name+".guard");
        attack=SettingsManager.getGuilds().getBoolean(name+".attack");
        defense=SettingsManager.getGuilds().getBoolean(name+".defense");
        xp = SettingsManager.getGuilds().getInt(name+".xp");
        nextlv = SettingsManager.getGuilds().getInt(name+".nextlv");
        heart = SettingsManager.getGuilds().getInt(name+".hearts");
    }

    public void setOwner(String owner){SettingsManager.getGuilds().set(name+".owner", owner);}
    public void setAWar(String awar){SettingsManager.getGuilds().set(name+".awar", awar);}
    public void setCech(String cech){SettingsManager.getGuilds().set(name+".cech", cech);}
    public void setMwar(String war){SettingsManager.getGuilds().set(name+".mwar", war);}
    public void setWeek(Boolean week){SettingsManager.getGuilds().set(name+".week", week);}
    public void setGuard(Boolean guard){SettingsManager.getGuilds().set(name+".guard", guard);}
    public void setAttack(Boolean attack){SettingsManager.getGuilds().set(name+".attack", attack);}
    public void setDefense(Boolean defense){SettingsManager.getGuilds().set(name+".defense", defense);}
    public void setLv(int lv){SettingsManager.getGuilds().set(name+".lv", lv);}
    public void setMembers(String members){
        List<String> list = SettingsManager.getGuilds().getStringList(name+".members");
        list.add(members);
        SettingsManager.getGuilds().set(name+".members",list);
    }
    public void setAlly(String ally){
        List<String> list = new ArrayList<String>();
        if(SettingsManager.getGuilds().get(name+".ally") != null){
        list = SettingsManager.getGuilds().getStringList(name+".ally");
        }
        list.add(ally);
        SettingsManager.getGuilds().set(name+".ally",list);
    }
    public void setDWar(String dwar){
        List<String> list = new ArrayList<String>();
        if(SettingsManager.getGuilds().get(name+".dwar") != null){
        list = SettingsManager.getGuilds().getStringList(name+".dwar");
        }
        list.add(dwar);
        SettingsManager.getGuilds().set(name+".dwar",list);
    }
    public void removeMembers(String members){
        List<String> list = SettingsManager.getGuilds().getStringList(name+".members");
        list.remove(members);
        SettingsManager.getGuilds().set(name+".members",list);
    }
    public void removeAlly(String ally){
        List<String> list = SettingsManager.getGuilds().getStringList(name+".ally");
        list.remove(ally);
        SettingsManager.getGuilds().set(name+".ally",list);
    }
    public void removeDwar(String dwar){
        List<String> list = SettingsManager.getGuilds().getStringList(name+".dwar");
        list.remove(dwar);
        SettingsManager.getGuilds().set(name+".dwar",list);
    }
    public void removeHearts(){
        heart=heart-1;
        SettingsManager.getGuilds().set(getname().toLowerCase()+".hearts", heart);
        SettingsManager.saveGuilds();
        if(gethearts() == 0){
            for(int i=0;i<getmembers().size();i++){
                if(Bukkit.getPlayer(getmembers().get(i)) != null){
                 Bukkit.getPlayer(getmembers().get(i)).sendMessage(ChatColor.RED + "Twojej gildi skończyły się życia.Gidlia zostaje rozwiązana"); 
                }
                SettingsManager.getPlayers().set(Bukkit.getPlayer(getmembers().get(i)).getName()+".name", null);
                SettingsManager.savePlayers();
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                regions.removeRegion(name);
            }
        }
    }
    public void setXp(int adding){
        xp=xp+adding;
        if(xp>=getnextlv()){
            switch(getlv()){
                case 1:{
                    setLv(2);
                    SettingsManager.getGuilds().set(name+".xp", 0);
                    SettingsManager.getGuilds().set(name+".nextlv", 400);
                    SettingsManager.saveGuilds();
                    for(int i=0;i<members.size();i++){
                        Player target = Bukkit.getPlayer(members.get(i));
                            if(target != null){
                                target.sendMessage(ChatColor.GREEN + "Twoja gildia awansowała do następnego lv Gratulacje!!!");
                            }
                    }
                    break;
                }
                case 2:{
                    setLv(3);
                    SettingsManager.getGuilds().set(name+".xp", 0);
                    SettingsManager.getGuilds().set(name+".nextlv", 10000);
                    SettingsManager.saveGuilds();
                    for(int i=0;i<members.size();i++){
                        Player target = Bukkit.getPlayer(members.get(i));
                            if(target != null){
                                target.sendMessage(ChatColor.GREEN + "Twoja gildia awansowała do następnego lv Gratulacje!!!");
                            }
                    }
                    break;
                }
            }
        }
    }

    public int gethearts(){return heart;}
    public int getxp() {return xp;}
    public int getnextlv() {return nextlv;}
    public List<String> getmembers(){return members;}
    public List<String> getally(){return ally;}
    public List<String> getdwar(){return dwar;}
    public int getlv(){return lv;}
    public String getname(){return name;}
    public String getowner(){return owner;}
    public String getcech(){return cech;}
    public String getawar(){return awar;}
    public String getMwar(){return mwar;}
    public boolean getweek(){return week;}
    public boolean getguard(){return guard;}
    public boolean getattack(){return attack;}
    public boolean getdefense(){return defense;}
}