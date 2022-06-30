package com.rootekstudio.Guilds;

import java.io.IOException;

import com.rootekstudio.Commands.AllyAns;
import com.rootekstudio.Commands.AllyGuild;
import com.rootekstudio.Commands.BreakAlly;
import com.rootekstudio.Commands.CreateGuild;
import com.rootekstudio.Commands.DestroyGuild;
import com.rootekstudio.Commands.GlHome;
import com.rootekstudio.Commands.QandA;
import com.rootekstudio.Commands.InvAns;
import com.rootekstudio.Commands.InviteGuild;
import com.rootekstudio.Commands.JoinWar;
import com.rootekstudio.Commands.Kick;
import com.rootekstudio.Commands.LeaveGuild;
import com.rootekstudio.Commands.SetOwner;
import com.rootekstudio.Commands.StartWar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class Menu implements CommandExecutor{
    Guilds plugin;
    public Menu(Guilds plugin){
        this.plugin=plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(ChatColor.GREEN + "/gl help");
            return true;
        }
        if(args[0].equalsIgnoreCase("help")){
            sender.sendMessage(ChatColor.GOLD + "Lista dostępnych komend");
            sender.sendMessage(ChatColor.GOLD + "create - tworzy gildie");
            sender.sendMessage(ChatColor.GOLD + "invite - zaprasza do gildi");
            sender.sendMessage(ChatColor.GOLD + "accept/deny - akceptuje/odrzuca zaproszenie do gildie");
            sender.sendMessage(ChatColor.GOLD + "kick - wyrzuca gracza z gildi");
            sender.sendMessage(ChatColor.GOLD + "leave - opuszcza gildie");
            sender.sendMessage(ChatColor.GOLD + "destroy - niszczy gildie");
            sender.sendMessage(ChatColor.GOLD + "setowner - wybiera nowego właściciela");
            sender.sendMessage(ChatColor.GOLD + "guild - wyświetla informacje o gildi");
            sender.sendMessage(ChatColor.GOLD + "ally - Wysyła zaproszenie do sojuszu");
            sender.sendMessage(ChatColor.GOLD + "allyaccept/allydeny - akceptuje/odrzuca zaproszenie do sojuszu");
            sender.sendMessage(ChatColor.GOLD + "breakally - Zrywa sojusz");
            sender.sendMessage(ChatColor.GOLD + "startwar - Rozpoczyna wojnę jako agresor");
            sender.sendMessage(ChatColor.GOLD + "Aby stworzyć gildię potrzebujesz mieć w ekwipunku po jednym bloku:");
            sender.sendMessage(ChatColor.GOLD + "Złota,Diamentu,Żelaza,Redstonu,Lapisu,Emeraldu");
            return true;
        }
        reloadcommands();
        if(!Guilds.permconf.get(sender.getName()+".commands").equals("tak")){
            sender.sendMessage(ChatColor.RED +"Musisz się zalogować");
            return true;
        }
        else if(args[0].equalsIgnoreCase("create")){
           CreateGuild.onCommand(sender, command, label, args);
           return true;
        }else if(args[0].equalsIgnoreCase("invite")){
            InviteGuild.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("accept") || args[0].equals("deny")){
            InvAns.onCommand(sender,command,label,args);
            return true;
        }else if(args[0].equals("kick")){
            Kick.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("destroy")){
            DestroyGuild.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("setowner")){
            SetOwner.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("leave")){
            LeaveGuild.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("guild")){
            QandA.onCommand(sender,command,label,args);
            return true;
        }else if(args[0].equals("ally")){
            AllyGuild.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("allyaccept") || args[0].equals("allydeny")){
            AllyAns.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("breakally")){
            BreakAlly.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("war")){
            StartWar.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("joinwar")){
            JoinWar.onCommand(sender, command, label, args);
            return true;
        }else if(args[0].equals("home")){
            GlHome.onCommand(sender, command, label, args);
            return true;
        }
        else{
            sender.sendMessage(ChatColor.GREEN + "/gl help");
            return true;
        }
    }
    public static void reloadcommands(){
        Guilds.permconf = YamlConfiguration.loadConfiguration(Guilds.perm);
    }
    public static void savecommands(){
        try
        {
            Guilds.permconf.save(Guilds.perm);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Nie udalo sie zapisac");
        }
    }
}