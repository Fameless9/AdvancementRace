package com.fameless.advancementrace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUICommand implements CommandExecutor, Listener {

    private Main main;

    public GUICommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        openGUI(player);
        return false;
    }
    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase("Settings")) return;

        event.setCancelled(true);

        if (event.getSlot() == 4) {
            if (event.getClick().equals(ClickType.LEFT)) {
                main.setRequiredAdvancements(main.getRequiredAdvancements() + 1);
                openGUI((Player) event.getWhoClicked());
            } else if (event.getClick().equals(ClickType.RIGHT)) {
                if (main.getRequiredAdvancements() == 1) return;
                main.setRequiredAdvancements(main.getRequiredAdvancements() - 1);
                openGUI((Player) event.getWhoClicked());
            }
        }
    }

    private void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, "Settings");

        inventory.setItem(4, getItem(new ItemStack(Material.CLOCK),
                ChatColor.GOLD.toString() + ChatColor.BOLD + "Required Advancements",
                ChatColor.GRAY + "Set the required amount of Advancements to win!", "",
                ChatColor.BLUE + "[" + ChatColor.GRAY + "Left Click: +1" + ChatColor.BLUE + "]",
                ChatColor.BLUE + "[" + ChatColor.GRAY + "Right Click: -1" + ChatColor.BLUE + "]", "","",
                ChatColor.BLUE + "[" + ChatColor.GRAY + "Current Amount: " + main.getRequiredAdvancements() + ChatColor.BLUE + "]"));
        player.openInventory(inventory);
    }
    private ItemStack getItem(ItemStack item, String name, String ...lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(s);
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
