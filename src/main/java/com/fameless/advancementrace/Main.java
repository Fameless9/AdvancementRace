package com.fameless.advancementrace;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private Timer timer;
    private int requiredAdvancements = 15;
    private HashMap<UUID, Integer> advancementMap = new HashMap<>();
    private BossbarManager bossbarManager;
    private GUICommand guiCommand;

    @Override
    public void onLoad() {

        try {
            if (!getConfig().getBoolean("reset")) {
                return;
            }
            File world = new File(Bukkit.getWorldContainer(),"world");
            File advancements = new File(world, "advancements");
            File nether = new File(Bukkit.getWorldContainer(),"world_nether");
            File netherAdvancements = new File(nether, "advancements");
            File end = new File(Bukkit.getWorldContainer(),"world_the_end");
            File endAdvancements = new File(end, "advancements");

            FileUtils.deleteDirectory(advancements);
            FileUtils.deleteDirectory(netherAdvancements);
            FileUtils.deleteDirectory(endAdvancements);
            getConfig().set("reset", false);
        } catch (IOException e) {
            e.printStackTrace();
        }


        super.onLoad();
    }

    @Override
    public void onEnable() {

        saveConfig();

        bossbarManager = new BossbarManager(this);
        timer = new Timer(0, false, this);
        guiCommand = new GUICommand(this);

        Bukkit.getPluginManager().registerEvents(new AdvancementListener(this),this);
        Bukkit.getPluginManager().registerEvents(new FirstTimeConfig(this), this);
        Bukkit.getPluginManager().registerEvents(guiCommand,this);

        getCommand("timer").setExecutor(new TimerCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));
        getCommand("stop").setExecutor(new StopCommand(this));
        getCommand("reset").setExecutor(new ResetCommand(this));
        getCommand("menu").setExecutor(guiCommand);
    }

    public Timer getTimer() {
        return timer;
    }

    public int getRequiredAdvancements() {
        return requiredAdvancements;
    }
    public void setRequiredAdvancements(int requiredAdvancements) {
        this.requiredAdvancements = requiredAdvancements;
    }

    public HashMap<UUID, Integer> getAdvancementMap() {
        return advancementMap;
    }

    public BossbarManager getBossbarManager() {
        return bossbarManager;
    }
}
