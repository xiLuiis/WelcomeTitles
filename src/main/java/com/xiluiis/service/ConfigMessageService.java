package com.xiluiis.service;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigMessageService implements MessageService{
    private final JavaPlugin plugin;

    public ConfigMessageService(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public String createMessage(String playerNameString, String pathString){
        String message = plugin.getConfig().getString(pathString).replace("%player%", playerNameString);
        return message;
    }
}
