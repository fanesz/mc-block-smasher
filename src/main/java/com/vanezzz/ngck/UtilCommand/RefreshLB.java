package com.vanezzz.ngck.UtilCommand;

import com.vanezzz.ngck.OtherEvent.lbData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import static com.vanezzz.ngck.OtherEvent.BreakCounterLB.*;

public class RefreshLB implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            startTask(player);
            BreakCounterLB(player);
            try{
                scoreboard.registerNewTeam(player.getName());
                Team team = scoreboard.getTeam(player.getName()); team.setSuffix(" : 0");
            }catch (IllegalArgumentException e){}
            boolean newData = true;
            for (lbData playerList : dataList){
                if(playerList.nick.equals(player.getName())){
                    newData = false;
                }
            }
            if(newData){
                dataList.add((new lbData(player.getName(), 0)));
            }
        }



        return true;
    }

}
