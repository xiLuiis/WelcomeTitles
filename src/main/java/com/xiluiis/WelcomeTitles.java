package com.xiluiis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;

import com.xiluiis.service.ConfigMessageService;
import com.xiluiis.service.PlayerRankService;

public class WelcomeTitles extends JavaPlugin implements Listener
{
    private ConfigMessageService messageService;
    private PlayerRankService playerRankService;

    @Override
    public void onEnable() {
        getLogger().info("Running WelcomeTitles version 1.0.0-beta.");
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        messageService = new ConfigMessageService(this);
        playerRankService = new PlayerRankService(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling WelcomeTitles.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setJoinMessage(messageService.createMessage(playerNameString, playerRankService.getPlayerRank(player) + "-global-join-message"));
        player.sendMessage(messageService.createMessage(playerNameString, playerRankService.getPlayerRank(player) + "-private-join-message"));
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setQuitMessage(messageService.createMessage(playerNameString, playerRankService.getPlayerRank(player) + "-global-quit-message"));
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if(args.length==0 || (args.length >= 1 && args[0].equalsIgnoreCase("help"))){
            if(playerRankService.isAllowedToHelp(sender)){
                if(playerRankService.isAllowedToSet(sender)){
                    sender.sendMessage("Executing help premium options");
                }
                    sender.sendMessage("Executing help options");
                    return true;
                }
            }
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("set")){
                if(playerRankService.isAllowedToSet(sender)){
                    sender.sendMessage("Executing set command");
                    messageService.changeMessage(sender.getName(), "admin-global-join-message", "Test");
                    return true;
                }else{
                    sender.sendMessage("You don't have permissions to execute this command");
                    return true;
                }
            }
        }
        sender.sendMessage("Unknown command. Use /welcometitles help to see possible commands");
        return true;
    }

}