package com.xiluiis.service;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigMessageService implements MessageService{
    private final JavaPlugin plugin;

    public ConfigMessageService(JavaPlugin plugin){
        this.plugin = plugin;
    }
    
    @Override
    public String createMessage(String playerNameString, String pathString){
        String message = plugin.getConfig().getString(pathString).replace("%player%", playerNameString);
        return message;
    }

    @Override
    public void changeMessage(String playerNameString, String pathString, String newText){
        plugin.getConfig().set(pathString,newText);
        plugin.saveConfig();
    }
}
