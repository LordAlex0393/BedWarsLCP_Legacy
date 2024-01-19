package org.lordalex.bedwarslcp.events;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.BedWarsUtil;
import org.lordalex.bedwarslcp.utils.ColorUtil;
import org.lordalex.bedwarslcp.utils.YmlPaser;


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
            p.setGameMode(GameMode.ADVENTURE);
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
                    p.setGameMode(GameMode.SURVIVAL);
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.setDisplayName(ChatColor.GREEN + p.getName());
                    p.setPlayerListName(ChatColor.GREEN + p.getName());
                    p.teleport(location);
                    p.setBedSpawnLocation(location, true);
                }
                if(isEqualsItem(e, "&eЖелтая команда")){
                    userTeam.put(p.getUniqueId(), "yellow");
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.setDisplayName(ChatColor.YELLOW + p.getName());
                    p.setPlayerListName(ChatColor.YELLOW + p.getName());
                    p.teleport(location);
                    p.setBedSpawnLocation(location, true);
                }
                else if(isEqualsItem(e, "&cКрасная команда")){
                    userTeam.put(p.getUniqueId(), "red");
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.setDisplayName(ChatColor.RED + p.getName());
                    p.setPlayerListName(ChatColor.RED + p.getName());
                    p.teleport(location);
                    p.setBedSpawnLocation(location, true);
                }
                else if(isEqualsItem(e, "&9Синяя команда")){
                    userTeam.put(p.getUniqueId(), "blue");
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);
                    int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size()-1);
                    Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
                    p.getInventory().removeItem(new ItemStack(Material.NAME_TAG, 1));
                    p.setDisplayName(ChatColor.BLUE + p.getName());
                    p.setPlayerListName(ChatColor.BLUE + p.getName());
                    p.teleport(location);
                    p.setBedSpawnLocation(location, true);
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(userTeam.containsKey(p.getUniqueId())) {

            for(String bedPos : mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getBed()){
                Location loc = BedWarsUtil.parseLocation(p.getWorld(), bedPos);
                if(loc.getBlock().getType() != Material.BED_BLOCK){
                    p.setGameMode(GameMode.SPECTATOR);
                    p.setDisplayName(p.getName());
                    p.setPlayerListName(p.getName());
                    p.setGameMode(GameMode.SPECTATOR);
                    return;
                }
            }

            int spawnIndex = (int) (Math.random() * mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().size() - 1);
            Location location = BedWarsUtil.parseLocation(p.getWorld(), mapConfig.getTeams().get(userTeam.get(p.getUniqueId())).getSpawns().get(spawnIndex));
            e.setRespawnLocation(location);
            p.setBedSpawnLocation(location, true);

            //e.setRespawnLocation(new Location(p.getWorld(), 571.5, 62, 475.5));

        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if((!allowedToBreak.contains(e.getBlock().getType())) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
        Player p = e.getPlayer();
        if(e.getBlock().getType()==Material.BED_BLOCK || e.getBlock().getType()==Material.BED){
            e.getBlock().setType(Material.AIR);
            Location breakedLocation = e.getBlock().getLocation();
            for(String key : mapConfig.getTeams().keySet()){
                for(String bedPos : mapConfig.getTeams().get(key).getBed()){
                    Location loc = BedWarsUtil.parseLocation(p.getWorld(), bedPos);
                    if(loc.equals(e.getBlock().getLocation())){
                        loc.getBlock().setType(Material.AIR);
                        for (Player player : e.getBlock().getWorld().getPlayers()) {
                            HashMap<String, String> COLOR_CODE = new HashMap<>();
                            HashMap<String, String> COLOR_STRING = new HashMap<>();
                            COLOR_CODE.put("green", "&a");
                            COLOR_CODE.put("red", "&c");
                            COLOR_CODE.put("blue", "&9");
                            COLOR_CODE.put("yellow", "&e");

                            COLOR_STRING.put("green", "Зелёную");
                            COLOR_STRING.put("red", "Красную");
                            COLOR_STRING.put("blue", "Синюю");
                            COLOR_STRING.put("yellow", "Желтую");
                            //p.sendMessage("сломанный блок соответствует спавну кровати " + key);
                            p.sendMessage(ColorUtil.getMessage("&fИгрок " + COLOR_CODE.get(userTeam.get(p.getUniqueId())) + p.getName()
                                    + "&f разрушил " + COLOR_CODE.get(key) + COLOR_STRING.get(key) + " кровать"));
                            return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        String killed = e.getEntity().getName();
        String killer = e.getEntity().getKiller().getName();
        e.setDeathMessage("Игрок " + killed + " убит игроком " + killer);
    }

    @EventHandler
    public void onMonster(CreatureSpawnEvent e){
        if(e.getEntity().getType() == null || e.getEntity().getType() != EntityType.VILLAGER){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(!isStarted){
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
//    @EventHandler()
//    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
//        Player player = event.getPlayer();
//        String pName = ChatColor.GRAY + "[Игрок] " + ChatColor.RESET + player.getName() + ": ";
//        if (player.getName().equals("_Lord_Alex_")){
//            pName = ColorUtil.getMessage("&3&l[Гл.Админ] _Lord_Alex_: ") + ChatColor.RESET;
//        }
//        String msg = event.getMessage();
//
//        if (msg == null)
//            return;
//
//        if (!isStarted){
//            event.setFormat(pName + msg);
//        }
//    }
}
