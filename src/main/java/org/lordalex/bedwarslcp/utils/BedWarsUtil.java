package org.lordalex.bedwarslcp.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.lordalex.bedwarslcp.BedWarsLCP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import static jdk.xml.internal.SecuritySupport.getClassLoader;
import static org.lordalex.bedwarslcp.BedWarsLCP.mapConfig;

public class BedWarsUtil {
//    public static HashMap<String, String> COLOR_CODE;
//    public static HashMap<String, String> COLOR_STRING;
//    static{
//        COLOR_CODE.put("green", "&a");
//        COLOR_CODE.put("red", "&c");
//        COLOR_CODE.put("blue", "&9");
//        COLOR_CODE.put("yellow", "&e");
//
//        COLOR_STRING.put("green", "Зелёную");
//        COLOR_STRING.put("red", "Красную");
//        COLOR_STRING.put("blue", "Синюю");
//        COLOR_STRING.put("yellow", "Желтую");
//    }
    public static void start(CommandSender sender){
        spawnVillagers(sender, mapConfig);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(BedWarsLCP.isStarted){
                    spawnGoldResourse(sender, mapConfig);
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(BedWarsLCP.getInstance(), mapConfig.getGoldFrequency(), mapConfig.getGoldFrequency());

        new BukkitRunnable() {
            @Override
            public void run() {
                if(BedWarsLCP.isStarted){
                    spawnIronResourse(sender, mapConfig);
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(BedWarsLCP.getInstance(), mapConfig.getIronFrequency(), mapConfig.getIronFrequency());

        new BukkitRunnable() {
            @Override
            public void run() {
                if(BedWarsLCP.isStarted){
                    spawnBronzeResourse(sender, mapConfig);
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(BedWarsLCP.getInstance(), mapConfig.getBronzeFrequency(), mapConfig.getBronzeFrequency());

    }
    private static void spawnGoldResourse(CommandSender sender, MapConfig mapConfig){
        Player p = (Player) sender;
        World world = p.getWorld();

        for (String position : mapConfig.getGoldSpawns()) {
            Location location = parseLocation(world, position);
            Item dropitem = world.dropItem(location, new ItemStack(Material.GOLD_INGOT, 1));
            dropitem.setVelocity(dropitem.getVelocity().zero());
        }
    }
    private static void spawnIronResourse(CommandSender sender, MapConfig mapConfig){
        Player p = (Player) sender;
        World world = p.getWorld();

        HashMap<String, BedWarsTeam> hm = mapConfig.getTeams();
        for(String key : hm.keySet()){
            for (String position : hm.get(key).getIronSpawns()) {
                Location location = parseLocation(world, position);
                Item dropitem = world.dropItem(location, new ItemStack(Material.IRON_INGOT, 1));
                dropitem.setVelocity(dropitem.getVelocity().zero());
            }
        }
    }
    private static void spawnBronzeResourse(CommandSender sender, MapConfig mapConfig){
        Player p = (Player) sender;
        World world = p.getWorld();

        HashMap<String, BedWarsTeam> hm = mapConfig.getTeams();
        for(String key : hm.keySet()){
            for (String position : hm.get(key).getBronzeSpawns()) {
                Location location = parseLocation(world, position);
                Item dropitem = world.dropItem(location, new ItemStack(Material.CLAY_BRICK, 1));
                dropitem.setVelocity(dropitem.getVelocity().zero());
            }
        }
    }
    private static void spawnVillagers(CommandSender sender, MapConfig mapConfig){
        Player p = (Player) sender;
        World world = p.getWorld();

        HashMap<String, BedWarsTeam> hm = mapConfig.getTeams();
        for(String key : hm.keySet()){
            for (String position : hm.get(key).getVillagers()) {
                Location location = parseLocation(world, position);

                Villager vil = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
                vil.setCustomName(ChatColor.YELLOW + "Торговец");
                vil.setCustomNameVisible(true);
                vil.setAdult();
                vil.setNoDamageTicks(36000);
            }
        }
    }
    public static Location parseLocation(World world, String position){
        String[] coordinates = position.split(", ");
        double X = Double.parseDouble(coordinates[0]);
        double Y = Double.parseDouble(coordinates[1]);
        double Z = Double.parseDouble(coordinates[2]);
        Location location = new Location(world, X, Y, Z);
        return location;
    }
}
