package com.xiluiis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;

import java.util.HashMap;
import java.util.Map;

import com.xiluiis.commands.HelpSubCommand;
import com.xiluiis.commands.SetSubCommand;
import com.xiluiis.commands.SubCommand;
import com.xiluiis.service.FileMessageService;
import com.xiluiis.service.PlayerRankService;

public class WelcomeTitles extends JavaPlugin implements Listener
{
    private FileMessageService messageService;
    private PlayerRankService playerRankService;
    private Map<String, SubCommand> subCommands = new HashMap<>();

    private static final String HELP_COMMAND = "help";
    private static final String SET_COMMAND = "set";
    private final String PLUGIN_VERSION = "version 1.0.0-beta";
    

    @Override
    public void onEnable() {
        getLogger().info("Disabling WelcomeTitles.");

        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();

        messageService = new FileMessageService(this);
        playerRankService = new PlayerRankService(this);
        
        subCommands.put(SET_COMMAND, new SetSubCommand(messageService, playerRankService));
        subCommands.put(HELP_COMMAND, new HelpSubCommand(messageService, playerRankService));
    }

    @Override
    public void onDisable() {
        getLogger().info("Running WelcomeTitles " + PLUGIN_VERSION + ".");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        player.sendMessage(messageService.getConfigMessage(playerNameString, playerRankService.getPlayerRank(player) + "-private-join-message"));
        event.setJoinMessage(messageService.getWelcomeMessage(playerNameString, playerRankService.getPlayerRank(player) + "-global-join-message"));
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event){
        String playerNameString = event.getPlayer().getName();
        Player player = event.getPlayer();
        event.setQuitMessage(messageService.getConfigMessage(playerNameString, playerRankService.getPlayerRank(player) + "-global-quit-message"));
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            args = new String[] {HELP_COMMAND};
        }

        String subCommandString = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandString);
        
        if (subCommand != null) {
            subCommand.execute(sender, args);
        } else {
            sender.sendMessage("Unknown command. Use /welcometitles help to see possible commands");
        }
        return true;
    }
}