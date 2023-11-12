package com.vanezzz.ngck.EditScore;


import com.vanezzz.ngck.OtherEvent.BreakCounterLB;
import com.vanezzz.ngck.OtherEvent.lbData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckScore implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;}
        Player player = (Player) sender;

        if(sender.isOp()){
            if (args.length == 0) {
                for (lbData playerList : BreakCounterLB.dataList) {
                    if(playerList.nick.equals(sender.getName())) {
                        String totalBreak = playerList.count >= 1000 ? String.format("%.1fk", playerList.count / 1000.0) : String.valueOf(playerList.count);
                        sender.sendMessage("§e>> §7§oYour score: §b§o" + totalBreak);
                    }
                }
            } else if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§e>>§f Player not found.");
                    return true;
                }
                for (lbData playerList : BreakCounterLB.dataList) {
                    if(playerList.nick.equals(target.getName())) {
                        String totalBreak = playerList.count >= 1000 ? String.format("%.1fk", playerList.count / 1000.0) : String.valueOf(playerList.count);
                        sender.sendMessage("§e>> §7§o"+target.getName()+"'s score: §b§o" + totalBreak);
                    }
                }
            } else {
                sender.sendMessage("§e>>§f Wrong command!, /score  |  /score <player>");
                return true;
            }

        } else {
            if (args.length != 0) {
                sender.sendMessage("§e>>§f Wrong Command!, /score");
                return true;
            }
            for (lbData playerList : BreakCounterLB.dataList) {
                if(playerList.nick.equals(sender.getName())) {
                    String totalBreak = playerList.count >= 1000 ? String.format("%.1fk", playerList.count / 1000.0) : String.valueOf(playerList.count);
                    sender.sendMessage("§e>> §7§oYour score: §b§o" + totalBreak);
                }
            }

        }








        return true;
    }
}