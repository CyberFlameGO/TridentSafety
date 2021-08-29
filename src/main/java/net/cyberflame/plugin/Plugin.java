package net.cyberflame.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.cyberflame.plugin.commands.Command;
import net.cyberflame.plugin.listeners.PlayerJoinListener;
import net.cyberflame.plugin.tasks.Task;

public class Plugin extends JavaPlugin {
    
    @Override
    public void onEnable () {
        this.saveDefaultConfig();
	    
        Plugin.instance = this;
	    
        this.getCommand("command").setExecutor(new Command());
        
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        final long taskRepeatEvery = this.getConfig().getInt("task-repeat-every") * 20L;
        this.getServer().getScheduler().runTaskTimer(this, new Task(), taskRepeatEvery, taskRepeatEvery);
    }

    private static Plugin instance;

    public static Plugin getInstance () {
        return Plugin.instance;
    }
}
