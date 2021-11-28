package iceskates.us.glasscrab.i.IceSkates;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info("§bIce Skates has been Enabled! wahoo §e§lPogU");
        Bukkit.getPluginManager().registerEvents(this,this);
        this.getCommand("giveSkates").setExecutor(new giveSkates());
    }

    @EventHandler
    public void skate(PlayerMoveEvent e) {
        if((e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.ICE) || e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.PACKED_ICE) || e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BLUE_ICE)) && e.getPlayer().getEquipment().getBoots() != null && e.getPlayer().getEquipment().getBoots().getItemMeta().getLore() != null && e.getPlayer().getEquipment().getBoots().getItemMeta().getLore().get(0).contains("skate")){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 3,false,false));
        }

        else if(!e.getPlayer().isFlying() && (!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.ICE) || !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.PACKED_ICE) || !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BLUE_ICE)) && e.getPlayer().getEquipment().getBoots() != null && e.getPlayer().getEquipment().getBoots().getItemMeta().getLore() != null && e.getPlayer().getEquipment().getBoots().getItemMeta().getLore().get(0).contains("skate")){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 2,false,false));
        }
    }

    public class giveSkates implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            List<String> playerNames = new ArrayList<String>();

            for (Player online : Bukkit.getServer().getOnlinePlayers()){
                playerNames.add(online.getName());
            }

            if(args.length == 0 && sender instanceof Player) {
                Player player = (Player)sender;
                ItemStack skates = new ItemStack(Material.LEATHER_BOOTS);
                ItemMeta skatesMeta = skates.getItemMeta();
                skatesMeta.setDisplayName("§bIce Skates");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§7You skate along ice with ease!");
                skatesMeta.setLore(lore);
                skatesMeta.setUnbreakable(true);
                skatesMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                skatesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                skates.setItemMeta(skatesMeta);
                player.getInventory().addItem(skates);
            }

            else if(playerNames.contains(args[0])){
                Player player = Bukkit.getPlayer(args[0]);
                ItemStack skates = new ItemStack(Material.LEATHER_BOOTS);
                ItemMeta skatesMeta = skates.getItemMeta();
                skatesMeta.setDisplayName("§bIce Skates");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§7You skate along ice with ease!");
                skatesMeta.setLore(lore);
                skatesMeta.setUnbreakable(true);
                skatesMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                skatesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                skates.setItemMeta(skatesMeta);
                player.getInventory().addItem(skates);
            }

            else{
                sender.sendMessage("§cHey idiot, §bname a player §cSTUPID");
                return false;
            }


            return true;
        }
    }

    @Override
    public void onDisable() {
    }
}