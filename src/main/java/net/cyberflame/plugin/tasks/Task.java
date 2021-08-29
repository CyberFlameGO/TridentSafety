package net.cyberflame.plugin.tasks;

import org.bukkit.Bukkit;

import net.cyberflame.plugin.Plugin;

public class Task implements Runnable {
    @Override
    public void run() {
        final String message = ExamplePlugin.getInstance().getConfig().getString("messages.from-task");
        Bukkit.getServer().broadcastMessage(message);
    }
}
