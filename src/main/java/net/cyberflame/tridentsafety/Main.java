package net.cyberflame.tridentsafety;

import net.cyberflame.tridentsafety.listeners.EntityPickupItemListener;
import net.cyberflame.tridentsafety.listeners.EntityPortalListener;
import net.cyberflame.tridentsafety.listeners.ProjectileLaunchListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Trident;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin
{

    public static NamespacedKey LOYALTY_TAG;
    public static NamespacedKey OFFHAND_TAG;
    private static Main instance;
    private final ArrayList<UUID> tridents = new ArrayList<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance    = this;
        LOYALTY_TAG = new NamespacedKey(this, "loyalty");
        OFFHAND_TAG = new NamespacedKey(this, "offhand");
        registerListeners();
    }

    private void registerListeners()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EntityPickupItemListener(), this);
        pm.registerEvents(new EntityPortalListener(), this);
        pm.registerEvents(new ProjectileLaunchListener(), this);
    }


    public void setLoyal(Trident trident) {
        tridents.add(trident.getUniqueId());
    }

    public void removeLoyal(Trident trident) {
        tridents.remove(trident.getUniqueId());
    }
}