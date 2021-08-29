package net.cyberflame.tridentsafety.listeners;

import net.cyberflame.tridentsafety.Main;
import net.cyberflame.tridentsafety.tasks.WatchTrident;
import net.cyberflame.tridentsafety.utils.EnchantmentUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;

public class ProjectileLaunchListener implements Listener 
{

    private final Main main = Main.getInstance();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntityType() != EntityType.TRIDENT) return;

        Trident trident = (Trident) event.getEntity();
        if (!(trident.getShooter() instanceof Player)) return;
        
        Player player = (Player) trident.getShooter();
        PlayerInventory playerInventory = player.getInventory();

        // Offhand
        if (playerInventory.getItemInMainHand().getType() == Material.TRIDENT) return;
        if (playerInventory.getItemInOffHand().getType() != Material.TRIDENT) return;
        trident.getPersistentDataContainer().set(Main.OFFHAND_TAG, PersistentDataType.BYTE, (byte) 1);

        ItemStack tridentItem = null;
        if (playerInventory.getItemInOffHand() != null) {
            if (playerInventory.getItemInOffHand().getType() == Material.TRIDENT) tridentItem = playerInventory.getItemInOffHand();
        }
        if (playerInventory.getItemInMainHand() != null) {
            if (playerInventory.getItemInMainHand().getType() == Material.TRIDENT) {
                tridentItem = playerInventory.getItemInMainHand();
            }
        }
        if (tridentItem == null) {
            main.debug("tridentItem not found");
            return;
        }
        if (!EnchantmentUtils.hasLoyalty(tridentItem)) {
            return;

        main.setLoyal(trident);
        new WatchTrident(trident).runTaskTimer(main,1,1);
        Bukkit.getScheduler().runTaskLater(main,() ->main.removeLoyal(trident),1200);
    }

}
