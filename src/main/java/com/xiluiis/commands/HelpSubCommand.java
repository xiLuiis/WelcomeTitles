package com.xiluiis.commands;

import org.bukkit.command.CommandSender;

import com.xiluiis.service.FileMessageService;
import com.xiluiis.service.PlayerRankService;

public class HelpSubCommand implements SubCommand{
    private final FileMessageService configMessageService;
    private final PlayerRankService playerRankService;

    public HelpSubCommand(FileMessageService configMessageService, PlayerRankService playerRankService){
        this.configMessageService = configMessageService;
        this.playerRankService = playerRankService;
    }

    public String getName(){
        return "help";
    }
    
    public void execute(CommandSender sender,String[] args){
         if(playerRankService.isAllowedToHelp(sender)){
            if(playerRankService.isAllowedToSet(sender)){
                sender.sendMessage("Executing help premium options");
            }
                sender.sendMessage("Executing help options");
        }
    }

}
