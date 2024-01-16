package org.lordalex.bedwarslcp.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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

import static jdk.xml.internal.SecuritySupport.getClassLoader;

public class BedWarsUtil {
    public static void start(CommandSender sender){
        System.out.println("Prepare to read file");
        MapConfig mapConfig = YmlPaser.parseMapConfig("C:\\MineConfig\\MastebrConfig.yml");
        System.out.println("YML is loaded");
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
            String[] coordinates = position.split(", ");
            double X = Double.parseDouble(coordinates[0]);
            double Y = Double.parseDouble(coordinates[1]);
            double Z = Double.parseDouble(coordinates[2]);
            Location location = new Location(world, X, Y, Z);
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
                String[] coordinates = position.split(", ");
                double X = Double.parseDouble(coordinates[0]);
                double Y = Double.parseDouble(coordinates[1]);
                double Z = Double.parseDouble(coordinates[2]);
                Location location = new Location(world, X, Y, Z);
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
                String[] coordinates = position.split(", ");
                double X = Double.parseDouble(coordinates[0]);
                double Y = Double.parseDouble(coordinates[1]);
                double Z = Double.parseDouble(coordinates[2]);
                Location location = new Location(world, X, Y, Z);
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
                String[] coordinates = position.split(", ");
                double X = Double.parseDouble(coordinates[0]);
                double Y = Double.parseDouble(coordinates[1]);
                double Z = Double.parseDouble(coordinates[2]);
                Location location = new Location(world, X, Y, Z);

                Villager vil = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
                vil.setCustomName(ChatColor.YELLOW + "Торговец");
                vil.setCustomNameVisible(true);
                vil.setAdult();
                vil.setNoDamageTicks(200);
            }
        }
    }
}
