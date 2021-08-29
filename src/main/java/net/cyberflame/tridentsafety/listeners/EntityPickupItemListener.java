package de.jeff_media.bettertridents.listeners;

import de.jeff_media.bettertridents.Main;
import de.jeff_media.bettertridents.config.Config;
import de.jeff_media.bettertridents.tasks.MoveToOffhand;
import de.jeff_media.bettertridents.utils.EnchantmentUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EntityPickupItemListener implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler(ignoreCancelled = true)
    private void onEntityPickupItem(EntityPickupItemEvent event) {
        if(event.getItem().getType().equals(EntityType.TRIDENT)) {
            Trident trident = (Trident) event.getItem();
            if(event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if(trident.getShooter() instanceof Player) {
                    Player shooter = (Player) trident.getShooter();
                    if(player != shooter)
                        event.setCancelled(true);
                }
            }
        }
      
        if (!(event.getArrow() instanceof Trident)) return;
        Trident trident = (Trident) event.getArrow();
        if(!EnchantmentUtils.isOffhandThrown(trident)) {
            return;
        }
        Player player = event.getPlayer();
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) return;

        ItemStack tridentItem = event.getItem().getItemStack().clone();
        ItemMeta meta = tridentItem.getItemMeta();
        meta.getPersistentDataContainer().set(Main.OFFHAND_TAG, PersistentDataType.BYTE, (byte) 1);
        tridentItem.setItemMeta(meta);
        event.getItem().setItemStack(tridentItem);

        main.debug("Starting offhand task...");
        new MoveToOffhand(player, tridentItem).runTask(main);
    }
}
