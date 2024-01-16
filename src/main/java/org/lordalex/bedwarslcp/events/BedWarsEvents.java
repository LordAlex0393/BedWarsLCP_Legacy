package org.lordalex.bedwarslcp.events;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.BedWarsTeam;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lordalex.bedwarslcp.BedWarsLCP.mapConfig;

public class BedWarsEvents implements Listener {

//    @EventHandler
//    public void onSpawn(PlayerJoinEvent e){
//        Player p = e.getPlayer();
//        if(BedWarsLCP.isStarted){
//            HashMap<String, BedWarsTeam> hm = mapConfig.getTeams();
//            for(String key : hm.keySet()){
//                for (String position : hm.get(key).getIronSpawns()) {
//                    String[] coordinates = position.split(", ");
//                    double X = Double.parseDouble(coordinates[0]);
//                    double Y = Double.parseDouble(coordinates[1]);
//                    double Z = Double.parseDouble(coordinates[2]);
//                    Location location = new Location(p.getWorld(), X, Y, Z);
//                    p.sendMessage(position);
//                    p.teleport(location);
//                }
//            }
//        }
//        else{
//            String position = mapConfig.getLobby();
//            String[] coordinates = position.split(", ");
//            double X = Double.parseDouble(coordinates[0]);
//            double Y = Double.parseDouble(coordinates[1]);
//            double Z = Double.parseDouble(coordinates[2]);
//            Location location = new Location(p.getWorld(), X, Y, Z);
//            p.sendMessage(position);
//            p.teleport(location);
//        }
//    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        String position = mapConfig.getLobby();
        String[] coordinates = position.split(", ");
        double X = Double.parseDouble(coordinates[0]);
        double Y = Double.parseDouble(coordinates[1]);
        double Z = Double.parseDouble(coordinates[2]);
        Location location = new Location(p.getWorld(), X, Y, Z);
        p.sendMessage(position);
        p.teleport(location);


        if(!p.getInventory().contains(Material.NAME_TAG))
        {
            ItemStack is1 = new ItemStack(Material.NAME_TAG, 1);
            ItemMeta im1 = is1.getItemMeta();
            im1.setDisplayName(ColorUtil.getMessage("&eВыбор команды"));
            is1.setItemMeta(im1);
            p.getInventory().addItem(is1);
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.NAME_TAG)) return;

        Inventory inv = Bukkit.createInventory(null, 27, "Торговец");


        ItemStack woolStack1 = new ItemStack( Material.WOOL, 1, (byte)5);
        ItemMeta woolMeta1 = woolStack1.getItemMeta();
        woolMeta1.setDisplayName(ColorUtil.getMessage("&aЗеленая команда"));
        woolStack1.setItemMeta(woolMeta1);
        inv.setItem(10, woolStack1);

        ItemStack woolStack2 = new ItemStack( Material.WOOL, 1, (byte)4);
        ItemMeta woolMeta2 = woolStack2.getItemMeta();
        woolMeta2.setDisplayName(ColorUtil.getMessage("&eЖелтая команда"));
        woolStack2.setItemMeta(woolMeta2);
        inv.setItem(12, woolStack2);

        ItemStack woolStack3 = new ItemStack( Material.WOOL, 1, (byte)14);
        ItemMeta woolMeta3 = woolStack3.getItemMeta();
        woolMeta3.setDisplayName(ColorUtil.getMessage("&cКрасная команда"));
        woolStack3.setItemMeta(woolMeta3);
        inv.setItem(14, woolStack3);

        ItemStack woolStack4 = new ItemStack( Material.WOOL, 1, (byte)11);
        ItemMeta woolMeta4 = woolStack4.getItemMeta();
        woolMeta4.setDisplayName(ColorUtil.getMessage("&9Синяя команда"));
        woolStack4.setItemMeta(woolMeta4);
        inv.setItem(16, woolStack4);

        p.openInventory(inv);
    }
}
