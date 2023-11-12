package com.vanezzz.ngck.MainEvent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScrambleWord implements CommandExecutor, Listener {

    static String[][] wordList = {
            {"palembang","plemabnga"},
            {"minecraft","mneicftra"},
            {"speaker","seakper"},
            {"NGCK","KCGN"},
            {"diamond","dimaodn"},
            {"bogor","brgoo"},
            {"jawir","jairw"},
    };
    static Material[] prize = {
            Material.DIAMOND,
            Material.IRON_SHOVEL,
            Material.GOLDEN_SHOVEL,
            Material.SHEARS,
            Material.GOLDEN_AXE,
            Material.IRON_AXE
    };
    static int lastWord = 0;
    static String[] currentWord = new String[2];
    public static boolean scrambleEvent = false;
    static boolean found = false;
    static Material currentPrize;
    static ScheduledExecutorService scheduler;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            if (scrambleEvent == false) {
                if (lastWord > wordList.length - 1) {
                    player.sendMessage("§c>> §fAll Default Scrambled-Word already used!, /eventscramble <word>");
                    return true;
                }
                Random rand = new Random();
                currentPrize = prize[rand.nextInt(prize.length)];
                Bukkit.broadcastMessage("§e>> RANDOM EVENT!, §ffirst player say the correct scrambled-word §6§l" + wordList[lastWord][1] + " §rwin §e" + currentPrize.name() + "§f!");
                scrambleEvent = true;
                found = false;
                currentWord = new String[]{wordList[lastWord][0], wordList[lastWord][1]};
                scheduler = Executors.newScheduledThreadPool(1);
                Runnable task = () -> {
                    if (found == false) {
                        scrambleEvent = false;
                        Bukkit.broadcastMessage("§e>> §fNo one correct!, the answer is §3" + wordList[lastWord][1] + " §f-> §b" + wordList[lastWord][0]);
                        lastWord += 1;
                    }
                };
                scheduler.schedule(task, 30, TimeUnit.SECONDS);
            }
        } else if(args.length == 1){
            if (scrambleEvent == false) {
                if(args[0].length() <= 3){
                    sender.sendMessage("§e>>§f Wrong Args!, word length minimal is 4.");
                    return true;
                }
                String splitArgs[] = args[0].substring(1).split("");
                String scrambledWord;
                List<String> argsList = Arrays.asList(splitArgs);
                while (true) {
                    scrambledWord = args[0].substring(0, 1);
                    Collections.shuffle(argsList);
                    for (String letter : argsList) {
                        scrambledWord += letter;
                    }
                    if(!scrambledWord.equals(args[0])) break;
                }
                currentWord = new String[]{args[0], scrambledWord};

                Random rand = new Random();
                currentPrize = prize[rand.nextInt(prize.length)];
                Bukkit.broadcastMessage("§e>> RANDOM EVENT!, §ffirst player say the correct scrambled-word §6§l" + scrambledWord + " §rwin §e" + currentPrize.name() + "§f!");
                scrambleEvent = true;
                found = false;
                scheduler = Executors.newScheduledThreadPool(1);
                Runnable task = () -> {
                    if (found == false) {
                        scrambleEvent = false;
                        Bukkit.broadcastMessage("§e>> §fNo one correct!, the answer is §3" + currentWord[1] + " §f-> §b" + args[0]);
                        lastWord += 1;
                    }
                };
                scheduler.schedule(task, 30, TimeUnit.SECONDS);

            }
        } else {
            sender.sendMessage("§e>>§f Wrong Args!, /eventscramble | /eventscramble <word>");
        }
        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(scrambleEvent){
            event.setCancelled(true);
            if (player.isOp()) {
                Bukkit.broadcastMessage("§7<§a" + player.getName() + "§7> §f" + event.getMessage());
            } else {
                Bukkit.broadcastMessage("§7<§f" + player.getName() + "§7> " + event.getMessage());
            }
            if(event.getMessage().equals(currentWord[0])){
                Bukkit.broadcastMessage("§e>> §e"+player.getName() + " §fanswer is §acorrect§f!,  §3"+currentWord[1] + " §f-> §b"+currentWord[0]);
                player.getInventory().addItem(new ItemStack(currentPrize));
                player.sendActionBar("§e>> §eYou got §l"+currentPrize.name()+"§r§e!");
                found = true;
                scrambleEvent = false;
                scheduler.shutdownNow();
                lastWord += 1;
            }
        }
    }
}
