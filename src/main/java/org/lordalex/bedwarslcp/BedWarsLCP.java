package org.lordalex.bedwarslcp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.lordalex.bedwarslcp.commands.StartCommand;
import org.lordalex.bedwarslcp.events.*;
import org.lordalex.bedwarslcp.utils.MapConfig;
import org.lordalex.bedwarslcp.utils.YmlPaser;

public final class BedWarsLCP extends JavaPlugin {
    private static Plugin instance;
    public static boolean isStarted = false;
    public static MapConfig mapConfig;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("PLUGIN ACTIVATED!");

        mapConfig = YmlPaser.parseMapConfig("C:\\MineConfig\\MastebrConfig.yml");

        Bukkit.getPluginManager().registerEvents(new SavingPlatform(), this);
        Bukkit.getPluginManager().registerEvents(new TrackerGPS(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportationDust(), this);
        Bukkit.getPluginManager().registerEvents(new OpenTrader(), this);
        Bukkit.getPluginManager().registerEvents(new BedWarsEvents(), this);
        getCommand("bw").setExecutor(new StartCommand());

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(World world : Bukkit.getServer().getWorlds()) {
                    world.setThundering(false);
                    world.setStorm(false);
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
}