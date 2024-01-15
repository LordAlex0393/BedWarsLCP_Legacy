package org.lordalex.bedwarslcp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.lordalex.bedwarslcp.events.SavingPlatform;

public final class BedWarsLCP extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("PLUGIN ACTIVATED!!!!!!!!!!!!!!!!!!!!!");
        Bukkit.getPluginManager().registerEvents(new SavingPlatform(), this);

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Plugin getInstance(){
        return instance;
    }
}