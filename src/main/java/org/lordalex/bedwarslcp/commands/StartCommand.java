package org.lordalex.bedwarslcp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.lordalex.bedwarslcp.BedWarsLCP;

public class StartCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equals("start") && sender.isOp()){
            BedWarsLCP.isStarted = true;
            sender.sendMessage("BedWars was started");
            return true;
        }
        if(args[0].equals("kit")){
            if (!(sender instanceof Player))
            {
                sender.sendMessage("You must be a player to execute that command!");
                return true;
            }
            Player p = (Player) sender;
            if(!BedWarsLCP.isStarted){
                p.sendMessage("BW game is offline");
                return true;
            }

            ItemStack is1 = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta im1 = is1.getItemMeta();
            im1.setDisplayName("Спасательная платформа");

            ItemStack is2 = new ItemStack(Material.SULPHUR, 1);
            ItemMeta im2 = is2.getItemMeta();
            im2.setDisplayName("Телепортирующий порошок");

            ItemStack is3 = new ItemStack(Material.COMPASS, 1);
            ItemMeta im3 = is3.getItemMeta();
            im3.setDisplayName("GPS трекер");

            p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 64));
            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 64));
            p.getInventory().addItem(is1);
            p.getInventory().addItem(is2);
            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 64));
            p.getInventory().addItem(is3);
            p.getInventory().addItem(new ItemStack(Material.GRILLED_PORK, 64));
            p.getInventory().addItem(new ItemStack(Material.WEB, 64));
            BedWarsLCP.isStarted = true;
            sender.sendMessage("BedWars was started");
            return true;
        }
        return false;
    }
}
