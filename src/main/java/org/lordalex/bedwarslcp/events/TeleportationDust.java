package org.lordalex.bedwarslcp.events;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.lordalex.bedwarslcp.BedWarsLCP;

public class TeleportationDust implements Listener{
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().getType() == Material.SULPHUR)) return;
        p.sendMessage("Starting teleportation");
        new BukkitRunnable(){
            @Override
            public void run(){
                teleportByDust(p);
                p.getWorld().playEffect(p.getLocation(),Effect.SMOKE, Material.LAVA);
                p.sendMessage("Done!");
            }
        }.runTaskLater(BedWarsLCP.getInstance(), 18);

    }

    private void teleportByDust(Player p) {
        int y = 255;
        Location loc = p.getLocation();
        loc.setX(loc.getX() + (Math.random() * (20 - 5)) + 5);
        loc.setZ(loc.getZ() + (Math.random() * (20 - 5)) + 5);
        loc.setY(y);
        Location tpLoc = loc.clone();
        while(tpLoc.getBlock().getType() == Material.AIR){
            tpLoc.setY(y);
            y--;
        }
        loc.setY(y+2);
        p.teleport(loc);
    }
}
