package org.catan.Model;

import java.util.ArrayList;

/**
 * Class that contains all chat messages.
 * It turns those messages into a String so it can be displayed.
 *
 * todo
 * database
 * timestamps?
 *
 * @author Jeroen
 * @version 0.5
 */

public class Chat {

    private int id;
    private int gameId;
    private ArrayList<ChatMessage> chatMessages = new ArrayList<>();
    private String chatString;

    public Chat(int gameId) {
        this.gameId = gameId;
    }

    public String generateChatString(){
        chatString = "";
        for (int i = 0; i < chatMessages.size(); i++) {
            if(i == 0){ chatString = chatMessages.get(i).getMessage(); }
            else{ chatString = chatString + "\n" + chatMessages.get(i).getMessage(); }
        }
        return chatString;
    }

    public ArrayList getChatMessages(){
        return chatMessages;
    }

    public void addChatMessage(ChatMessage message) {
        chatMessages.add(message);
    }

    public void removeChatMessage(ChatMessage message) {
        chatMessages.remove(chatMessages.indexOf(message));
    }

    public void setChatMessages(ArrayList<ChatMessage> messages) {
        chatMessages = messages;
    }

//    private ArrayList getMessagesByTimestamp(ArrayList<ChatMessage> messages){
//
//    }

}