package me.nelson131.bs;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.nelson131.bs.BorderSurvival.plugin;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        World world = event.getPlayer().getWorld();
        WorldBorder wb = world.getWorldBorder();

        wb.setSize(wb.getSize() + plugin.getConfig().getInt("int-size"));
    }
}
