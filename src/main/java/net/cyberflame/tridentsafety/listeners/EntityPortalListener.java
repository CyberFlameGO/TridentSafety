package net.cyberflame.tridentsafety.listeners;

import net.cyberflame.tridentsafety.utils.EnchantmentUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class EntityPortalListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPortal(EntityPortalEvent event) {
        if(event.getEntityType() != EntityType.TRIDENT) return;
        Trident trident = (Trident) event.getEntity();
        if(EnchantmentUtils.getLoyalty(trident)>0) {
            event.setCancelled(true);
        }
    }

}
