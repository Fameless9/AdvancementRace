package com.fameless.advancementrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {

    private Main main;

    public ResetCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            if (args[0].equals("confirm")) {
                main.getConfig().set("reset",true);
                main.saveConfig();
                Bukkit.spigot().restart();
            } else {
                commandSender.sendMessage(ChatColor.RED + "Usage: /reset confirm");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Usage: /reset confirm");
        }
        return false;
    }
}
