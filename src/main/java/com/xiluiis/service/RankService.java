package com.xiluiis.service;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface RankService {
    String getPlayerRank(Player player);
    boolean isAllowedToSet(CommandSender sender);
}
