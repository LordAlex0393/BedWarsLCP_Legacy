package org.lordalex.bedwarslcp.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.lordalex.bedwarslcp.utils.Trader;

public class OpenTrader implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();
        if(e.getView().getTitle().equals("Торговец")){
            if(e.getCurrentItem() != null
                    && e.getCurrentItem().getItemMeta() != null
                    && e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&l&bБлоки"))){
                Trader.openBlocksMenu((Player) e.getView().getPlayer());
            }
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equals("Блоки")){
            if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&fГладкий песчаник"))){

                    if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
                        if (checkItem(p, Material.CLAY_BRICK, 10)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 10));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 20));
                        }
                    } else {
                        if (checkItem(p, Material.CLAY_BRICK, 1)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 1));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 2));
                        }
                    }
                }
                else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&fСтупеньки из песчаника"))){

                    if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
                        if (checkItem(p, Material.CLAY_BRICK, 15)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 15));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE_STAIRS, 10));
                        }
                    } else {
                        if (checkItem(p, Material.CLAY_BRICK, 3)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 3));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 2));
                        }
                    }
                }
                else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&fСтупеньки из песчаника"))){

                    if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
                        if (checkItem(p, Material.CLAY_BRICK, 15)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 15));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE_STAIRS, 10));
                        }
                    } else {
                        if (checkItem(p, Material.CLAY_BRICK, 3)) {
                            p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 3));
                            p.getInventory().addItem(new ItemStack(Material.SANDSTONE_STAIRS, 2));
                        }
                    }
                }
            }
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equals("Блоки")){
            if(e.getCurrentItem() != null
                    && e.getCurrentItem().getItemMeta() != null
                    && e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage("&f← &eНазад"))){
                Trader.openGlobalMenu((Player) e.getView().getPlayer());
            }
            e.setCancelled(true);
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
    @EventHandler
    public void onSpawn(PlayerInteractEvent e){

        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.MONSTER_EGG)) return;
        Player p = e.getPlayer();
        Villager vil = (Villager) p.getLocation().getWorld().spawnEntity(p.getLocation(),EntityType.VILLAGER);
        vil.setCustomName(ChatColor.YELLOW + "Торговец");
        vil.setCustomNameVisible(true);
        //vil.setAI(false);
        vil.setAdult();
        vil.setNoDamageTicks(200);
    }

    private boolean checkItem(Player p, Material mat, int amount){
        return p.getInventory().containsAtLeast(new ItemStack(mat), amount);
    }
}
