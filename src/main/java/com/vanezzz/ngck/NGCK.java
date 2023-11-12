package com.vanezzz.ngck;

import com.vanezzz.ngck.EditScore.*;
import com.vanezzz.ngck.MainEvent.RandomPotion;
import com.vanezzz.ngck.MainEvent.ScrambleWord;
import com.vanezzz.ngck.OtherEvent.*;
import com.vanezzz.ngck.UtilCommand.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static com.vanezzz.ngck.UtilCommand.BackupScore.backupExport;


public final class NGCK extends JavaPlugin implements Listener {

    private BreakCounterLB breakCounterLB;

    @Override
    public void onEnable() {

//        getServer().getPluginManager().registerEvents(new SpectatorModeEvent(), this);
//        getCommand("itemspectator").setExecutor(new GiveItem());
//        SpectatorMode.init();

//        getServer().getPluginManager().registerEvents(new ReplacerWandEvent(), this);
//        getCommand("itemreplacerwand").setExecutor(new GiveItem());
//        ReplacerWand.init();

        getCommand("scoregive").setExecutor(new GiveScore());
        getCommand("scoreremove").setExecutor(new RemoveScore());
        getCommand("scoreset").setExecutor(new SetScore());
        getCommand("score").setExecutor(new CheckScore());

        getCommand("sb").setExecutor(new Broadcast());
        getCommand("sbscreen").setExecutor(new BroadcastScreen());
        getCommand("clear").setExecutor(new ClearItems(this));
        getCommand("mute").setExecutor(new Mute());
        getCommand("unmute").setExecutor(new Mute());
        getCommand("mutelist").setExecutor(new Mute());
        getCommand("refreshlb").setExecutor(new RefreshLB());
        getCommand("rules").setExecutor(new Rules());
        getCommand("scorelb").setExecutor(new ScoreLeaderboard());

        getCommand("eventscramble").setExecutor(new ScrambleWord());
        getCommand("eventpotion").setExecutor(new RandomPotion(this));

        getCommand("backup-export").setExecutor(new BackupScore(this));
        getCommand("backup-import").setExecutor(new BackupScore(this));


        getServer().getPluginManager().registerEvents(new BreakCounterLB(this), this);
        getServer().getPluginManager().registerEvents(new ScrambleWord(), this);
        getServer().getPluginManager().registerEvents(new ChatChanger(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveChanger(), this);
        getServer().getPluginManager().registerEvents(new Mute(), this);
        getServer().getPluginManager().registerEvents(new PlayerPVP(), this);
        getServer().getPluginManager().registerEvents(new CancelMilk(), this);


        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                backupExport();
            }
        }, 6000L);






    }





    @Override
    public void onDisable() {

    }
}
