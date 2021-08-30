package net.cyberflame.tridentsafety.listeners;

import net.cyberflame.tridentsafety.Main;
import net.cyberflame.tridentsafety.tasks.MoveToOffHand;
import net.cyberflame.tridentsafety.utils.EnchantmentUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EntityPickupItemListener implements Listener
{
    private final Main main = Main.getInstance();

    @EventHandler(ignoreCancelled = true)
    private void onEntityPickupItem(EntityPickupItemEvent event) {
        if (! (event.getItem() instanceof Trident)) return;

        Trident trident = (Trident) event.getItem();

        if (! (event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (! (trident.getShooter() instanceof Player)) return;
        Player shooter = (Player) trident.getShooter();
        if (player != shooter)
            {
                event.setCancelled(true);
                return;
            }


        if (! (EnchantmentUtils.isOffHandThrown(trident))) return;
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) return;

        ItemStack tridentItem = event.getItem().getItemStack().clone();
        ItemMeta meta = tridentItem.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(Main.OFFHAND_TAG, PersistentDataType.BYTE, (byte) 1);
        tridentItem.setItemMeta(meta);
        event.getItem().setItemStack(tridentItem);

        new MoveToOffHand(player, tridentItem).runTask(main);
    }
}
