package com.xiluiis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xiluiis.service.ConfigMessageService;
import com.xiluiis.service.PlayerRankService;

public class WelcomeTitles extends JavaPlugin implements Listener
{
    private ConfigMessageService messageService;
    private PlayerRankService PlayerRankService;

    @Override
    public void onEnable() {
        getLogger().info("Running WelcomeTitles version 1.0.0-beta.");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        messageService = new ConfigMessageService(this);
        PlayerRankService = new PlayerRankService();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling WelcomeTitles.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setJoinMessage(messageService.createMessage(playerNameString, PlayerRankService.getPlayerRank(player) + "-global-join-message"));
        player.sendMessage(messageService.createMessage(playerNameString, PlayerRankService.getPlayerRank(player) + "-private-join-message"));
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setQuitMessage(messageService.createMessage(playerNameString, PlayerRankService.getPlayerRank(player) + "-global-quit-message"));
    }


}
