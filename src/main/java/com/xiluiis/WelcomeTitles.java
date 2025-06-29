package com.xiluiis;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();
        event.setJoinMessage(createMessage(playerNameString, getPlayerRank(player) + "-global-join-message"));
        player.sendMessage(createMessage(playerNameString, getPlayerRank(player) + "-private-join-message"));
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setQuitMessage(createMessage(playerNameString, getPlayerRank(player) + "-global-quit-message"));
    }

    public String createMessage(String playerNameString, String pathString){
        String message = getConfig().getString(pathString).replace("%player%", playerNameString);
        return message;
    }

    public String getPlayerRank(Player player){
        if(player.hasPermission("welcometitles.vip")){
            return "vip";
        }
        else if (player.isOp() || player.hasPermission("welcometitles.admin")){
            return "admin";
        }else if(player.hasPermission("welcometitles.mod")){
            return "mod";
        }
        else if(player.hasPermission("welcometitles.default")){
            return "default";
        }
        return "default";
    }

}
