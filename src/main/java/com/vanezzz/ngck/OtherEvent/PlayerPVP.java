package com.vanezzz.ngck.OtherEvent;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.vanezzz.ngck.OtherEvent.BreakCounterLB.dataList;

public class PlayerPVP implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player) {
                Player damager = (Player) projectile.getShooter();
                if (event.getEntity() instanceof Player) {
                    for (lbData playerData : dataList) {
                        if (playerData.nick.equals(damager.getName())) {
                            playerData.count -= 3;
                            damager.sendActionBar("§cYou Hit Someone!, §4-3 §cscore!");
                        }
                    }
                }
            }
        } else if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            for (lbData playerData : dataList) {
                if (playerData.nick.equals(damager.getName())) {
                    playerData.count -= 5;
                    damager.sendActionBar("§cYou Shot Someone!, §4-5 §cscore!");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killedPlayer = event.getEntity();
        Entity killer = killedPlayer.getKiller();
        if(killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            for (lbData playerData : dataList) {
                if(playerData.nick.equals(killerPlayer.getName())){
                    playerData.count = playerData.count-playerData.count*10/100;
                    killerPlayer.sendActionBar("§cYou KILL Someone!, §4-10% §cscore!");
                    killerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 15*20, 5));
                    killerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15*20, 5));

                }
            }
        }
    }

}
