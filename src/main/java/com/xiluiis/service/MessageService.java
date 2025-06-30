package com.xiluiis.service;

public interface MessageService {
    String createMessage(String playerNameString, String pathString);
    void changeMessage(String playerNameString, String pathString, String newText);
}
