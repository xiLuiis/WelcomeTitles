package com.xiluiis.service;

import org.bukkit.entity.Player;

public class PlayerRankService implements RankService {
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
