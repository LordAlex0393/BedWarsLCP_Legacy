package org.lordalex.bedwarslcp.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.lordalex.bedwarslcp.utils.BedWarsUtil;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.lordalex.bedwarslcp.utils.Trader;

import java.util.ArrayList;
import java.util.List;

import static org.lordalex.bedwarslcp.BedWarsLCP.mapConfig;

public class OpenTrader implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e == null) return;
        Player p = (Player) e.getView().getPlayer();

        if(e.getView().getTitle().equals("Торговец")){
            if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
                if(isEqualsItem(e, "&l&bБлоки")){
                    Trader.openBlocksMenu((Player) e.getView().getPlayer());
                }
                else if(isEqualsItem(e, "&l&bБроня")){
                    Trader.openArmorMenu((Player) e.getView().getPlayer());
                }
                else if(isEqualsItem(e, "&l&bКирки")){
                    Trader.openPickaxeMenu((Player) e.getView().getPlayer());
                }
                else if(isEqualsItem(e, "&l&bМечи")){
                    Trader.openSwordMenu((Player) e.getView().getPlayer());
                }
                else if(isEqualsItem(e, "&l&bЕда")){
                    Trader.openFoodMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
        else if(e.getView().getTitle().equals("Блоки")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {

                if (isEqualsItem(e, "&fГладкий песчаник")) {
                    ItemStack sandstoneItem = new ItemStack(Material.SANDSTONE, 1, (byte) 2);
                    buyItem(e, Material.CLAY_BRICK, sandstoneItem, 1, 2);
                }
                else if (isEqualsItem(e, "&fСтупеньки из песчаника")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.SANDSTONE_STAIRS), 3, 2);
                }
                else if (isEqualsItem(e, "&fЭндерняк")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.ENDER_STONE), 7, 1);
                }
                else if (isEqualsItem(e, "&fЖелезный блок")) {
                    buyItem(e, Material.IRON_INGOT, new ItemStack(Material.IRON_BLOCK), 3, 1);
                }
                else if (isEqualsItem(e, "&fСветящийся камень")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.GLOWSTONE), 16, 4);
                }
                else if (isEqualsItem(e, "&fСтекло")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.GLASS), 4, 1);
                }
                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
        else if(e.getView().getTitle().equals("Броня")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {

                if (isEqualsItem(e, "&fЖелезный сет")) {
                    ItemStack ironLegsStack = new ItemStack(Material.IRON_LEGGINGS, 1);
                    ItemMeta ironLegsMeta = ironLegsStack.getItemMeta();
                    ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                    ironLegsStack.setItemMeta(ironLegsMeta);

                    ItemStack ironBootsStack = new ItemStack(Material.IRON_BOOTS, 1);
                    ItemMeta ironBootsMeta = ironBootsStack.getItemMeta();
                    ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                    ironBootsStack.setItemMeta(ironBootsMeta);

                    ItemStack ironHelmetStack = new ItemStack(Material.IRON_HELMET, 1);
                    ItemMeta ironHelmetMeta = ironHelmetStack.getItemMeta();
                    ironHelmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                    ironHelmetStack.setItemMeta(ironHelmetMeta);

                    buyItem(e, Material.CLAY_BRICK, ironHelmetStack, 2, 1);
                    buyItem(e, Material.CLAY_BRICK, ironLegsStack, 2, 1);
                    buyItem(e, Material.CLAY_BRICK, ironBootsStack, 2, 1);
                }
                else if (isEqualsItem(e, "&aЖелезка уровень 1")) {
                    ItemStack ironChestplateStack = new ItemStack(Material.IRON_CHESTPLATE, 1);
                    ItemMeta ironChestplateMeta = ironChestplateStack.getItemMeta();
                    ironChestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                    ironChestplateStack.setItemMeta(ironChestplateMeta);
                    buyItem(e, Material.IRON_INGOT, ironChestplateStack, 1, 1);
                }
                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
        else if(e.getView().getTitle().equals("Кирки")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                if (isEqualsItem(e, "&bКаменная кирка")) {
                    ItemStack stonePickaxeStack = new ItemStack(Material.STONE_PICKAXE, 1);
                    ItemMeta stonePickaxeMeta = stonePickaxeStack.getItemMeta();
                    stonePickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    stonePickaxeStack.setItemMeta(stonePickaxeMeta);
                    buyItem(e, Material.CLAY_BRICK, stonePickaxeStack, 4, 1);
                }
                else if (isEqualsItem(e, "&bЖелезная кирка")) {
                    ItemStack ironPickaxeStack = new ItemStack(Material.IRON_PICKAXE, 1);
                    ItemMeta ironPickaxeMeta = ironPickaxeStack.getItemMeta();
                    ironPickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    ironPickaxeStack.setItemMeta(ironPickaxeMeta);
                    buyItem(e, Material.IRON_INGOT, ironPickaxeStack, 2, 1);
                }
                else if (isEqualsItem(e, "&bАлмазная кирка") && e.getCurrentItem().getItemMeta().getEnchants().get(Enchantment.DIG_SPEED) == 1) {
                    ItemStack diamond1PickaxeStack = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta diamond1PickaxeMeta = diamond1PickaxeStack.getItemMeta();
                    diamond1PickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    diamond1PickaxeStack.setItemMeta(diamond1PickaxeMeta);
                    buyItem(e, Material.GOLD_INGOT, diamond1PickaxeStack, 2, 1);
                }
                else if (isEqualsItem(e, "&bАлмазная кирка") && e.getCurrentItem().getItemMeta().getEnchants().get(Enchantment.DIG_SPEED) == 3) {
                    ItemStack diamond1PickaxeStack = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta diamond1PickaxeMeta = diamond1PickaxeStack.getItemMeta();
                    diamond1PickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    diamond1PickaxeStack.setItemMeta(diamond1PickaxeMeta);
                    buyItem(e, Material.GOLD_INGOT, diamond1PickaxeStack, 8, 1);
                }
                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
        else if(e.getView().getTitle().equals("Мечи")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                if (isEqualsItem(e, "&bЖелезный меч")) {
                    ItemStack ironSwordStack = new ItemStack(Material.IRON_SWORD, 1);
                    ItemMeta ironSwordMeta = ironSwordStack.getItemMeta();
                    ironSwordMeta.setDisplayName(ColorUtil.getMessage("&bЖелезный меч"));
                    ironSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    ironSwordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    ironSwordStack.setItemMeta(ironSwordMeta);
                    buyItem(e, Material.CLAY_BRICK, ironSwordStack, 3, 1);
                }
                else if (isEqualsItem(e, "&aАлмеч")) {
                    ItemStack diamondSwordStack = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta diamondSwordMeta = diamondSwordStack.getItemMeta();
                    diamondSwordMeta.setDisplayName(ColorUtil.getMessage("&aАлмеч"));
                    diamondSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    diamondSwordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    diamondSwordStack.setItemMeta(diamondSwordMeta);
                    buyItem(e, Material.IRON_INGOT, diamondSwordStack, 1, 1);
                }
                else if (isEqualsItem(e, "&bЭкскалибур")){
                    ItemStack excaliburSwordStack = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta excaliburSwordMeta = excaliburSwordStack.getItemMeta();
                    excaliburSwordMeta.setDisplayName(ColorUtil.getMessage("&bЭкскалибур"));
                    excaliburSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    excaliburSwordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    excaliburSwordMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    excaliburSwordStack.setItemMeta(excaliburSwordMeta);
                    buyItem(e, Material.IRON_INGOT, excaliburSwordStack, 5, 1);
                }
                else if (isEqualsItem(e, "&6Смертоносец")){
                    ItemStack deathBringerSwordStack = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta deathBringerSwordMeta = deathBringerSwordStack.getItemMeta();
                    deathBringerSwordMeta.setDisplayName(ColorUtil.getMessage("&6Смертоносец"));
                    deathBringerSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                    deathBringerSwordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    deathBringerSwordMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    deathBringerSwordStack.setItemMeta(deathBringerSwordMeta);
                    buyItem(e, Material.GOLD_INGOT, deathBringerSwordStack, 6, 1);
                }
                else if (isEqualsItem(e, "&cКиллмагедон")){
                    ItemStack killmagedonSwordStack = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta killmagedonSwordMeta = killmagedonSwordStack.getItemMeta();
                    killmagedonSwordMeta.setDisplayName(ColorUtil.getMessage("&cКиллмагедон"));
                    killmagedonSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
                    killmagedonSwordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    killmagedonSwordMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
                    killmagedonSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
                    killmagedonSwordStack.setItemMeta(killmagedonSwordMeta);
                    buyItem(e, Material.GOLD_INGOT, killmagedonSwordStack, 30, 1);
                }

                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
        else if(e.getView().getTitle().equals("Еда")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {

                if (isEqualsItem(e, "&fЕда чемпионов")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.APPLE), 1, 1);
                }
                else if (isEqualsItem(e, "&fЖареная свинина")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.GRILLED_PORK), 2, 1);
                }
                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onTrade(PlayerInteractEntityEvent e){
        Entity ent = e.getRightClicked();
        if(ent instanceof Villager) {
            e.setCancelled(true);
            Trader.openGlobalMenu(e.getPlayer());
        }
    }
//    @EventHandler
//    public void onVillagerSpawn(PlayerInteractEvent e){
//        if(e.getItem() == null) return;
//        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
//        if (!(e.getItem().getType() == Material.MONSTER_EGG)) return;
//        Player p = e.getPlayer();
//        e.setCancelled(true);
//        Villager vil = (Villager) p.getLocation().getWorld().spawnEntity(p.getLocation(),EntityType.VILLAGER);
//        vil.setCustomName(ChatColor.YELLOW + "Торговец");
//        vil.setCustomNameVisible(true);
//        //vil.setAI(false);
//        vil.setAdult();
//        vil.setNoDamageTicks(200);
//    }

    private boolean checkItem(Player p, Material mat, int amount){
        return p.getInventory().containsAtLeast(new ItemStack(mat), amount);
    }
    private boolean isShiftClick(InventoryClickEvent e){
        return (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT);
    }
    private boolean isEqualsItem(InventoryClickEvent e, String itemDisplayName){
        if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            return e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage(itemDisplayName));
        }
        else{
            return false;
        }
    }
    private boolean buyItemShift(Player p, Material material, ItemStack item, int materialAmount, int itemAmount){
        int factor = 10;
        for(factor = 10; factor > 0; factor--){
            buyItemNormal(p, material, item, materialAmount, itemAmount);
        }
        return true;
    }
    private boolean buyItemNormal(Player p, Material material, ItemStack item, int materialAmount, int itemAmount){
        if (checkItem(p, material, materialAmount)) {
            p.getInventory().removeItem(new ItemStack(material, materialAmount));
            item.setAmount(itemAmount);
            p.getInventory().addItem(item);
            return true;
        }
        else{
            return false;
        }
    }
    private boolean buyItem(InventoryClickEvent e, Material material, ItemStack item, int materialAmount, int itemAmount){
        Player p = (Player) e.getView().getPlayer();
        if (isShiftClick(e)) {
            buyItemShift(p, material, item, materialAmount, itemAmount);
        }
        else {
            buyItemNormal(p, material, item, materialAmount, itemAmount);
        }
        e.setCancelled(true);
        return true;
    }
}
