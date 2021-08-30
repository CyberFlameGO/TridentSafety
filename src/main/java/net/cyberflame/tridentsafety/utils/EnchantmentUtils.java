package net.cyberflame.tridentsafety.utils;

import net.cyberflame.tridentsafety.Main;
// import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
// import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EnchantmentUtils
{
/*
    public static int getLevelFromTrident(Player player, Enchantment enchantment) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.TRIDENT)
            {
                item = player.getInventory().getItemInOffHand();
            }
        if (item.getType() != Material.TRIDENT) return 0;
        if (! item.hasItemMeta()) return 0;
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (meta.hasEnchant(enchantment))
            {
                return meta.getEnchantLevel(enchantment);
            }
        return 0;
    }
*/
    public static int getLoyalty(Trident trident) {
        PersistentDataContainer tridentPDC = trident.getPersistentDataContainer();
        return tridentPDC.getOrDefault(Main.LOYALTY_TAG, PersistentDataType.INTEGER, 0);
    }

/*
    public static void registerLoyalty(Trident trident, int level) {
        if (level == 0) return;
        PersistentDataContainer tridentPDC = trident.getPersistentDataContainer();
        tridentPDC.set(Main.LOYALTY_TAG, PersistentDataType.INTEGER, level);
    }
*/
    public static boolean isOffHandThrown(Trident trident) {
        PersistentDataContainer tridentPDC = trident.getPersistentDataContainer();
        return tridentPDC.has(Main.OFFHAND_TAG, PersistentDataType.BYTE);
    }

    public static boolean hasLoyalty(ItemStack item) {
        if (! item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.hasEnchant(Enchantment.LOYALTY);
    }

}