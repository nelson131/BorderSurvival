package me.nelson131.bs;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.nelson131.bs.BorderSurvival.plugin;

public class BorderCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("message-command-cancel"));
            return false;
        }
        if (command.getName().equalsIgnoreCase("border") && sender.isOp()) {
            switch (args[0]) {
                case "start":
                    World world = player.getWorld();

                    plugin.getConfig().addDefault("default-border-center-x", world.getWorldBorder().getCenter().getX());
                    plugin.getConfig().addDefault("default-border-center-z", world.getWorldBorder().getCenter().getY());
                    plugin.getConfig().addDefault("default-border-size", world.getWorldBorder().getSize());
                    plugin.getConfig().addDefault("default-spawn-location-x", world.getSpawnLocation().getX());
                    plugin.getConfig().addDefault("default-spawn-location-y", world.getSpawnLocation().getY());
                    plugin.getConfig().addDefault("default-spawn-location-z", world.getSpawnLocation().getZ());

                    int x = 0;
                    int y = world.getHighestBlockYAt(0,0);
                    int z = 0;
                    world.setSpawnLocation(x,y,z);
                    WorldBorder worldBorder = world.getWorldBorder();
                    worldBorder.setCenter(0,0);
                    worldBorder.setSize(4);

                    for(Player players : Bukkit.getOnlinePlayers()){
                        Location location = world.getSpawnLocation();
                        players.setBedSpawnLocation(location);
                        players.setHealth(0);
                        players.getInventory().clear();

                        players.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("message-worldborder-set"));
                    }
                    return true;

                case "stop":
                    World world1 = player.getWorld();
                    WorldBorder worldBorder1 = world1.getWorldBorder();

                    int xc = plugin.getConfig().getInt("default-border-center-x");
                    int xb = plugin.getConfig().getInt("default-border-center-z");
                    int size = plugin.getConfig().getInt("default-border-size");
                    int sx = plugin.getConfig().getInt("default-spawn-location-x");
                    int sy = plugin.getConfig().getInt("default-spawn-location-y");
                    int sz = plugin.getConfig().getInt("default-spawn-location-z");

                    worldBorder1.setCenter(xc,xb);
                    worldBorder1.setSize(size);
                    world1.setSpawnLocation(sx, sy, sz);

                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("message-worldborder-deny"));
                    }
                    return true;
            }
        }

        if (!(sender.isOp())) {
            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("message-not-op"));
            return false;
        }
        return true;
    }
}
