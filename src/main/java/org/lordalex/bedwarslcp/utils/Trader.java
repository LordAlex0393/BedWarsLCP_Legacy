package org.lordalex.bedwarslcp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SandstoneType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sandstone;

import java.util.ArrayList;
import java.util.List;

public class Trader {
    public static void openGlobalMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Торговец");

        ItemStack sandstoneStack = new ItemStack(Material.SANDSTONE, 1);
        ItemMeta sandstoneMeta = sandstoneStack.getItemMeta();
        sandstoneStack.setData(new Sandstone(SandstoneType.SMOOTH));
        sandstoneMeta.setDisplayName(ColorUtil.getMessage("&l&bБлоки"));
        List<String> sandstoneList = new ArrayList<>();
        sandstoneList.add(ColorUtil.getMessage("&e- &7Песчаник"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Ступеньки из песчаника"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Эндерняк"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Железный блок"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Светокамень"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Стекло"));
        sandstoneList.add(ColorUtil.getMessage("&e- &7Блок слизи"));
        sandstoneMeta.setLore(sandstoneList);
        sandstoneStack.setItemMeta(sandstoneMeta);

        inv.setItem(0, sandstoneStack);

        ItemStack armorStack = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta armorMeta = armorStack.getItemMeta();
        armorMeta.setDisplayName(ColorUtil.getMessage("&l&bБроня"));
        List<String> armorList = new ArrayList<>();
        armorList.add(ColorUtil.getMessage("&e- &7Железный сет"));
        armorList.add(ColorUtil.getMessage("&e- &aЖелезка уровень 1"));
        armorList.add(ColorUtil.getMessage("&e- &aЖелезка уровень 2"));
        armorList.add(ColorUtil.getMessage("&e- &7Алмазный сет"));
        armorList.add(ColorUtil.getMessage("&e- &fАлмазка уровень 0"));
        armorList.add(ColorUtil.getMessage("&e- &aАлмазка уровень 1"));
        armorList.add(ColorUtil.getMessage("&e- &bАлмазка уровень 2"));
        armorList.add(ColorUtil.getMessage("&e- &cАлмазка уровень 3"));
        armorMeta.setLore(armorList);
        armorStack.setItemMeta(armorMeta);

        inv.setItem(1, armorStack);
        p.openInventory(inv);
    }

    public static void openBlocksMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 18, "Блоки");

        ItemStack sandstoneStack = new ItemStack(Material.SANDSTONE, 2);
        ItemMeta sandstoneMeta = sandstoneStack.getItemMeta();
        sandstoneStack.setData(new Sandstone(SandstoneType.SMOOTH));
        sandstoneMeta.setDisplayName(ColorUtil.getMessage("&fГладкий песчаник"));
        List<String> sandstoneList = new ArrayList<>();
        sandstoneList.add(ColorUtil.getMessage("&dЦена: &61 бронза"));
        sandstoneMeta.setLore(sandstoneList);
        sandstoneStack.setItemMeta(sandstoneMeta);
        inv.setItem(0, sandstoneStack);

        ItemStack sandstoneStairsStack = new ItemStack(Material.SANDSTONE_STAIRS, 2);
        ItemMeta sandstoneStairsMeta = sandstoneStairsStack.getItemMeta();
        sandstoneStairsMeta.setDisplayName(ColorUtil.getMessage("&fСтупеньки из песчаника"));
        List<String> sandstoneStairsList = new ArrayList<>();
        sandstoneStairsList.add(ColorUtil.getMessage("&dЦена: &63 бронзы"));
        sandstoneStairsMeta.setLore(sandstoneStairsList);
        sandstoneStairsStack.setItemMeta(sandstoneStairsMeta);
        inv.setItem(1, sandstoneStairsStack);

        ItemStack bedStack = new ItemStack(Material.BED, 2);
        ItemMeta bedMeta = bedStack.getItemMeta();
        bedMeta.setDisplayName(ColorUtil.getMessage("&f← &eНазад"));
        bedStack.setItemMeta(bedMeta);
        inv.setItem(13, bedStack);

        p.openInventory(inv);
    }
}
