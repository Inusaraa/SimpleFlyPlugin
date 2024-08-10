package org.inusara;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	@SuppressWarnings("unused")
	private PlayerData playerData;
	
	public static Main getInstance() {
		return plugin;
	}
	
	
	@Override
	public void onEnable() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		this.playerData = new PlayerData(this);
		plugin = this;
		getLogger().info(ChatColor.GREEN + "Enabled Fly Plugin v1.1");
		getServer().getPluginManager().registerEvents((Listener)new Event(), (Plugin)this);
		getConfig().options().copyDefaults(true);
	    this.getCommand("fly").setExecutor(new Command());
		this.getCommand("flyspeed").setExecutor(new Command());
		this.getCommand("lfly").setExecutor(new Command());
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		//playerData.save(); after restart or stop, will delete all data in 'playerdata.yml'. So do not use this.
	}
}
