package com.fameless.advancementrace;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstTimeConfig implements Listener {

    private Main main;

    public FirstTimeConfig(Main main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (main.getConfig().getBoolean("first-time-configuration")) return;
        event.getPlayer().sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "      First Time Configuration   " +
                ChatColor.GREEN + ChatColor.BOLD + "\n Thanks for downloading my plugin. To make sure everything is working like it should," +
                " follow these short instructions. To make the /reset confirm command work, you need to configure a " +
                "startup script. To do that, create a new .txt file in your server folder. Open it up and put the following in there:" +
                ChatColor.GRAY + "\njava -Xmx8G -jar paper-1.20.1-68.jar\n" +
                "pause\n" +
                ChatColor.GREEN + ChatColor.BOLD + " (Important) Save the file and rename it to: start.sh (requires file name extensions)" +
                " Now go into your spigot.yml and make sure the restart-script is set to './start.sh'");
        main.getConfig().set("first-time-configuration", true);
    }
}
