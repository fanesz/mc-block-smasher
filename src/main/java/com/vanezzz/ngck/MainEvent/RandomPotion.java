package com.vanezzz.ngck.MainEvent;

import com.vanezzz.ngck.NGCK;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;


public class RandomPotion implements CommandExecutor {
    private NGCK plugin;
    public RandomPotion(NGCK plugin) {
        this.plugin = plugin;
    }
    static String[] potionList = {
            "haste",
            "blindness",
            "speed",
            "slowness",
            "mining fatigue",
            "jump boost",
            "hunger",
            "poison",
            "glowing"
    };
    static String currentPotion;
    static PotionEffect currentAppliedPotion;
    static String appliedPotionMessage;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;
        }
        boolean found = false;
        if(args.length != 0){
            currentPotion = String.join(" ", args);
            for(String potion : potionList){
                if(currentPotion.equals(potion)){
                    found = true;
                }
            }
            if(found == false){
                sender.sendMessage("§e>> Wrong args!, §f/eventpotion <"+String.join(" | ",potionList)+">.");
                return true;
            }
        } else {
            Random rand = new Random();
            currentPotion = potionList[rand.nextInt(potionList.length)];
        }

        if(currentPotion.equals("haste")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.FAST_DIGGING, 45*20, 3);
            appliedPotionMessage = "§e§lHaste §rfor §a45 §fSecond!";
        } else if(currentPotion.equals("blindness")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.BLINDNESS, 60*20, 1);
            appliedPotionMessage = "§0§lBlindness §rfor §a1 §fMinute!";
        } else if(currentPotion.equals("speed")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.SPEED, 60*20, 5);
            appliedPotionMessage = "§d§lSpeed §rfor §a1 §fMinute!";
        } else if(currentPotion.equals("slowness")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.SLOW, 60*20, 3);
            appliedPotionMessage = "§7§lSlowness §rfor §a1 §fMinute!";
        } else if(currentPotion.equals("mining fatigue")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.SLOW_DIGGING, 45*20, 3);
            appliedPotionMessage = "§3§lMining Fatigue §rfor §a45 §fSecond!";
        } else if(currentPotion.equals("jump boost")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.JUMP, 120*20, 3);
            appliedPotionMessage = "§b§lJump Boost §rfor §a2 §fMinutes!";
        } else if(currentPotion.equals("hunger")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.HUNGER, 60*20, 2);
            appliedPotionMessage = "§6§lHunger §rfor §a1 §fMinute!";
        } else if(currentPotion.equals("poison")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.POISON, 60*20, 1);
            appliedPotionMessage = "§2§lPoison §rfor §a60 §fSecond!";
        } else if(currentPotion.equals("glowing")){
            currentAppliedPotion = new PotionEffect(PotionEffectType.GLOWING, 120*20, 1);
            appliedPotionMessage = "§e§lGlowing §rfor §a2 §fMinutes!";
        } else {
            sender.sendMessage("§e>> Wrong args!, §f/eventpotion <"+String.join(" | ",potionList)+">.");
            return true;
        }



        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("§e>> RANDOM EVENT!, §fall player got random potion effect "+appliedPotionMessage);
            player.addPotionEffect(currentAppliedPotion);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
        }


//        Player player = Bukkit.getPlayer("playerName");
//        PotionEffect effect = new PotionEffect(PotionEffectType.FAST_DIGGING, 30*20, 1);
//        player.addPotionEffect(currentAppliedPotion);



//        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
//            public void run() {
//
//
//            }
//        }, 400L);


        return true;
    }
}
