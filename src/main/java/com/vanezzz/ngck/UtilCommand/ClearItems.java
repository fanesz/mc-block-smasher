package com.vanezzz.ngck.UtilCommand;

import com.vanezzz.ngck.NGCK;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class ClearItems implements CommandExecutor {
    static ScheduledExecutorService scheduler;
    static boolean isRunning;
    private NGCK plugin;
    int totalRemoved;

    public ClearItems(NGCK plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;
        }

        if(isRunning){
            Bukkit.broadcastMessage("§4>> §cClear items canceled.");
            isRunning = false; totalRemoved=0;
            return true;
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§4>> §cDropped items will be cleared in §e§l30 §csecond.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
            }

            isRunning = true; totalRemoved=0;

            ////10sec
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if (isRunning) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage("§4>> §cDropped items will be cleared in §e§l10 §csecond.");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
                        }
                    }
                }
            }, 400L);

            ////3sec
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if (isRunning) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage("§4>> §cDropped items will be cleared in §e§l3 §csecond.");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
                        }
                    }
                }
            }, 540L);

            ////2sec
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if (isRunning) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage("§4>> §cDropped items will be cleared in §e§l2 §csecond.");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
                        }
                    }
                }
            }, 560L);

            ////1sec
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if(isRunning) {
                        for (Player player : Bukkit.getOnlinePlayers()){
                            player.sendMessage("§4>> §cDropped items will be cleared in §e§l1 §csecond.");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
                        }
                    }
                };
            }, 580L);

            ////cleared
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if (isRunning) {
                        isRunning = false;
                        for (World w : Bukkit.getServer().getWorlds()) {
                            List<Entity> entities = w.getEntities();
                            for (Entity entity : entities) {
                                if (entity instanceof Item) {
                                    ItemStack item = ((Item) entity).getItemStack();
                                    totalRemoved += item.getAmount();
                                    entity.remove();
                                }
                            }
                        }
                        for (Player player : Bukkit.getOnlinePlayers()){
                            player.sendMessage("§4>> §cSuccessfully delete §a"+totalRemoved+"§c items!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
                        }
                    }
                }
            }, 600L);



        }








        return true;
    }
}
