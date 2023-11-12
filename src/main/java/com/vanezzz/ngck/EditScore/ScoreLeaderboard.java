package com.vanezzz.ngck.EditScore;

import com.vanezzz.ngck.OtherEvent.BreakCounterLB;
import com.vanezzz.ngck.OtherEvent.lbData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ScoreLeaderboard implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String dataList = "§e=============================\n§e>> §fLeaderboard§8:\n";
        int counting = 1;
        for (lbData playerList : BreakCounterLB.dataList) {
            String totalBreak = playerList.count >= 1000 ? String.format("%.1fk", playerList.count / 1000.0) : String.valueOf(playerList.count);
           dataList += "§e" + String.valueOf(counting) + ". §f" + playerList.nick + "§7: §b" + String.valueOf(totalBreak);
        }
        dataList += "\n§e=============================";
        sender.sendMessage(dataList);


        return true;
    }
}
