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

        ItemStack sandstoneStack = new ItemStack(Material.SANDSTONE, 1, (byte) 2);
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
        sandstoneMeta.setLore(sandstoneList);
        sandstoneStack.setItemMeta(sandstoneMeta);

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

        ItemStack pickaxeStack = new ItemStack(Material.GOLD_PICKAXE, 1);
        ItemMeta pickaxeMeta = pickaxeStack.getItemMeta();
        pickaxeMeta.setDisplayName(ColorUtil.getMessage("&l&bКирки"));
        List<String> pickaxeList = new ArrayList<>();
        pickaxeList.add(ColorUtil.getMessage("&e-&7 Каменная кирка"));
        pickaxeList.add(ColorUtil.getMessage("&e-&7 Железная кирка"));
        pickaxeList.add(ColorUtil.getMessage("&e-&7 Алмазная кирка"));
        pickaxeMeta.setLore(pickaxeList);
        pickaxeStack.setItemMeta(pickaxeMeta);

        ItemStack swordStack = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta swordMeta = swordStack.getItemMeta();
        swordMeta.setDisplayName(ColorUtil.getMessage("&l&bМечи"));
        List<String> swordList = new ArrayList<>();
        swordList.add(ColorUtil.getMessage("&e-&7 Железный меч"));
        swordList.add(ColorUtil.getMessage("&e-&a Алмеч"));
        swordList.add(ColorUtil.getMessage("&e-&b Экскалибур"));
        swordList.add(ColorUtil.getMessage("&e-&6 Смертоносец"));
        swordList.add(ColorUtil.getMessage("&e-&c Киллмагедон"));
        swordMeta.setLore(swordList);
        swordStack.setItemMeta(swordMeta);

        ItemStack bowStack = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bowStack.getItemMeta();
        bowMeta.setDisplayName(ColorUtil.getMessage("&l&bЛуки"));
        List<String> bowList = new ArrayList<>();
        bowList.add(ColorUtil.getMessage("&e-&a Лук уровень 1"));
        bowList.add(ColorUtil.getMessage("&e-&b Лук уровень 2"));
        bowList.add(ColorUtil.getMessage("&e-&c Лук уровень 3"));
        bowList.add(ColorUtil.getMessage("&e-&c Лукер"));
        bowList.add(ColorUtil.getMessage("&e-&7 Стрела"));
        bowMeta.setLore(bowList);
        bowStack.setItemMeta(bowMeta);

        ItemStack foodStack = new ItemStack(Material.APPLE, 1);
        ItemMeta foodMeta = foodStack.getItemMeta();
        foodMeta.setDisplayName(ColorUtil.getMessage("&l&bЕда"));
        List<String> foodList = new ArrayList<>();
        foodList.add(ColorUtil.getMessage("&e-&f Еда чемпионов"));
        foodList.add(ColorUtil.getMessage("&e-&7 Жареная свинина"));
        foodList.add(ColorUtil.getMessage("&e-&7 Торт"));
        foodList.add(ColorUtil.getMessage("&e-&7 Золотое яблоко"));
        foodMeta.setLore(foodList);
        foodStack.setItemMeta(foodMeta);

        ItemStack chestStack = new ItemStack(Material.CHEST, 1);
        ItemMeta chestMeta = chestStack.getItemMeta();
        chestMeta.setDisplayName(ColorUtil.getMessage("&l&bСундуки"));
        List<String> chestList = new ArrayList<>();
        chestList.add(ColorUtil.getMessage("&e-&a Сундук"));
        chestList.add(ColorUtil.getMessage("&e-&a Командный сундук"));
        chestMeta.setLore(chestList);
        chestStack.setItemMeta(chestMeta);

        ItemStack potionStack = new ItemStack(Material.POTION, 1);
        ItemMeta potionMeta = potionStack.getItemMeta();
        potionMeta.setDisplayName(ColorUtil.getMessage("&l&bЗелья"));
        List<String> potionList = new ArrayList<>();
        potionList.add(ColorUtil.getMessage("&e- Хилка уровень 1"));
        potionList.add(ColorUtil.getMessage("&e- Хилка уровень 2"));
        potionList.add(ColorUtil.getMessage("&e- Скорость"));
        potionList.add(ColorUtil.getMessage("&e- Зелье регенерации"));
        potionList.add(ColorUtil.getMessage("&e- Сила"));
        potionList.add(ColorUtil.getMessage("&e- Зелье невидимости"));
        potionMeta.setLore(potionList);
        potionStack.setItemMeta(potionMeta);

        ItemStack specialStack = new ItemStack(Material.TNT, 1);
        ItemMeta specialMeta = specialStack.getItemMeta();
        specialMeta.setDisplayName(ColorUtil.getMessage("&l&bЗелья"));
        List<String> specialList = new ArrayList<>();
        specialList.add(ColorUtil.getMessage("&e-&7 Лестница"));
        specialList.add(ColorUtil.getMessage("&e-&7 Паутина"));
        specialList.add(ColorUtil.getMessage("&e-&7 Удочка"));
        specialList.add(ColorUtil.getMessage("&e-&7 Зажигалка"));
        specialList.add(ColorUtil.getMessage("&e-&7 Динамит"));
        specialList.add(ColorUtil.getMessage("&e-&7 Жемчуг края"));
        specialList.add(ColorUtil.getMessage("&e-&b Телепорт домой (&e6 сек.&b)"));
        specialList.add(ColorUtil.getMessage("&e-&b Кость тора"));
        specialList.add(ColorUtil.getMessage("&e- GPS трекер"));
        specialList.add(ColorUtil.getMessage("&e-&b Спасательная платформа"));
        specialList.add(ColorUtil.getMessage("&e-&b Ловушка"));
        specialMeta.setLore(specialList);
        specialStack.setItemMeta(specialMeta);

        ItemStack changingStack = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta changingMeta = changingStack.getItemMeta();
        changingMeta.setDisplayName(ColorUtil.getMessage("&l&bОбмен валют"));
        List<String> changingList = new ArrayList<>();
        changingList.add(ColorUtil.getMessage("&e-&f 1 железо за&6 48 бронзы"));
        changingList.add(ColorUtil.getMessage("&e-&e 1 золото&f за 14 железа"));
        changingList.add(ColorUtil.getMessage("&e-&6 32 бронзы&f за 1 железо"));
        changingList.add(ColorUtil.getMessage("&e-&f 7 железа за&e 1 золото"));
        changingMeta.setLore(changingList);
        changingStack.setItemMeta(changingMeta);

        inv.setItem(0, sandstoneStack);
        inv.setItem(1, armorStack);
        inv.setItem(2, pickaxeStack);
        inv.setItem(3, swordStack);
        inv.setItem(4, bowStack);
        inv.setItem(5, foodStack);
        inv.setItem(6, chestStack);
        inv.setItem(7, potionStack);
        inv.setItem(8, specialStack);
        inv.setItem(13, changingStack);
        p.openInventory(inv);
    }

    public static void openBlocksMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 18, "Блоки");

        ItemStack sandstoneStack = new ItemStack(Material.SANDSTONE, 2, (byte) 2);
        ItemMeta sandstoneMeta = sandstoneStack.getItemMeta();
        sandstoneMeta.setDisplayName(ColorUtil.getMessage("&fГладкий песчаник"));
        List<String> sandstoneList = new ArrayList<>();
        sandstoneList.add(ColorUtil.getMessage("&dЦена: &61 бронза"));
        sandstoneMeta.setLore(sandstoneList);
        sandstoneStack.setItemMeta(sandstoneMeta);

        ItemStack sandstoneStairsStack = new ItemStack(Material.SANDSTONE_STAIRS, 2);
        ItemMeta sandstoneStairsMeta = sandstoneStairsStack.getItemMeta();
        sandstoneStairsMeta.setDisplayName(ColorUtil.getMessage("&fСтупеньки из песчаника"));
        List<String> sandstoneStairsList = new ArrayList<>();
        sandstoneStairsList.add(ColorUtil.getMessage("&dЦена: &63 бронзы"));
        sandstoneStairsMeta.setLore(sandstoneStairsList);
        sandstoneStairsStack.setItemMeta(sandstoneStairsMeta);

        ItemStack enderstoneStairsStack = new ItemStack(Material.ENDER_STONE, 1);
        ItemMeta enderstoneStairsMeta = enderstoneStairsStack.getItemMeta();
        enderstoneStairsMeta.setDisplayName(ColorUtil.getMessage("&fЭндерняк"));
        List<String> enderstoneStairsList = new ArrayList<>();
        enderstoneStairsList.add(ColorUtil.getMessage("&dЦена: &67 бронзы"));
        enderstoneStairsMeta.setLore(enderstoneStairsList);
        enderstoneStairsStack.setItemMeta(enderstoneStairsMeta);

        ItemStack ironblockStairsStack = new ItemStack(Material.IRON_BLOCK, 1);
        ItemMeta ironblockStairsMeta = ironblockStairsStack.getItemMeta();
        ironblockStairsMeta.setDisplayName(ColorUtil.getMessage("&fЖелезный блок"));
        List<String> ironblockStairsList = new ArrayList<>();
        ironblockStairsList.add(ColorUtil.getMessage("&dЦена: &f3 железа"));
        ironblockStairsMeta.setLore(ironblockStairsList);
        ironblockStairsStack.setItemMeta(ironblockStairsMeta);

        ItemStack glowstoneStairsStack = new ItemStack(Material.GLOWSTONE, 4);
        ItemMeta glowstoneStairsMeta = glowstoneStairsStack.getItemMeta();
        glowstoneStairsMeta.setDisplayName(ColorUtil.getMessage("&fСветящийся камень"));
        List<String> glowstoneStairsList = new ArrayList<>();
        glowstoneStairsList.add(ColorUtil.getMessage("&dЦена: &616 бронзы"));
        glowstoneStairsMeta.setLore(glowstoneStairsList);
        glowstoneStairsStack.setItemMeta(glowstoneStairsMeta);

        ItemStack glassStairsStack = new ItemStack(Material.GLASS, 1);
        ItemMeta glassStairsMeta = glassStairsStack.getItemMeta();
        glassStairsMeta.setDisplayName(ColorUtil.getMessage("&fСтекло"));
        List<String> glassStairsList = new ArrayList<>();
        glassStairsList.add(ColorUtil.getMessage("&dЦена: &64 бронзы"));
        glassStairsMeta.setLore(glassStairsList);
        glassStairsStack.setItemMeta(glassStairsMeta);

        ItemStack bedStack = new ItemStack(Material.BED, 1);
        ItemMeta bedMeta = bedStack.getItemMeta();
        bedMeta.setDisplayName(ColorUtil.getMessage("&f← &eНазад"));
        bedStack.setItemMeta(bedMeta);

        inv.setItem(0, sandstoneStack);
        inv.setItem(1, sandstoneStairsStack);
        inv.setItem(2, enderstoneStairsStack);
        inv.setItem(3, ironblockStairsStack);
        inv.setItem(4, glowstoneStairsStack);
        inv.setItem(5, glassStairsStack);
        inv.setItem(13, bedStack);

        p.openInventory(inv);
    }
}
