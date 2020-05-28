package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.catan.Model.Chat ;
import org.catan.Model.ChatMessage ;

public class ChatController {
    Chat chat = new Chat(1); // get game ID
    @FXML
    private TextField messageField;
    @FXML
    private TextArea chatBox;
    @FXML
    private Text msgContent;
    @FXML
    private Text sender;

    public ChatController() {

    }

    private void keyHandler() {

    }

    private void sendChatMessage(ChatMessage message) {

    }

    @FXML
    private void sendMessage() {
        if(messageField.getText().length() > 0 && messageField.getText().length() < 100){
            ChatMessage message = new ChatMessage(messageField.getText());
            chat.addChatMessage(message);
            messageField.clear();

            updateChatView();
        }
        else{ messageField.clear(); }
    }

    private void updateChatView() {
        chatBox.clear();
        chatBox.appendText(chat.generateChatString());
    }
}