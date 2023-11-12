package com.vanezzz.ngck.OtherEvent;

import com.vanezzz.ngck.NGCK;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class BreakCounterLB implements Listener {

    public static List<String> punishedPlayer1 = new ArrayList<>();
    public static List<String> punishedPlayer2 = new ArrayList<>();
    public static List<lbData> dataList = new ArrayList<>();
    private static NGCK ngck;
    public static Scoreboard scoreboard;

    public BreakCounterLB(NGCK ngck) {
        this.ngck = ngck;
    }


    public static void BreakCounterLB(Player player) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("BreakCounter", "Dummy","§e§lNGCK Department");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore(" ").setScore(14); //\u0020
        objective.getScore("§d§lTop Player§8:").setScore(13);
        objective.getScore("§8=====================").setScore(1);
        objective.getScore(" §ediscord.gg/ngck").setScore(0);
        player.setScoreboard(scoreboard);

        try{Team team = scoreboard.registerNewTeam(player.getName());} catch (IllegalArgumentException e){}
        Team team = scoreboard.getTeam(player.getName());
        team.setSuffix(" : 0");
        team.addEntry(player.getName());
        objective.getScore(player.getName()).setScore(10);



    }

    public static void startTask(Player player) {
        Bukkit.getScheduler().runTaskLater(ngck, new Runnable() {
            public void run() {
                Objective objective = scoreboard.getObjective("BreakCounter");
                Collections.sort(dataList, new Comparator<lbData>() {
                    @Override
                    public int compare(lbData d1, lbData d2) {
                        return Integer.compare(d1.count, d2.count);
                    }
                });
                Collections.reverse(dataList);
                try {
                    player.setScoreboard(scoreboard);
                    Team team = scoreboard.getTeam(player.getName());
                    for (lbData data : dataList) {
                        if (data.nick.equals(player.getName())) {
                            String totalBreak = data.count >= 1000 ? String.format("%.1fk", data.count / 1000.0) : String.valueOf(data.count);
                            team.setSuffix("§7: §b" + String.valueOf(totalBreak));
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    scoreboard.registerNewTeam(player.getName());
                    Team team = scoreboard.getTeam(player.getName());
                    team.setSuffix(" §7: §b0");
                    for (lbData data : dataList) {
                        if (data.nick.equals(player.getName())) {
                            String totalBreak = data.count >= 1000 ? String.format("%.1fk", data.count / 1000.0) : String.valueOf(data.count);
                            team.setSuffix("§7: §b" + String.valueOf(totalBreak));
                            break;
                        }
                    }
                }
                int n = 0;
                for (lbData data : dataList) {
                    objective.getScore(data.nick).setScore(12 - n);
                    n += 1;
                    Team team2 = scoreboard.getTeam(data.nick);
                    String color = n==1 ? "§e" : n==2 ? "§e" : n==3 ? "§e" : "§7";
                    if(team2 == null){
                        try {
                            scoreboard.registerNewTeam(player.getName());
                            team2.setSuffix(" §7: §b0");
                            team2.setPrefix(color + String.valueOf(n) + ". ");
                        } catch (IllegalArgumentException e){}
                    } else {
                        team2.setPrefix(color + String.valueOf(n) + ". ");
                    }
                }
                Team team = scoreboard.getTeam(player.getName());
                team.addEntry(player.getName());
                //potion effect
                for (lbData data : dataList) {
                    if (data.nick.equals(player.getName())) {
                        if(data.count <= -30 && !punishedPlayer1.contains(player.getName())) {
                            PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2);
                            player.addPotionEffect(effect);
                            player.sendMessage("§0>> §cYou Get §4Punished§c!, §0Slowness §capplied permanently until your score above §4-30§c!");
                            System.out.println(">> " + data.nick + " punished because the score below -30");
                            punishedPlayer1.add((player.getName()));
                        } else if(data.count <= -100 && !punishedPlayer2.contains(player.getName())){
                            PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 2);
                            player.addPotionEffect(effect);
                            player.sendMessage("§0>> §cYou Get §4Punished§c!, §0Blindness §capplied permanently until your score above §4-100§c!");
                            System.out.println(">> "+data.nick+" punished because the score below -100");
                            punishedPlayer2.add((player.getName()));
                        } else if(data.count > -30 && punishedPlayer1.contains(player.getName())){
                            player.removePotionEffect(PotionEffectType.SLOW);
                            player.sendMessage("§0>> §fYou no longer §4Punished§c!, §0Slowness §cremoved!");
                            punishedPlayer1.remove(player.getName());
                        } else if(data.count > -100 && punishedPlayer2.contains(player.getName())) {
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            player.sendMessage("§0>> §fYou no longer §4Punished§c!, §0Blindness §cremoved!");
                            punishedPlayer2.remove(player.getName());

                        }
                        break;
                    }
                }
                //potion effect end
                if(Bukkit.getOnlinePlayers().contains(player)){
                    startTask(player);
                }
            }
        }, 60L);
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Material brokeBlock = event.getBlock().getType();
        Player player = event.getPlayer();
        if(!event.getBlock().isSolid() || event.getBlock().isPassable() && !player.isOp()){
            player.sendActionBar("§8You didn't get a point from breaking "+brokeBlock+".");
            return;
        }
        for (lbData playerList : dataList){
            if(playerList.nick.equals(player.getName())){
                playerList.count += 1;

                if(playerList.count%50 == 0){
                    player.sendActionBar("§eYou broke "+String.valueOf(playerList.count)+" blocks!");
                }
                break;
            }
        }
    }
    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.isOp()){
            for (lbData playerList : dataList){
                if(playerList.nick.equals(player.getName())){
                    playerList.count -= 1;
                    player.sendActionBar("§4-1 score from placing a block.");
                    break;
                }
            }
        }
    }

    @EventHandler
    public static void onJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        BreakCounterLB(player);
        try{
            scoreboard.registerNewTeam(player.getName());
            Team team = scoreboard.getTeam(player.getName()); team.setSuffix(" §7: §b0");
        }catch (IllegalArgumentException e){}
        boolean newData = true;
        for (lbData playerList : dataList){
            if(playerList.nick.equals(player.getName())){
                newData = false;
            }
        }
        if(newData){
            dataList.add((new lbData(player.getName(), 0)));
            // first join msg

        }
        startTask(player);

    }



}
