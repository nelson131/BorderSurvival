package me.nelson131.bs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BorderSurvival extends JavaPlugin {

    public static Plugin plugin = BorderSurvival.getPlugin();
    private static Plugin getPlugin() {
        return plugin;
    }

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getCommand("border").setExecutor(new BorderCommands());

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}
