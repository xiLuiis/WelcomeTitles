package com.xiluiis.commands;

import org.bukkit.command.CommandSender;
import java.util.Arrays;

import com.xiluiis.service.FileMessageService;
import com.xiluiis.service.PlayerRankService;

public class SetSubCommand implements SubCommand{
    private final FileMessageService configMessageService;
    private final PlayerRankService playerRankService;

    public SetSubCommand(FileMessageService configMessageService, PlayerRankService playerRankService){
        this.configMessageService = configMessageService;
        this.playerRankService = playerRankService;
    }

    public String getName(){
        return "set";
    }
    
    public void execute(CommandSender sender,String[] args){
        

        if(playerRankService.isAllowedToSet(sender)){
            if (args.length < 2) {
                sender.sendMessage("Usage: /welcometitles set <message>");
                return;
            }

            String stringMessage = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            String playerName = sender.getName();
            
            configMessageService.setYAMLMessage(playerName, stringMessage);
            sender.sendMessage("You changed your join message for: "+ stringMessage + ".");
        }else{
            sender.sendMessage("You don't have permissions to execute this command");
        }
    }

    
}
