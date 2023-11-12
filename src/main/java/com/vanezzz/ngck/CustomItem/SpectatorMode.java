package com.vanezzz.ngck.CustomItem;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpectatorMode {
    public static ItemStack Spectatoritem;

    public static void init() {
        Spectatoritem();

    }

    private static void Spectatoritem() {
        ItemStack item = new ItemStack(Material.GRAY_WOOL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Right-Click to Spectate");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        Spectatoritem = item;
    }
}
