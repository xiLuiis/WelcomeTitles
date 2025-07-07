package com.xiluiis.service;

public interface MessageService {
    String getConfigMessage(String playerNameString, String pathString);
    String getYAMLMessage(String playerNameString);
    String getWelcomeMessage(String playerNameString,String pathString);
    void setConfigMessage(String playerNameString, String pathString, String newText);
    void setYAMLMessage(String playerNameString, String newText);
    
}