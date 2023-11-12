package com.vanezzz.ngck.UtilCommand;

import com.vanezzz.ngck.NGCK;
import com.vanezzz.ngck.OtherEvent.BreakCounterLB;
import com.vanezzz.ngck.OtherEvent.lbData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.util.Scanner;

import static com.vanezzz.ngck.OtherEvent.BreakCounterLB.*;

public class BackupScore implements CommandExecutor {
    private static NGCK ngck;
    public BackupScore(NGCK ngck) {
        this.ngck = ngck;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equals("backup-export")) {
            String playerData = "";
            int totalData = 0;
            for (lbData playerList : BreakCounterLB.dataList) {
                playerData += playerList.nick + "-" + String.valueOf(playerList.count) + ",";
                totalData += 1;
            }
            if (playerData.endsWith(",")) {
                playerData = playerData.substring(0, playerData.length() - 1);
            }
            try {
                FileWriter writer = new FileWriter("backup-manual.txt", false);
                writer.write(playerData);
                writer.close();
                sender.sendMessage("§7>> " + String.valueOf(totalData) + " Data exported to backup.txt");
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("§7>> Error exporting nickname to backup.txt");
            }

        } else if (cmd.getName().equals("backup-import")) {
            File file = null;
            Scanner scanner = null;
            String playerData = "";
            if (args.length == 1) {
                if (args[0].equals("manual")) {
                    file = new File("backup-manual.txt");
                }
            } else {
                file = new File("backup.txt");
            }

            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    playerData += line;
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            String[] playerDataList = playerData.split(",");
            int totalBackuped = 0;
            for (String playerDatas : playerDataList) {
                String[] playerDatasSplit = playerDatas.split("-");
                String playerNick = playerDatasSplit[0];
                int playerCount = Integer.parseInt(playerDatasSplit[1]);
                for (lbData playerList : BreakCounterLB.dataList) {
                    if (playerList.nick.equals(playerNick)) {
                        playerList.count = playerCount; totalBackuped+=1;
                        break;
                    }
                }
            }
            sender.sendMessage("§7>>> "+totalBackuped +" data successfully backuped!");
        }
        return true;
    }

    public static void backupExport() {
        System.out.println("[autotask] starting...");
        String playerData = "";
        int totalData = 0;
        for (lbData playerList : dataList) {
            playerData += playerList.nick + "-" + String.valueOf(playerList.count) + ",";
            totalData += 1;
        }
        if (playerData.endsWith(",")) {
            playerData = playerData.substring(0, playerData.length() - 1);
        }
        try {
            FileWriter writer = new FileWriter("backup.txt", false);
            writer.write("");
            writer.write(playerData);
            writer.close();
            System.out.println("[autotask] " + String.valueOf(totalData) + " Data exported to backup.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[autotask] Error exporting nickname to backup.txt");
        }
        Bukkit.getScheduler().runTaskLater(ngck, new Runnable() {
            public void run() {
                backupExport();
            }
        }, 6000L);
    }
}
