package com.vanezzz.ngck.OtherEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveChanger implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public static void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("§d<§f" + player.getName() + " §dentered, §f" + (Bukkit.getOnlinePlayers().size() - 1) + " §dothers here>");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("§d<§f"+player.getName()+" §dleft, §f"+(Bukkit.getOnlinePlayers().size()-1)+" §dothers here>");
    }
}
