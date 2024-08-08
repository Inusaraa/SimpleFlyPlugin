package org.inusara;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public static Main getInstance() {
		return plugin;
	}
	
	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info(ChatColor.GREEN + "Enabled Example Fly Plugin v1.0");
		getServer().getPluginManager().registerEvents((Listener)new Event(), (Plugin)this);
		getConfig().options().copyDefaults(true);
	    this.getCommand("fly").setExecutor(new Command());
		this.getCommand("flyspeed").setExecutor(new Command());
		this.getCommand("lfly").setExecutor(new Command());
		saveConfig();
	}
	
}
