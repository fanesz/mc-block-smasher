package com.vanezzz.ngck.OtherEvent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class CancelMilk implements Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack consumedItem = event.getItem();
        if (consumedItem.getType() == Material.MILK_BUCKET) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§c>> §fYou can't drink milk!");
        }
    }

}
