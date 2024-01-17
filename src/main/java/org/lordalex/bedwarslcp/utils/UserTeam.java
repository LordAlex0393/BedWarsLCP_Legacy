package org.lordalex.bedwarslcp.utils;

import java.util.UUID;

public class UserTeam {
    private UUID uuid;
    private String color;

    public UserTeam(UUID uuid, String color) {
        this.uuid = uuid;
        this.color = color;
    }
    public UserTeam() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
