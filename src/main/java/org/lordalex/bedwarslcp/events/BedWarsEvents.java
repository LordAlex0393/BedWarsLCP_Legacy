package org.lordalex.bedwarslcp.events;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.BedWarsUtil;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.lordalex.bedwarslcp.utils.UserTeam;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.HashMap;
import java.util.UUID;

import static org.lordalex.bedwarslcp.BedWarsLCP.*;


public class BedWarsEvents implements Listener {
    private HashMap<UUID, String> userTeam;

    public BedWarsEvents() {
        userTeam = new HashMap<>();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        if(!isStarted){
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
    }
    @EventHandler
    public void onTeamMenuClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if(e.getItem() == null) return;
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.NAME_TAG)) return;

        Inventory inv = Bukkit.createInventory(null, 27, "Выбор команды");


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
    @EventHandler
    public void onTeamColorClick(InventoryClickEvent e){
        if(e == null) return;
        Player p = (Player) e.getView().getPlayer();

        if(e.getView().getTitle().equals("Выбор команды")){
            if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
                if(isEqualsItem(e, "&aЗеленая команда")){
                    userTeam.put(p.getUniqueId(), "green");
                    p.getInventory().clear();
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.teleport(location);
                }
                if(isEqualsItem(e, "&eЖелтая команда")){
                    userTeam.put(p.getUniqueId(), "yellow");
                    p.getInventory().clear();
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.teleport(location);
                }
                else if(isEqualsItem(e, "&cКрасная команда")){
                    userTeam.put(p.getUniqueId(), "red");
                    p.getInventory().clear();
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.teleport(location);
                }
                else if(isEqualsItem(e, "&9Синяя команда")){
                    userTeam.put(p.getUniqueId(), "blue");
                    p.getInventory().clear();
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.teleport(location);
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(userTeam.containsKey(p.getUniqueId())) {
            int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size() - 1);
            Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
            e.setRespawnLocation(location);
            //e.setRespawnLocation(new Location(p.getWorld(), 571.5, 62, 475.5));
        }
    }
    @EventHandler
    public void onMonster(CreatureSpawnEvent e){
        if(e.getEntity().getType() == null || e.getEntity().getType() != EntityType.VILLAGER){
            e.setCancelled(true);
        }
    }
    private boolean isEqualsItem(InventoryClickEvent e, String itemDisplayName){
        if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            return e.getCurrentItem().getItemMeta().getDisplayName().equals(ColorUtil.getMessage(itemDisplayName));
        }
        else{
            return false;
        }
    }
    public void disband(){
        userTeam = new HashMap<>();
    }

    public HashMap<UUID, String> getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(HashMap<UUID, String> userTeam) {
        this.userTeam = userTeam;
    }
}
