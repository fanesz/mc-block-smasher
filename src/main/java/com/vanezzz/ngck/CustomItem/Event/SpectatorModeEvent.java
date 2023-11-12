package com.vanezzz.ngck.CustomItem.Event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpectatorModeEvent implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(com.vanezzz.ngck.CustomItem.SpectatorMode.Spectatoritem.getItemMeta())) {
                    Player player = event.getPlayer();
                    player.sendMessage("§aSpectator Mode!");
                    player.setGameMode(GameMode.SPECTATOR);
                    player.getInventory().remove(player.getItemInHand());
                }
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(com.vanezzz.ngck.CustomItem.SpectatorMode.Spectatoritem.getItemMeta())) {
                    Player player = event.getPlayer();
                    player.sendMessage("§aSpectator Mode!");
                    player.setGameMode(GameMode.SPECTATOR);
                    player.getInventory().remove(player.getItemInHand());
                }
            }
        }
    }
}
