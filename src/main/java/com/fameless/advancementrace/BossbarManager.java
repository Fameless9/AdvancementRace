package com.fameless.advancementrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class BossbarManager {

    private Main main;
    private HashMap<Player, BossBar> bossBarHashMap = new HashMap<>();

    public BossbarManager(Main main) {
        this.main = main;
    }

    public void createBossbar(Player player) {
        if (bossBarHashMap.containsKey(player)) return;
        BossBar bossBar = Bukkit.createBossBar(ChatColor.GOLD.toString() + ChatColor.BOLD + "Advancements: " + main.getAdvancementMap().get(player.getUniqueId()), BarColor.YELLOW, BarStyle.SOLID);
        bossBar.addPlayer(player);
        bossBarHashMap.put(player, bossBar);
    }
    public void updateBossbar(Player player) {
        BossBar bossBar = bossBarHashMap.get(player);
        bossBar.setTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "Advancements: " + main.getAdvancementMap().get(player.getUniqueId()));
    }
}
