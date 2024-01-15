package org.lordalex.bedwarslcp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.lordalex.bedwarslcp.commands.StartCommand;
import org.lordalex.bedwarslcp.events.SavingPlatform;
import org.lordalex.bedwarslcp.events.TeleportationDust;
import org.lordalex.bedwarslcp.events.TrackerGPS;

public final class BedWarsLCP extends JavaPlugin {
    private static Plugin instance;
    public static boolean isStarted = false;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("PLUGIN ACTIVATED!!!!!!!!!!!!!!!!!!!!!");
        Bukkit.getPluginManager().registerEvents(new SavingPlatform(), this);
        Bukkit.getPluginManager().registerEvents(new TrackerGPS(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportationDust(), this);
        getCommand("bw").setExecutor(new StartCommand());

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Plugin getInstance(){
        return instance;
    }
}