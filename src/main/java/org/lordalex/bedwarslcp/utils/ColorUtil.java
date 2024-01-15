package org.lordalex.bedwarslcp.utils;

import org.bukkit.ChatColor;

public class ColorUtil {
    public static String getMessage(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
