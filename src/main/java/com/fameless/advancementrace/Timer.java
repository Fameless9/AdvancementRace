package com.fameless.advancementrace;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private int time;
    private boolean running;
    private Main main;

    public Timer(int time, boolean running, Main main) {
        this.time = time;
        this.running = running;
        this.main = main;
        run();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sendActionbar(player);
                }
                if (isRunning()) {
                    setTime(getTime() + 1);
                }
            }
        }.runTaskTimer(main, 0,20);
    }
    private void sendActionbar(Player player) {

        int days = time/86400;
        int hours = time/3600 % 24;
        int minutes = time/60 % 60;
        int seconds = time % 60;

        StringBuilder message = new StringBuilder();
        if (days >= 1) {
            message.append(days).append("d ");
        }
        if (hours >= 1) {
            message.append(hours).append("h ");
        }
        if (minutes >= 1) {
            message.append(minutes).append("m ");
        }
        message.append(seconds).append("s ");

        if (!isRunning()) {
            message.append("(paused)");
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + message));
    }
}
