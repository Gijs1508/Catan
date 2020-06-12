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

    public ChatMessage() {}


    public ChatMessage(String content) {
        this.content = content;
        init();
    }

    public ChatMessage(String content, String playerName) {
        this.content = content;
        this.sender = playerName;
        this.message = this.sender + ": " + this.content;
    }

    private void init(){
            this.sender = Player.getMainPlayer().getName(); // Wordt later vervangen met getPlayer ofzo
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