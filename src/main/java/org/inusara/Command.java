package org.inusara;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class Command implements CommandExecutor {

	private PlayerData playerData;
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {	
		this.playerData = new PlayerData(Main.getInstance());																																
		if(command.getName().equalsIgnoreCase("fly")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not player!");
				return false;
			}
			Player player = (Player) sender;
			if(!player.hasPermission("Fly.permission")) {
				player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-permission").replaceAll("&", "§"));
				return false;
			}
			if(!player.getAllowFlight()) {
				if (args.length == 1) {
					if(Bukkit.getServer().getPlayer(args[0]) == null) {
						player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-unknown-player").replaceAll("&", "§"));
						return false;
					}
					Player player2 = (Player) Bukkit.getServer().getPlayer(args[0]);
					if(!player2.getAllowFlight()) {
						player2.setAllowFlight(true);
						player2.setFlying(true);
						player2.setFlySpeed(player2.getFlySpeed());
						player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-second-player").replace("%player%", player2.getName()).replaceAll("&", "§"));
						player2.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-second-player-canfly").replaceAll("&", "§"));   
						playerData.setPlayer(player2, true);
						playerData.save();
					} else {
						player2.setAllowFlight(false);
						player2.setFlying(false);
						player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-second-player").replace("%player%", player2.getName()).replaceAll("&", "§"));
						player2.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-second-player-cantfly").replaceAll("&", "§"));
						playerData.setPlayer(player2, false);
						playerData.save();
					}
				} else if(args.length == 0) {
					player.setAllowFlight(true);
					player.setFlying(true);
					player.setFlySpeed(player.getFlySpeed());
					player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-canfly").replaceAll("&", "§"));
					playerData.setPlayer(player, true);
					playerData.save();
				} else {
					player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-usage").replaceAll("&", "§"));
				}
			} else {
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage(Main.getInstance().getConfig().getString("Fly.fly-cantfly").replaceAll("&", "§"));
				playerData.setPlayer(player, false);
				playerData.save();
			}
		}
		if(command.getName().equalsIgnoreCase("flyspeed")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You are not player!");
				return false;
			}
			Player player = (Player) sender;
			if(!player.hasPermission("Fly.permission")) {
				player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-permission").replaceAll("&", "§"));
				return false;
			}
			if(args.length == 1) {
				try {
					float speed = Float.parseFloat(args[0])/10;
					if(!(speed >= -1 && speed <= 1)) {
						player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-value").replaceAll("&", "§"));
						return false;
					}
					player.setFlySpeed(speed);
					player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-successfuly").replace("%speed%", Float.toString(speed*10)).replaceAll("&", "§"));
				} catch (NumberFormatException e) {
					player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-usage").replaceAll("&", "§"));
					return false;
				}
			} else if(args.length == 2) {
				if(Bukkit.getServer().getPlayer(args[0]) == null) {
					player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-unknown-player").replaceAll("&", "§"));
					return false;
				}
				Player player2 = (Player) Bukkit.getServer().getPlayer(args[0]);
				try {
					float speed = Float.parseFloat(args[1])/10;
					if(!(speed >= -1 && speed <= 1)) {
						player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-value").replaceAll("&", "§"));
						return false;
					}
					player2.setFlySpeed(speed);
					player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-secondp-successfuly").replace("%player%", player2.getName()).replace("%speed%", Float.toString(speed*10)).replaceAll("&", "§"));
					player2.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-set-successfuly").replace("%player%", player2.getName()).replace("%speed%", Float.toString(speed*10)).replaceAll("&", "§"));
				} catch (NumberFormatException e) {
					player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-usage").replaceAll("&", "§"));
					return false;
				}
			} else {
				player.sendMessage(Main.getInstance().getConfig().getString("Flyspeed.flyspeed-usage").replaceAll("&", "§"));
			}
		}
		if(command.getName().equalsIgnoreCase("lfly")) {
			if(!sender.hasPermission("Fly.permission")) {
				sender.sendMessage(Main.getInstance().getConfig().getString("Lfly.lfly-permission").replaceAll("&", "§"));
				return false;
			}
			if(args.length != 0) {
				sender.sendMessage(Main.getInstance().getConfig().getString("Lfly.lfly-usage").replaceAll("&", "§"));
				return false;
			}
			sender.sendMessage(Main.getInstance().getConfig().getString("Lfly.lfly-list").replace("%list%", playerData.getList()).replaceAll("&", "§"));
		}
		return true;
	}
}
