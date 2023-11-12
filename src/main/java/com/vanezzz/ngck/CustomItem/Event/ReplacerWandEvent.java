package com.vanezzz.ngck.CustomItem.Event;

import com.vanezzz.ngck.CustomItem.ReplacerWand;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ReplacerWandEvent implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        Block block = event.getClickedBlock();
//        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
//        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && itemInOffHand.getType() != Material.AIR ) {
//            event.setCancelled(true);
//            if(event.getItem().getItemMeta().equals(ReplacerWand.ReplacerWand.getItemMeta())){
//                if (block.getType() != Material.BEDROCK) {
//                    Material material = itemInOffHand.getType();
//                    block.setType(material);
//
//                }
//            }
//
//        }
    }


}
