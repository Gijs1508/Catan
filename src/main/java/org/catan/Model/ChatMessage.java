package org.catan.Model;

import org.catan.Controller.ChatController ;

import java.util.Date;

/**
 * A single chat message which contains the sender and the content of the message.
 *
 * @author Jeroen
 */

public class ChatMessage {

    private int id;
    private String content;
    private String sender;
    private String message;
    private String playerName;

    private ChatController chatController;

    public ChatMessage(String content) {
        this.content = content;
        init();
    }

    private void init(){
            this.sender = Player.getMainPlayer().getName();
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

}