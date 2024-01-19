package org.lordalex.bedwarslcp.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.ColorUtil;

import static org.lordalex.bedwarslcp.BedWarsLCP.allowedToBreak;

public class onBlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if((!allowedToBreak.contains(e.getBlock().getType())) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
        if(e.getBlock().getType()==Material.BED_BLOCK){
            e.getBlock().getDrops().clear();
            for (Player player : e.getBlock().getWorld().getPlayers()) {
                player.sendMessage(ColorUtil.getMessage("&cКРОВАТЬ БЫЛА СЛОМАНА"));
            }
        }
    }
}
