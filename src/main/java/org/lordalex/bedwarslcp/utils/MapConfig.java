package org.lordalex.bedwarslcp.utils;

import java.util.ArrayList;
import java.util.List;

public class MapConfig {
    private String name;
    private String world;
    private ArrayList<Double> lobby;
    private double respawnY;
    private int bronzeFrequency;
    private int ironFrequency;
    private int goldFrequency;
    private ArrayList<ArrayList<Double>> goldSpawns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public ArrayList<Double> getLobby() {
        return lobby;
    }

    public void setLobby(ArrayList<Double> lobby) {
        this.lobby = lobby;
    }

    public double getRespawnY() {
        return respawnY;
    }

    public void setRespawnY(double respawnY) {
        this.respawnY = respawnY;
    }

    public int getBronzeFrequency() {
        return bronzeFrequency;
    }

    public void setBronzeFrequency(int bronzeFrequency) {
        this.bronzeFrequency = bronzeFrequency;
    }

    public int getIronFrequency() {
        return ironFrequency;
    }

    public void setIronFrequency(int ironFrequency) {
        this.ironFrequency = ironFrequency;
    }

    public int getGoldFrequency() {
        return goldFrequency;
    }

    public void setGoldFrequency(int goldFrequency) {
        this.goldFrequency = goldFrequency;
    }

    public ArrayList<ArrayList<Double>> getGoldSpawns() {
        return goldSpawns;
    }

    public void setGoldSpawns(ArrayList<ArrayList<Double>> goldSpawns) {
        this.goldSpawns = goldSpawns;
    }
}
