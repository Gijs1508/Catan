package org.catan.Model;

import org.catan.App;

/**
 * A single chat message which contains the sender and the content of the message.
 *
 * @author Jeroen
 */

public class ChatMessage {
    private String content;
    private String sender;
    private String message;

    public ChatMessage() { }

    public ChatMessage(String content) {
        this.content = content;
        init();
    }

    private void init(){
            this.sender = App.getClientPlayer().getName();
            this.message = sender + ": " + content;
    }

    public String getMessage() {
        return message;
    }
}