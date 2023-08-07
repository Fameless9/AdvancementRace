package com.fameless.advancementrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdvancementListener implements Listener {

    private Main main;

    public AdvancementListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!main.getAdvancementMap().containsKey(event.getPlayer().getUniqueId())) main.getAdvancementMap().put(event.getPlayer().getUniqueId(), 0);
        main.getBossbarManager().createBossbar(event.getPlayer());
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getKey().toString().contains("minecraft:recipe")) return;
        int newValue = main.getAdvancementMap().get(event.getPlayer().getUniqueId()) + 1;
        main.getAdvancementMap().put(event.getPlayer().getUniqueId(), newValue);
        main.getBossbarManager().updateBossbar(event.getPlayer());
        if (newValue == main.getRequiredAdvancements()) {
            main.getTimer().setRunning(false);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "GG", event.getPlayer().getName() + " has won the game!", 20, 60, 20);
            }
        }
    }
}
