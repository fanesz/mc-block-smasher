package com.vanezzz.ngck.OtherEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.vanezzz.ngck.MainEvent.ScrambleWord.scrambleEvent;
import static com.vanezzz.ngck.UtilCommand.Mute.mutedList;

public class ChatChanger implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        for (String mutedPlayer : mutedList){
            if(mutedPlayer.equals(player.getName())){
                event.setCancelled(true);
                event.getPlayer().sendMessage("§0>> §fCan't talk, you got muted!");
                System.out.println("§0>> §e"+player.getName()+" §ftrying to talk : §7"+event.getMessage());
                return;
            }
        }
        if(!scrambleEvent) {
            event.setCancelled(true);
            if (player.isOp()) {
                Bukkit.broadcastMessage("§7<§a" + player.getName() + "§7> §f" + event.getMessage());
            } else {
                Bukkit.broadcastMessage("§7<§f" + player.getName() + "§7> " + event.getMessage());
            }
        }

    }
}
