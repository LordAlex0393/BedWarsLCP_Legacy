package org.lordalex.bedwarslcp.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.lordalex.bedwarslcp.BedWarsLCP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SavingPlatform implements Listener {
    private final HashMap<UUID, Long> cooldownsMap;
    private final int COOLDOWN = 5000;

    public SavingPlatform() {
        this.cooldownsMap = new HashMap<UUID, Long>();
    }


    @EventHandler
    public void platformCreating(PlayerInteractEvent e) {
        List<Location> area;
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.BLAZE_ROD)) return;

        if (!cooldownsMap.containsKey(p.getUniqueId())
                || System.currentTimeMillis() - cooldownsMap.get(p.getUniqueId()) >= COOLDOWN) {
            cooldownsMap.put(p.getUniqueId(), System.currentTimeMillis());
            Location loc = p.getLocation();
            if(loc.getY() < 0){
                loc.setY(0);
            }

            area = new ArrayList<>();
            area.add(loc.clone().add(0, 0, 0));
            area.add(loc.clone().add(1, 0, 0));
            area.add(loc.clone().add(0, 0, 1));
            area.add(loc.clone().add(1, 0, 1));
            area.add(loc.clone().add(1, 0, -1));
            area.add(loc.clone().add(0, 0, -1));
            area.add(loc.clone().add(-1, 0, 1));
            area.add(loc.clone().add(-1, 0, 0));
            area.add(loc.clone().add(-1, 0, -1));

            area.add(loc.clone().add(2, 0, 1));
            area.add(loc.clone().add(2, 0, -1));
            area.add(loc.clone().add(1, 0, 2));
            area.add(loc.clone().add(1, 0, -2));
            area.add(loc.clone().add(-1, 0, 2));
            area.add(loc.clone().add(-1, 0, -2));
            area.add(loc.clone().add(-2, 0, 1));
            area.add(loc.clone().add(-2, 0, -1));


            for(Location l : area){
                if(p.getWorld().getBlockAt(l).getType() == Material.AIR || l.equals(loc)){
                    p.getWorld().getBlockAt(l).setType(Material.GLASS);
                }
            }

            new BukkitRunnable(){
                @Override
                public void run(){
                    for(Location l : area){
                        if(p.getWorld().getBlockAt(l).getType() == Material.GLASS){
                            p.getWorld().getBlockAt(l).setType(Material.AIR);
                        }
                    }
                }
            }.runTaskLater(BedWarsLCP.getInstance(), 100);

            loc.setX(centering(loc.getX()));
            loc.setZ(centering(loc.getZ()));
            p.teleport(loc.clone().add(0, 1, 0));

            p.sendMessage("You have activated the platform!");
        } else {
            //if the cooldown is not over, send the player a message
            p.sendMessage("Reloading: " + (COOLDOWN - (System.currentTimeMillis() - cooldownsMap.get(p.getUniqueId()))) / 1000 + "s");
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(cooldownsMap.containsKey(e.getEntity().getUniqueId())
                    && System.currentTimeMillis() - cooldownsMap.get(e.getEntity().getUniqueId()) < 1000){
                e.setCancelled(true);
            }
        }
    }

    private double centering(double coordinate){
        int d = (int) coordinate;
        if(coordinate >= 0){
            return d + 0.5;
        }
        else{
            return d - 0.5;
        }
    }
}
