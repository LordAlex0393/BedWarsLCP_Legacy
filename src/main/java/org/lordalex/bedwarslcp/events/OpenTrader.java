package org.lordalex.bedwarslcp.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.lordalex.bedwarslcp.utils.Trader;

public class OpenTrader implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e == null) return;
        Player p = (Player) e.getView().getPlayer();
        if(e.getView().getTitle().equals("Торговец")){
            if(e.getCurrentItem() != null
                    && e.getCurrentItem().getItemMeta() != null
                    && e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&l&bБлоки"))){
                Trader.openBlocksMenu((Player) e.getView().getPlayer());
            }
            e.setCancelled(true);
        }
        int materialAmount;
        int itemAmount;

        if(e.getView().getTitle().equals("Блоки")) {
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
                } else if (isEqualsItem(e, "&fСветящийся камень")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.GLOWSTONE), 16, 4);
                }
                else if (isEqualsItem(e, "&fСтекло")) {
                    buyItem(e, Material.CLAY_BRICK, new ItemStack(Material.GLASS), 4, 1);
                }
                else if (isEqualsItem(e,"&f← &eНазад")) {
                    Trader.openGlobalMenu((Player) e.getView().getPlayer());
                    e.setCancelled(true);
                }
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
        return e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage(itemDisplayName));
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
