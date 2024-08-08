package org.inusara;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for(UUID u : Command.listU) {
			if(player.getUniqueId().equals(u)) {
				player.setAllowFlight(true);
				player.setFlying(true);
				player.setFlySpeed(player.getFlySpeed());
				break;
			}
		}
	}
	
}
