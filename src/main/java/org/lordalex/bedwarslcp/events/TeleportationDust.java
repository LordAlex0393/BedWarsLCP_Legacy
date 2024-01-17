package org.lordalex.bedwarslcp.events;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.lordalex.bedwarslcp.BedWarsLCP;

import java.util.HashMap;
import java.util.UUID;

public class TeleportationDust implements Listener{
    private final HashMap<UUID, Long> cooldownsMap;
    private final int COOLDOWN = 5000;
    private final int DELAY = 1000;
    private BukkitTask teleport;

    public TeleportationDust() {
        this.cooldownsMap = new HashMap<UUID, Long>();
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(e.getItem() == null) return;
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.SULPHUR)) return;
        if (!cooldownsMap.containsKey(p.getUniqueId())
                || System.currentTimeMillis() - cooldownsMap.get(p.getUniqueId()) >= COOLDOWN) {
            cooldownsMap.put(p.getUniqueId(), System.currentTimeMillis());
            p.sendMessage("Starting teleportation");
            BukkitTask teleport = new BukkitRunnable() {
                @Override
                public void run() {
                    teleportByDust(p);
                    p.sendMessage("Done!");
                }
            }.runTaskLater(BedWarsLCP.getInstance(), 18);

        }
        else {
            //if the cooldown is not over, send the player a message
            p.sendMessage("Reloading: " + (COOLDOWN - (System.currentTimeMillis() - cooldownsMap.get(p.getUniqueId()))) / 1000 + "s");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(cooldownsMap.containsKey(e.getEntity().getUniqueId())
                && System.currentTimeMillis() - cooldownsMap.get(e.getEntity().getUniqueId()) < DELAY){
            teleport.cancel();
        }
    }
    private void teleportByDust(Player p) {
        int y = 255;
        Location loc = p.getLocation();
        loc.setX(500);
        loc.setZ(500);
        loc.setY(y);
        Location tpLoc = loc.clone();
        while(tpLoc.getBlock().getType() == Material.AIR){
            tpLoc.setY(y);
            y--;
        }
        loc.setY(y+2);
        p.teleport(loc);
    }

//    private void teleportByDust(Player p) {
//        int max = 20;
//        int min = 5;
//        int y = 255;
//        Location loc = p.getLocation();
//        loc.setX(loc.getX() + (Math.random() * (max - min)) + min);
//        loc.setZ(loc.getZ() + (Math.random() * (max - min)) + min);
//        loc.setY(y);
//        Location tpLoc = loc.clone();
//        while(tpLoc.getBlock().getType() == Material.AIR){
//            tpLoc.setY(y);
//            y--;
//        }
//        loc.setY(y+2);
//        p.teleport(loc);
//    }
}
