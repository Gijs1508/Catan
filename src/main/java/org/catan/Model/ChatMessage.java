package org.catan.Model;

import org.catan.Controller.ChatController ;

import java.util.Date;

public class ChatMessage {

    private int id;
    private String content;
    private String sender;
    private String message;
    private Date timestamp; // DateTime is geen data type?? -Sabrina
    private String playerName;

    private ChatController chatController;

    public ChatMessage(String content) {
        this.content = content;
        init();
    }

    private void init(){
            this.sender = "Jeroen"; // Wordt later vervangen met getPlayer ofzo
            this.message = sender + ": " + content;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
//
//    private int getId(){
//
//    }
//
//    private Date getTimeStamp(){
//
//    }

}