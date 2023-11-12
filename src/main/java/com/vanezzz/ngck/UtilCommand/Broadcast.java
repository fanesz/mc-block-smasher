package com.vanezzz.ngck.UtilCommand;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;
        }
        if(args.length < 1){
            sender.sendMessage("Wrong Args!, /sb <text>");
            return true;
        }
        String text = String.join(" ", args);

        if(sender.isOp()){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage("** §dSuper-Broadcast §ffrom §a"+sender.getName()+" §f** : §d"+text);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1.5f);
            }

        }


        return true;
    }
}
