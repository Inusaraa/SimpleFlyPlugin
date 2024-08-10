package org.inusara;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event implements Listener {

	private PlayerData playerData;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		this.playerData = new PlayerData(Main.getInstance());
		if(playerData.containsPlayer(player)) {
			player.setAllowFlight(playerData.getValue(player));
			player.setFlying(playerData.getValue(player));
		}
		
	}
	
}
