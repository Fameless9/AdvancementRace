package com.fameless.advancementrace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private Main main;

    public StartCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            if (!main.getTimer().isRunning()) {
                main.getTimer().setRunning(true);
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
        }
        return false;
    }
}
