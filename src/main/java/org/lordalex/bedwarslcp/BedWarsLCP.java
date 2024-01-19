package org.lordalex.bedwarslcp;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.lordalex.bedwarslcp.commands.KillCommand;
import org.lordalex.bedwarslcp.commands.StartCommand;
import org.lordalex.bedwarslcp.events.*;
import org.lordalex.bedwarslcp.utils.MapConfig;
import org.lordalex.bedwarslcp.utils.YmlPaser;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public final class BedWarsLCP extends JavaPlugin {
    private static Plugin instance;
    public static boolean isStarted = false;
    public static MapConfig mapConfig;
    public static HashSet<Material> allowedToBreak;
    public static HashSet<Block> placedBlocksSet;
    @Override
    public void onEnable() {
        instance = this;
        System.out.println("BedWarsLCP by _Lord_ALex_ is loaded");

        File file = new File("plugins\\BedWarsLCP\\MastebrConfig.yml");
        mapConfig = YmlPaser.parseMapConfig(file);
        init();

        Bukkit.getPluginManager().registerEvents(new SavingPlatform(), this);
        Bukkit.getPluginManager().registerEvents(new TrackerGPS(), this);
        //Bukkit.getPluginManager().registerEvents(new TeleportationDust(), this);
        Bukkit.getPluginManager().registerEvents(new OpenTrader(), this);
        Bukkit.getPluginManager().registerEvents(new BedWarsEvents(), this);
        //Bukkit.getPluginManager().registerEvents(new onBlockBreak(), this);
        getCommand("bw").setExecutor(new StartCommand());
        getCommand("kill").setExecutor(new KillCommand());

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(World world : Bukkit.getServer().getWorlds()) {
                    world.setThundering(false);
                    world.setStorm(false);
                    world.setTime(3000);
                }
            }
        }, 0L, 20L);


    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Plugin getInstance(){
        return instance;
    }
    public static void init(){
        allowedToBreak = new HashSet<>();
        allowedToBreak.add(Material.SANDSTONE);
        allowedToBreak.add(Material.WEB);
        allowedToBreak.add(Material.GLASS);
        allowedToBreak.add(Material.SANDSTONE_STAIRS);
        allowedToBreak.add(Material.ENDER_STONE);
        allowedToBreak.add(Material.GLOWSTONE);
        allowedToBreak.add(Material.IRON_BLOCK);
        //allowedToBreak.add(Material.BED_BLOCK);
    }
}