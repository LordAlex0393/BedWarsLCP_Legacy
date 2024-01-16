package org.lordalex.bedwarslcp.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.utils.BedWarsTeam;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.HashMap;

import static org.lordalex.bedwarslcp.BedWarsLCP.mapConfig;

public class BedWarsEvents implements Listener {

    @EventHandler
    public void onSpawn(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(BedWarsLCP.isStarted){
            HashMap<String, BedWarsTeam> hm = mapConfig.getTeams();
            for(String key : hm.keySet()){
                for (String position : hm.get(key).getIronSpawns()) {
                    String[] coordinates = position.split(", ");
                    double X = Double.parseDouble(coordinates[0]);
                    double Y = Double.parseDouble(coordinates[1]);
                    double Z = Double.parseDouble(coordinates[2]);
                    Location location = new Location(p.getWorld(), X, Y, Z);
                    p.sendMessage(position);
                    p.teleport(location);
                }
            }
        }
        else{
            String position = mapConfig.getLobby();
            String[] coordinates = position.split(", ");
            double X = Double.parseDouble(coordinates[0]);
            double Y = Double.parseDouble(coordinates[1]);
            double Z = Double.parseDouble(coordinates[2]);
            Location location = new Location(p.getWorld(), X, Y, Z);
            p.sendMessage(position);
            p.teleport(location);
        }
    }
}
