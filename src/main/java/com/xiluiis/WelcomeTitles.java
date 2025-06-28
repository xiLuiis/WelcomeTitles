package com.xiluiis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeTitles extends JavaPlugin implements Listener
{
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        getLogger().info("Alguien entro al servidor");
        String playerNameString = event.getPlayer().getName();
        event.setJoinMessage("[+] " + playerNameString);
        event.getPlayer().sendMessage("Hola " + playerNameString + ", Bienvenido al servidor");
    }

}
