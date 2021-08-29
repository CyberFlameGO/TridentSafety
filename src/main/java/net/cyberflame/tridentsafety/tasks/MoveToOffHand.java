package net.cyberflame.tridentsafety.tasks;

import net.cyberflame.tridentsafety.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class MoveToOffHand extends BukkitRunnable
{
    private final Player player;
    private final ItemStack tridentItem;

    public MoveToOffHand(Player player, ItemStack tridentItem) {
        this.player      = player;
        this.tridentItem = tridentItem;
    }

    @Override
    public void run() {
        for (int i = 0; i < player.getInventory().getSize(); i++)
            {
                ItemStack item = player.getInventory().getItem(i);
                if (item != null && item.equals(tridentItem))
                    {
                        ItemMeta meta = tridentItem.getItemMeta();
                        assert meta != null;
                        meta.getPersistentDataContainer().remove(Main.OFFHAND_TAG);
                        tridentItem.setItemMeta(meta);
                        ItemStack offhand = player.getInventory().getItemInOffHand();
                        if (offhand.getType() == Material.AIR)
                            {
                                player.getInventory().setItemInOffHand(tridentItem.clone());
                                tridentItem.setAmount(tridentItem.getAmount() - 1);
                                player.getInventory().setItem(i, tridentItem);
                            }
                        break;
                    }
            }
        player.updateInventory();
    }
}