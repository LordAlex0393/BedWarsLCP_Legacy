package org.lordalex.bedwarslcp.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static org.lordalex.bedwarslcp.BedWarsLCP.allowedToBreak;

public class onBlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.SURVIVAL){
            e.setCancelled(true);
        }
        else if(!allowedToBreak.contains(e.getBlock().getType())){
            e.setCancelled(true);
        }
    }
}
