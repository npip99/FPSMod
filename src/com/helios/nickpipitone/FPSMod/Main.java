package com.helios.nickpipitone.FPSMod;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	
	static Location[] Spawns = new Location[256];
	static int index = 0;
	
	@Override
	public void onEnable() {
		getLogger().info("FPS Mod: Enabled!");
	}
	
	public void onEntityDeath(PlayerDeathEvent e){
	    Player player = e.getPlayer();
	    if (e.getEntity() instanceof Player){
	    }
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if( cmd.getName().equalsIgnoreCase("addspawn") ) {
			if( index == 256 ) {
				sender.sendMessage("Max amount of spawns added");
				return true;
			}
			if( args.length == 0 ) {
				if( !(sender instanceof Player) ) {
					sender.sendMessage("You must be a player, or use /addspawn <x> <y> <z>");
					return true;
				}
				if( ((Player) sender).hasPermission("FPSMod.addspawn") ) {
					Location loc = ((Player) sender).getLocation();
					Spawns[index] = loc;
					sender.sendMessage("Added " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
				} else {
					sender.sendMessage("You don't have permission to use this command.");
					return true;
				}
			} else if( args.length == 3 ) {
				if( sender instanceof Player && !(((Player) sender).hasPermission("FPSMod.addspawn")) ) {
					sender.sendMessage("You don't have permission to use this command.");
					return true;
				}
				Location loc = new Location(getServer().getWorlds().get(0), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				Spawns[index] = loc;
				sender.sendMessage("Added " + Spawns[index].getBlockX() + " " + Spawns[index].getBlockY() + " " + Spawns[index].getBlockZ());
			} else {
				sender.sendMessage("Incorrect number of arguments");
				return true;
			}
			index++;
			return true;
		}
		
		if( cmd.getName().equalsIgnoreCase("listspawn") ) {
			if( sender instanceof Player && !(((Player) sender).hasPermission("FPSMod.addspawn")) ) {
				sender.sendMessage("You don't have permission to use this command.");
			} else {
				for( int i = 0; i < index; i++ )
					sender.sendMessage((i+1) + ": " + Spawns[i].getBlockX() + " " + Spawns[i].getBlockY() + " " + Spawns[i].getBlockZ());
			}
			return true;
		}
		
		return false; 
	}
	
	@Override
	public void onDisable() {
		getLogger().info("FPS Mod: Disabled!");
	}
}
