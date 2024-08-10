package org.inusara;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerData extends DataFile {
	
	public PlayerData(Main main) {
		super(main, "playerdata.yml");
	}
	
	public void setPlayer(Player player, boolean fly) {
		config.set(player.getUniqueId().toString() + "." + player.getName(), fly);
	}
	
	public boolean getValue(Player player) {
		boolean x = config.getBoolean(player.getUniqueId().toString() + "." + player.getName());
		return x;
	}
	
	public String getList() {
		ArrayList<String> list = new ArrayList<>();
		String uuid = "";
		String name = "";
		String string = "";
		for(String s : config.getKeys(true)) {
			if(s.length() == 36) {
				continue;
			}
			String[] liString = s.split("\\.");
			loop1:
				for(String a : liString) {
					if(a.length() == 36) {
						uuid = a;
						continue loop1;
					} else {
						name = a;
					}
					if(config.getBoolean(uuid + "." + name)) {
						list.add(name);
					}
				}
		}
		string = String.join(", ", list);
		return string;
	}
	
	public boolean containsPlayer(Player player) {
		try {
			boolean x = config.contains(player.getUniqueId().toString() + "." + player.getName());
			return x;
		} catch (NullPointerException e) {
			return false;
		}
		
	}
}
