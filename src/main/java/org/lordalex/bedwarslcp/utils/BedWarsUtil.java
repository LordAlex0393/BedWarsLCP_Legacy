package org.lordalex.bedwarslcp.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.lordalex.bedwarslcp.BedWarsLCP;

import java.util.ArrayList;

public class BedWarsUtil {
    private static int test = 0;


    public static void start(CommandSender sender){

        if(BedWarsLCP.isStarted) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    spawnGoldResourse(sender);
                    test++;
                }
            }.runTaskTimer(BedWarsLCP.getInstance(), 0L, 10L);
        }

    }
    private static void spawnGoldResourse(CommandSender sender){
        Player p = (Player) sender;
        MapConfig mapConfig = new MapConfig();
        mapConfig.setGoldFrequency(100);
        ArrayList<ArrayList<Double>> goldSpawns = new ArrayList<>();
        ArrayList<Double> gs1 = new ArrayList<>();
        gs1.add(-183.5);
        gs1.add(68.5);
        gs1.add(266.5);
        ArrayList<Double> gs2 = new ArrayList<>();
        gs2.add(-180.5);
        gs2.add(68.5);
        gs2.add(266.5);

        goldSpawns.add(gs1);
        goldSpawns.add(gs2);

        mapConfig.setGoldSpawns(goldSpawns);

        World world = p.getWorld();
//            Location location = new Location(world, mapConfig.getGoldSpawns().get(0), mapConfig.getGoldSpawns().get(1), mapConfig.getGoldSpawns().get(2));
//            Item dropitem = world.dropItem(location, new ItemStack(Material.CLAY_BRICK, 1));

        for (ArrayList<Double> position : mapConfig.getGoldSpawns()) {
            Location location = new Location(world, position.get(0), position.get(1), position.get(2));
            Item dropitem = world.dropItem(location, new ItemStack(Material.CLAY_BRICK, 1));
            dropitem.setVelocity(dropitem.getVelocity().zero());
        }
    }
}
