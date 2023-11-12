package com.vanezzz.ngck;
import com.vanezzz.ngck.CustomItem.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GiveItem implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only player can use that command!");
            return true;}
        Player player = (Player) sender;

        System.out.println("de1");
        if (cmd.getName().equalsIgnoreCase("itemspectator")) {
            player.getInventory().addItem(SpectatorMode.Spectatoritem);
        } else if(cmd.getName().equalsIgnoreCase("itemreplacerwand")) {
            System.out.println("de2");
            player.getInventory().addItem(ReplacerWand.ReplacerWand);
        }


        return true;
    }
}

