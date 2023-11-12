package com.vanezzz.ngck.UtilCommand;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Mute implements CommandExecutor, Listener {

    public static List<String> mutedList = new ArrayList<>();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player targetPlayer = null;
        if(args.length == 1){
            boolean playerChecker = false;
            for (Player player : Bukkit.getOnlinePlayers()){
                if(player.getName().equals(args[0])){
                    playerChecker = true; targetPlayer = player;
                }
            }
            if(playerChecker == false){
                sender.sendMessage("§e>>§f Player not found!");
                return true;
            }
        }

        if(cmd.getName().equals("mute")) {
            if(args.length == 1){
                for (String player : mutedList){
                    if(player.equals(args[0])){
                        sender.sendMessage("§e>>§f Player already muted!"); return true;
                    }
                }
                mutedList.add(args[0]);
                for (Player player : Bukkit.getOnlinePlayers()){
                    player.sendMessage("§d** §eThe Ancient Ones use §0Duct-Tape §eon §f"+args[0]+" §d**");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
                }
                targetPlayer.sendTitle("§0§lYOU GOT MUTED", "§8for 5 minutes.", 0, 100, 20);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 100, 0.1f);
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                Player finalTargetPlayer = targetPlayer;
                Runnable task1 = () -> {
                    boolean found = false;
                    for (String player : mutedList){
                        if(player.equals(finalTargetPlayer.getName()) && found == false){
                            mutedList.remove(args[0]);
                            found = true;
                            finalTargetPlayer.sendMessage("§0>>§f You Got Unmuted!");
                        }
                    }
                };
                scheduler.schedule(task1, 300, TimeUnit.SECONDS);

            } else {
                sender.sendMessage("§e>>§f Wrong command!, /mute <player>");
            }
        } else if(cmd.getName().equals("unmute")){
            if(args.length == 1){
                boolean found2 = false;
                for (String player : mutedList){
                    if(player.equals(args[0])){
                        mutedList.remove(mutedList.indexOf(args[0]));
                        found2 = true;
                        sender.sendMessage("§e>>§f Sucessfully unmute §b"+args[0]+"§f!");
                        targetPlayer.sendMessage("§0>>§f You Got Unmuted!");
                        break;
                    }
                }
                if(found2==false){
                    sender.sendMessage("§e>>§f Can't find player!, /mutelist");
                }
            } else {
                sender.sendMessage("§e>>§f Wrong command!, /unmute <player>");
            }
        } else if(cmd.getName().equals("mutelist")){
            int count = 1;
            String mutedListCombined = "\n";
            for(String player : mutedList){
                mutedListCombined += "§e"+String.valueOf(count)+". §b"+player+"\n";
                count++;
            }
            sender.sendMessage("§e=============================\n§e>> §fMuted list§8:"+mutedListCombined+"§e=============================");
        } else {
            sender.sendMessage("§e>>§f Wrong command!, /mute <player> | /unmute <player> | /mutelist");
        }
        return true;
    }


}
