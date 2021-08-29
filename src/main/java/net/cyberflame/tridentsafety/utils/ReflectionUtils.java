package net.cyberflame.tridentsafety.utils;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public final class ReflectionUtils
{
    private static final Map<String, Class<?>> classCache = new HashMap<>();
    private static String nmsVersion;

    public static String getNMSVersion() {
        if (nmsVersion == null)
            {
                final String name = Bukkit.getServer().getClass().getName();
                final String[] parts = name.split("\\.");
                if (parts.length > 3)
                    {
                        return nmsVersion = parts[3];
                    }
                // We're not on CraftBukkit, return an empty string, so we can silently fail
                return nmsVersion = "";
            }
        return nmsVersion;
    }

    public static Class<?> getNMSClass(final String className) {
        return getClassCached("net.minecraft.server." + getNMSVersion() + "." + className);
    }

    public static Class<?> getOBCClass(final String className) {
        return getClassCached("org.bukkit.craftbukkit." + getNMSVersion() + "." + className);
    }

    public static Class<?> getClassCached(final String className) {
        if (classCache.containsKey(className))
            {
                return classCache.get(className);
            }
        try
            {
                final Class<?> classForName = Class.forName(className);
                classCache.put(className, classForName);
                return classForName;
            }
        catch (final ClassNotFoundException e)
            {
                return null;
            }
    }
}
