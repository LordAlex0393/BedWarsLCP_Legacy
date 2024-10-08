package org.lordalex.bedwarslcp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;
import org.lordalex.bedwarslcp.BedWarsLCP;
import org.lordalex.bedwarslcp.events.BedWarsEvents;
import org.lordalex.bedwarslcp.utils.BedWarsUtil;
import org.lordalex.bedwarslcp.utils.ColorUtil;

import java.util.ArrayList;
import java.util.List;


public class StartCommand implements CommandExecutor{
    private String COMMAND_LIST = ColorUtil.getMessage("&e/bw &7start&f: начать игру BedWars\n" +
            "&e/bw &7start&f: завершить игру BedWars\n" +
            "&e/bw &7clear&f: очистить карту BedWars\n" +
            "&e/bw &7trader&f: заспавнить торговца\n" +
            "&e/bw &7kit&f: получить тестовый набор");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 0){
            sender.sendMessage(COMMAND_LIST);
        }
        else if(args[0].equalsIgnoreCase("start") && sender.isOp()){
            stopGame(sender);
            BedWarsLCP.isStarted = true;
            BedWarsUtil.start(sender);
            sender.sendMessage("BedWars was started");
            return true;
        }
        else if(args[0].equalsIgnoreCase("stop") && sender.isOp()){
            stopGame(sender);
            return true;
        }
        else if(args[0].equalsIgnoreCase("reset") && sender.isOp()){
            Player p = (Player) sender;
            for(Block block : BedWarsLCP.placedBlocksSet){
                block.setType(Material.AIR);
            }
            sender.sendMessage("BedWars reset");
            return true;
        }
        else if(args[0].equalsIgnoreCase("clear") && sender.isOp()){
            clearDroppedItems(sender);
            return true;
        }
        if(args[0].equalsIgnoreCase("kit") && sender.isOp()){
            if (!(sender instanceof Player))
            {
                sender.sendMessage("You must be a player to execute that command!");
                return true;
            }
            Player p = (Player) sender;
//            if(!BedWarsLCP.isStarted){
//                p.sendMessage("BW game is offline");
//                return true;
//            }

            ItemStack is1 = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta im1 = is1.getItemMeta();
            im1.setDisplayName(ColorUtil.getMessage("&bСпасательная платформа"));
            List<String> list1 = new ArrayList<>();
            list1.add(ColorUtil.getMessage("&7 Спаси себя от падения!"));
            list1.add(ColorUtil.getMessage("&7В течение &f10 секунд &7вы будете"));
            list1.add(ColorUtil.getMessage("&7стоять на стеклянной платформе."));
            list1.add(ColorUtil.getMessage("&7 Время перезарядки - &e20 секунд"));
            im1.setLore(list1);
            is1.setItemMeta(im1);

            ItemStack is2 = new ItemStack(Material.SULPHUR, 1);
            ItemMeta im2 = is2.getItemMeta();
            im2.setDisplayName("Телепортирующий порошок");
            is2.setItemMeta(im2);

            ItemStack is3 = new ItemStack(Material.COMPASS, 1);
            ItemMeta im3 = is3.getItemMeta();
            im3.setDisplayName("GPS трекер");
            is3.setItemMeta(im3);


            p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
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
        else if(args[0].equalsIgnoreCase("trader") && sender.isOp()){
            Player p = (Player) sender;
            Villager vil = (Villager) p.getLocation().getWorld().spawnEntity(p.getLocation(),EntityType.VILLAGER);
            vil.setCustomName(ChatColor.YELLOW + "Торговец");
            vil.setCustomNameVisible(true);
            vil.setAdult();
            vil.setNoDamageTicks(200);
        }
        else{
            sender.sendMessage(COMMAND_LIST);
        }
        return true;
    }
    private void clearDroppedItems(CommandSender sender){
        Player p = (Player) sender;
        World world = p.getWorld();
        List<Entity> entList = world.getEntities();
        for(Entity current : entList){
            if (current.getType() == EntityType.DROPPED_ITEM){
                current.remove();
            }
        }
    }
    private void stopGame(CommandSender sender){
        BedWarsLCP.isStarted = false;
        Player p = (Player) sender;
        for(Entity ent : p.getWorld().getEntities()){
            if(ent.getType() == EntityType.VILLAGER){
                ent.remove();
            }
        }
        clearDroppedItems(sender);
        sender.sendMessage("BedWars was stopped");
    }
}
