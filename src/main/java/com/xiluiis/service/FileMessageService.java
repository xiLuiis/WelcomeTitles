package com.xiluiis.service;
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileMessageService implements MessageService{
    private final JavaPlugin plugin;

    public FileMessageService(JavaPlugin plugin){
        this.plugin = plugin;
    }
    
    @Override
    public String getConfigMessage(String playerNameString, String pathString){
        String message = plugin.getConfig().getString(pathString);
        if(message == null) return "Default welcome message not found.";
        else return message.replace("%player%", playerNameString);
    }

    @Override
    public String getYAMLMessage(String playerNameString){
        File userMessagesFile = new File(plugin.getDataFolder(), "usermessages.yml");
        YamlConfiguration userMessagesConfig = YamlConfiguration.loadConfiguration(userMessagesFile);
        String message = userMessagesConfig.getString(playerNameString + ".join-message");
        if(message == null) return null;
        else return message.replace("%player%",playerNameString);
    }

    @Override
    public void setConfigMessage(String playerNameString, String pathString, String newText){
        plugin.getConfig().set(pathString,newText);
        plugin.saveConfig();
    }

    @Override
    public void setYAMLMessage(String playerNameString, String newText){
        
        File userMessagesFile = new File(plugin.getDataFolder(), "usermessages.yml");
        if(userMessagesFile != null){
            YamlConfiguration userMessagesConfig = YamlConfiguration.loadConfiguration(userMessagesFile);
            userMessagesConfig.set(playerNameString + ".join-message",newText);
            try {
                userMessagesConfig.save(userMessagesFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getWelcomeMessage(String playerNameString, String pathString){
        String customMessage = getYAMLMessage(playerNameString);
        String defaultMessage = getConfigMessage(playerNameString, pathString);

        return (customMessage != null ) ? customMessage : defaultMessage;
    }

//     public String getWelcomeMessage(String playerName, String rank) {
//     // Intenta obtener el mensaje personalizado
//     String custom = createYAMLMessage(playerName);
//     if (custom != null && !custom.equals("null")) {
//         return custom.replace("%player%", playerName);
//     }
//     // Si no hay personalizado, usa el global por rango
//     return createConfigMessage(playerName, rank + "-private-join-message");
// }
}
