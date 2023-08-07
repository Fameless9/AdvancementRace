package com.fameless.advancementrace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {

    private Timer timer;

    public TimerCommand(Main main) {
        timer = main.getTimer();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 2) {
                if (args[0].equals("set")) {
                    try {
                        timer.setTime(Integer.parseInt(args[1]));
                        sender.sendMessage(ChatColor.GRAY + "Timer was set to " + args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + "Argument 2 must be an integer.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid usage! Please use: /timer set <time>");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid usage! Please use: /timer set <time>");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        }
        return false;
    }
}
