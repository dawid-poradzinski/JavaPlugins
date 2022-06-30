package com.rootekstudio.Commands;

import com.rootekstudio.Guilds.Guild;
import com.rootekstudio.Guilds.Guilds;
import com.rootekstudio.Guilds.SettingsManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartWar {

    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Musisz być graczem");
            return true;
        }
        Player p = (Player) sender;
        if (SettingsManager.getPlayers().get(p.getName() + ".name") == null) {
            p.sendMessage(ChatColor.RED + "Nie jesteś w żadnej gildi");
            return true;
        }
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "/gl war <gildia>");
            return true;
        }
        Guild guild = new Guild(SettingsManager.getPlayers().getString(p.getName() + ".name").toLowerCase());
        if (!p.getName().equals(guild.getowner())) {
            p.sendMessage(ChatColor.RED + "Nie jesteś właścicielem");
            return true;
        }
        if(args[1].toLowerCase().equals(guild.getname().toLowerCase())){
            p.sendMessage(ChatColor.RED + "Nie możesz wypowiedzieć wojny sam sobie");
            return true;
        }
        if(guild.getattack()){
            p.sendMessage(ChatColor.RED + "Twoja gildia wypowiedziała już wojnę");
            return true;
        }
        if (SettingsManager.getGuilds().get(args[1].toLowerCase()) == null) {
            p.sendMessage(ChatColor.RED + "Nie można odnaleźć gildi");
            return true;
        }
        if(guild.getally().contains(args[1])){
            p.sendMessage(ChatColor.RED + "Nie możesz wypowidać wojny sojusznikowi");
            return true;
        }
        Guild target = new Guild(args[1].toLowerCase());
        if(target.getguard()){
            p.sendMessage(ChatColor.RED + "Gildia aktualnie posiada ochronę 24h");
            return true;
        }
        Player owner = Bukkit.getPlayer(target.getowner());
        if(!Guilds.permconf.get(owner.getName()+".commands").equals("tak")){
            sender.sendMessage(ChatColor.RED +"Przeciwny lider nie jest zalogowany");
            return true;
        }
        if(target.getdefense()){
            p.sendMessage(ChatColor.RED + "Przeciwna gildia prowadzi aktualnie wojne");
            return true;
        }
        guild.setAWar(target.getname());
        guild.setAttack(true);
        target.setDWar(guild.getname());
        target.setMwar(guild.getname());
        target.setDefense(true);
        SettingsManager.saveGuilds();
        for(int i=0;i<guild.getmembers().size();i++){
            if(Bukkit.getPlayer(guild.getmembers().get(i)) != null){
        Bukkit.getPlayer(guild.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Twoja wypowiedziała wojne: "+target.getname());
        Bukkit.getPlayer(guild.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Macie 10 minut do zniknięcia cuboida");    
        }
        }
        for(int i=0;i<target.getmembers().size();i++){
            if(Bukkit.getPlayer(target.getmembers().get(i)) != null){
        Bukkit.getPlayer(target.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Twojej gildi została wypowiedziana wojna przez: "+guild.getname());    
        Bukkit.getPlayer(target.getmembers().get(i)).sendMessage(ChatColor.GREEN + "Macie 10 minut do zniknięcia cuboida");    
        }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable() {

            @Override
            public void run() {
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                DefaultDomain owners = regions.getRegion(target.getname().toLowerCase()).getMembers();
                for(int i=0;i<guild.getmembers().size();i++){
                    owners.addPlayer(guild.getmembers().get(i));
                }
                regions.getRegion(target.getname().toLowerCase()).setMembers(owners);
            }
        },12000L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Guilds.getInstance(), new Runnable(){

			@Override
			public void run() {
				if(!target.getguard()){
                    guild.removeHearts();
                    target.setXp(30);
                }
				
			}

        },60000L);
        return true;
    }
}