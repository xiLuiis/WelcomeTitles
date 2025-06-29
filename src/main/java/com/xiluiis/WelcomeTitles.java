package com.xiluiis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeTitles extends JavaPlugin implements Listener
{
    @Override
    public void onEnable() {
        getLogger().info("Running WelcomeTitles!");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling WelcomeTitles!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String playerNameString = event.getPlayer().getName();
        String message = (event.getPlayer().isOp() || event.getPlayer().hasPermission("welcometitles.admin")) ?
        getConfig().getString("admin-global-join-message").replace("%player%", playerNameString):
        getConfig().getString("player-global-join-message").replace("%player%", playerNameString);
        event.setJoinMessage(message);
        event.getPlayer().sendMessage(getConfig().getString("player-private-join-message").replace("%player%", playerNameString));
        
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event){
        String playerNameString = event.getPlayer().getName();
        String message = (event.getPlayer().isOp() || event.getPlayer().hasPermission("welcometitles.admin")) ?
        getConfig().getString("admin-global-quit-message").replace("%player%", playerNameString):
        getConfig().getString("player-global-quit-message").replace("%player%", playerNameString) + playerNameString;
        event.setQuitMessage(message);
    }

}
