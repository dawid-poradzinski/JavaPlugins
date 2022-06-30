package com.rootekstudio.Commands;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogIn implements CommandExecutor, Listener {
	Commands plugin;
	SettingsManager a;
	LogIn(Commands plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (Commands.logowanie.contains(p.getName())) {
			e.setTo(e.getFrom());
			p.sendMessage(ChatColor.RED + "Nie możesz się ruszać");
		}
	}

	@EventHandler
	public void onInvnetoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (Commands.logowanie.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onIntenortHit(PlayerDropItemEvent e){
		Player p = (Player) e.getPlayer();
		if(Commands.logowanie.contains(p.getName())){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e){
		SettingsManager.getPerm().set(e.getPlayer().getName()+".commands", "nie");
		SettingsManager.savePerm();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Commands.logowanie.add(p.getName());
		if(!p.hasPlayedBefore()){
			SettingsManager.getPerm().set(p.getName()+".commands", "nie");
			SettingsManager.getPerm().set(p.getName()+".admin", "nie");
			SettingsManager.getPerm().set(p.getName()+".vip", "nie");
			SettingsManager.getPerm().set(p.getName()+".svip", "nie");
			SettingsManager.savePerm();
		}
		String nick = p.getName();

		Connection c = getConnection();

		String readPoints = "SELECT * FROM login WHERE nick='" + nick + "'";

		ResultSet resultPoints = null;
		String nick_db = null;

		try {
			Statement s = c.createStatement();
			resultPoints = s.executeQuery(readPoints);

			if (resultPoints.next()) {
				nick_db = resultPoints.getString("nick");
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			p.sendMessage(ChatColor.RED + "Wystąpił błąd po naszej stronie. "
					+ "Jeżeli problem będzie się powtarzał, skontaktuj się z administratorami. "
					+ "Kod błędu: [get_password_db]");
		}

		try {
			c.close();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		if (nick_db == null) {
			World w = Bukkit.getServer().getWorld(SettingsManager.getCords().getString("spawn.world"));
			double x = SettingsManager.getCords().getDouble("spawn.x");
			double y = SettingsManager.getCords().getDouble("spawn.y");
			double z = SettingsManager.getCords().getDouble("spawn.z");
			p.teleport(new Location(w, x, y, z));
			p.sendMessage(ChatColor.RED + "Zarejestruj się /register <haslo> <haslo>");
		} else {
			if (SettingsManager.getLogin().getConfigurationSection(p.getName() + ".world") == null) {
				SettingsManager.getLogin().set(p.getName() + ".world", p.getLocation().getWorld().getName());
				SettingsManager.getLogin().set(p.getName() + ".x", p.getLocation().getX());
				SettingsManager.getLogin().set(p.getName() + ".y", p.getLocation().getY());
				SettingsManager.getLogin().set(p.getName() + ".z", p.getLocation().getZ());
				SettingsManager.getLogin().set(p.getName() + ".pitch", p.getLocation().getPitch());
				SettingsManager.getLogin().set(p.getName() + ".yaw", p.getLocation().getYaw());
				SettingsManager.saveLogin();
			}
			World w = Bukkit.getServer().getWorld(SettingsManager.getCords().getString("spawn.world"));
			double x = SettingsManager.getCords().getDouble("spawn.x");
			double y = SettingsManager.getCords().getDouble("spawn.y");
			double z = SettingsManager.getCords().getDouble("spawn.z");
			p.teleport(new Location(w, x, y, z));
			p.sendMessage(ChatColor.RED + "Zaloguj się /login <haslo>");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		Bukkit.getServer().getLogger().info(cmd.getName());
	try{
		if (sender instanceof Player) {
			if(!cmd.getName().equalsIgnoreCase("register") && !cmd.getName().equalsIgnoreCase("login")){
				Bukkit.getServer().getLogger().info("komendaniewykonana");

				return true;
			}

			Player p = (Player) sender;
			String nick = p.getName();

			Connection c = getConnection();

			String readPassword = "SELECT password FROM login WHERE nick='" + nick + "'";

			ResultSet resultSet = null;
			String password_db = null;
			String password_entered = null;

			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				digest.reset();
				digest.update(args[0].getBytes("utf8"));
				password_entered = String.format("%040x", new BigInteger(1, digest.digest()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}

			try {
				Statement s = c.createStatement();
				resultSet = s.executeQuery(readPassword);

				if (resultSet.next()) {
					password_db = resultSet.getString("password");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				p.sendMessage(ChatColor.RED + "Wystąpił błąd po naszej stronie. "
						+ "Jeżeli problem będzie się powtarzał, skontaktuj się z administratorami. "
						+ "Kod błędu: [get_password_db]");
			}
			//NIE WYKONUJE SIE
			Bukkit.getServer().getLogger().info(cmd.getName());
			if (cmd.getName().equals("register")) {
				Bukkit.getServer().getLogger().info("register wykonane");

				if (password_db != null) {
					p.sendMessage(ChatColor.RED + "Juz masz ustawione haslo");
					return true;
				} else {
					if (args.length != 2) {
						p.sendMessage(ChatColor.RED + "Wpisz /register <haslo> <haslo>");
						return true;
					}
					if (args[0].equals(args[1])) {

						String SQLupdate = "INSERT INTO login (nick,password,ip)" + " VALUES ('" + nick + "',SHA1('"
								+ args[0] + "'),'" + p.getAddress().getAddress().getHostAddress() + "')";

						try {
							Statement s = c.createStatement();
							s.executeUpdate(SQLupdate);

							p.sendMessage(ChatColor.RED + "Pomyslna rejestracja");

						} catch (SQLException e) {
							e.printStackTrace();
							p.sendMessage(ChatColor.RED + "Wystąpił błąd po naszej stronie. "
									+ "Jeżeli problem będzie się powtarzał, skontaktuj się z administratorami. "
									+ "Kod błędu: [register_db]");
						}

						Commands.logowanie.remove(p.getName());
						SettingsManager.getPerm().set(p.getName() + ".commands", "tak");
						SettingsManager.savePerm();
						return true;
					} else {
						p.sendMessage(ChatColor.RED + "Hasla nie sa takie same");
						return true;
					}
				}
			}
			if (cmd.getName().equalsIgnoreCase("login")) {

				if (!Commands.logowanie.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "Jestes zalogowany");
					return true;
				}
				if (password_db == null) {
					p.sendMessage(ChatColor.RED + "Wpierw zarejestruj sie /register");
					return true;
				}
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "Podaj haslo");
					return true;
				}
				if (password_entered.contains(password_db)) {
					try{
					Commands.logowanie.remove(p.getName());
					p.sendMessage(ChatColor.GREEN + "Pomyslne logowanie");


						String ip = p.getAddress().getAddress().getHostAddress();

						//if theres is more than one acc how to create loop?
						String multiname = "SELECT nick FROM login WHERE ip=" + "'" + ip + "'";
						ArrayList<String> multi = new ArrayList<>();
						ResultSet result = null;
						try {
							Statement s = c.createStatement();
							result = s.executeQuery(multiname);
			
							while(result.next()){
								
								multi.add(result.getString("nick"));
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(multi.size()>=2){
						for(Player k : Bukkit.getServer().getOnlinePlayers()){
							if(SettingsManager.getPerm().getString(k.getName()+".admin").equals("tak")){
								k.sendMessage(ChatColor.RED + "Gracz "+p.getName()+" korzysta z adresu na którym grały konta: " + multi);
							}
						}
					}
						multi.clear();

					World w = Bukkit.getServer().getWorld(SettingsManager.getLogin().getString(p.getName() + ".world"));
					double x = SettingsManager.getLogin().getDouble(p.getName() + ".x");
					double y = SettingsManager.getLogin().getDouble(p.getName() + ".y");
					double z = SettingsManager.getLogin().getDouble(p.getName() + ".z");
					p.teleport(new Location(w, x, y, z));
					SettingsManager.getLogin().set(p.getName() + ".world", null);
					SettingsManager.getLogin().set(p.getName() + ".x", null);
					SettingsManager.getLogin().set(p.getName() + ".y", null);
					SettingsManager.getLogin().set(p.getName() + ".z", null);
					SettingsManager.getLogin().set(p.getName() + ".pitch", null);
					SettingsManager.getLogin().set(p.getName() + ".yaw", null);
					SettingsManager.saveLogin();
					SettingsManager.getPerm().set(p.getName() + ".commands", "tak");
					SettingsManager.savePerm();
					return true;
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Spróbuj ponownie");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Bledne logowanie");
					return true;
				}
			}
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "Musisz byc graczem");
			return true;
		}
		}catch(Exception e){
			sender.sendMessage(ChatColor.RED + "Wystąpił błąd w czasie łączanie się z bazą danych mySQL spróbuj ponownie później");
			return true;
		}
	}	
	public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://xxx/gitcraft", 
            "root", 
            "NOT_PUBLIC");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
