package com.vanezzz.ngck.UtilCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Rules implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String[] rules = {
                "§e=============================",
                "§e>> §fRules§8:",
                "§e- §7Dilarang melakukan segala macam hack",
                "§e- §7Dilarang x-ray dan bug abuse",
                "§e- §7Dilarang boosting player / teaming, seperti berbagi resource",
                "§e- §7Skor dibawah -30 akan mendapat efek slowness permanent",
                "§e- §7Skor dibawah -100 akan mendapat efek blindness permanent",
                "§e- §7Memukul player akan mengurangi skor sebanyak -3/meele hit, -5/range hit",
                "§e- §7Membunuh player akan mengurangi skor sebanyak 10% dari total skor kamu, dan mendapat efek Slowness dan Blindness selama 15 detik",
                "§e============================="
        };
        sender.sendMessage(String.join("\n", rules));
        return true;
    }
}
