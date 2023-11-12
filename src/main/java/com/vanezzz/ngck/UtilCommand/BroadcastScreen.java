package com.vanezzz.ngck.UtilCommand;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastScreen implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;
        }
        if(args.length < 1){
            sender.sendMessage("Wrong Args!, /sbscreen <text>");
            return true;
        }
        String text = String.join(" ", args);

        if(sender.isOp()){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendTitle(text, "", 10, 50, 10);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
            }

        }


        return true;
    }
}
