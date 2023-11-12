package com.vanezzz.ngck.EditScore;


import com.vanezzz.ngck.OtherEvent.BreakCounterLB;
import com.vanezzz.ngck.OtherEvent.lbData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetScore implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;}
        Player player = (Player) sender;

        if (args.length < 2) {
            sender.sendMessage("§e>>§f Wrong Args!, /scoreset <player> <score>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§e>>§f Player not found.");
            return true;
        }

        for (lbData playerList : BreakCounterLB.dataList) {
            if(playerList.nick.equals(target.getName())) {

                playerList.count = Integer.parseInt(args[1]);
                sender.sendMessage("§e>> §7§oSuccessfully set §b§o"+args[1]+" score §7§o to " + target.getName() + " (score: "+String.valueOf(playerList.count)+")");
                target.sendMessage("§e>> §7§oYour score has been set to §b§o"+args[1]+" score!" + "§7§o (score: "+String.valueOf(playerList.count)+")");
            }
        }


        return true;
    }
}