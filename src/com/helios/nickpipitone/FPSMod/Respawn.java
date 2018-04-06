package com.helios.nickpipitone.FPSMod;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Respawn extends PlayerDeathEvent {
	Random generator = new Random();

	public Respawn(Player player, List<ItemStack> drops, int droppedExp,
			int newExp, int newTotalExp, int newLevel, String deathMessage) {
		super(player, drops, droppedExp, newExp, newTotalExp, newLevel, deathMessage);
		
		int rand = generator.nextInt(Main.index) - 1;
		player.sendMessage(String.valueOf(rand));
		if( !(rand < 0) )
			player.teleport(Main.Spawns[rand]);
	}
}
