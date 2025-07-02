package com.xiluiis.service;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerRankService implements RankService {
    private final JavaPlugin plugin;
    
    public PlayerRankService(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
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

    @Override
    public boolean isAllowedToSet(CommandSender sender){
        //sender.hasPermission("welcometitles.vip") ||  sender.hasPermission("welcometitles.admin") || sender.isOp();
        Boolean adminAllowed = plugin.getConfig().getBoolean("sub-command-permissions.set.admin");
        Boolean modAllowed = plugin.getConfig().getBoolean("sub-command-permissions.set.mod");
        Boolean vipAllowed = plugin.getConfig().getBoolean("sub-command-permissions.set.vip");
        Boolean defaultAllowed = plugin.getConfig().getBoolean("sub-command-permissions.set.default");

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.isOp()){
                return true;
            }

            String playerRank = getPlayerRank(player);
            
            Boolean hasPermissions =    (playerRank.equals("vip")&&vipAllowed) ? true :
                                        (playerRank.equals("admin")&&adminAllowed) ? true :
                                        (playerRank.equals("mod")&&modAllowed) ? true :
                                        (playerRank.equals("default")&&defaultAllowed) ? true : false;

            return hasPermissions;
        }else return false;
    }

    @Override
    public boolean isAllowedToHelp(CommandSender sender){
        //sender.hasPermission("welcometitles.vip") ||  sender.hasPermission("welcometitles.admin") || sender.isOp();
        Boolean adminAllowed = plugin.getConfig().getBoolean("sub-command-permissions.help.admin");
        Boolean modAllowed = plugin.getConfig().getBoolean("sub-command-permissions.help.mod");
        Boolean vipAllowed = plugin.getConfig().getBoolean("sub-command-permissions.help.vip");
        Boolean defaultAllowed = plugin.getConfig().getBoolean("sub-command-permissions.help.default");

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.isOp()){
                return true;
            }

            String playerRank = getPlayerRank(player);
            
            Boolean hasPermissions =    (playerRank.equals("vip")&&vipAllowed) ? true :
                                        (playerRank.equals("admin")&&adminAllowed) ? true :
                                        (playerRank.equals("mod")&&modAllowed) ? true :
                                        (playerRank.equals("default")&&defaultAllowed) ? true : false;

            return hasPermissions;
        }else return false;
    }
}
