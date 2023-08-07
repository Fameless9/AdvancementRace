package com.fameless.advancementrace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    private Main main;

    public StopCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            if (main.getTimer().isRunning()) {
                main.getTimer().setRunning(false);
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
        }
        return false;
    }
}
