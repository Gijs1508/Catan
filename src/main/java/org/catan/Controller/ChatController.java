package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.catan.App;
import org.catan.Model.Chat ;
import org.catan.Model.ChatMessage ;
import org.catan.interfaces.ChatObservable;
import org.catan.logic.DatabaseConnector;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles the chat that allows players to communicate.
 *
 * @author Jeroen, Werner
 */

public class ChatController implements Initializable, ChatObservable {
    @FXML private TextField messageField;
    @FXML private TextArea chatBox;
    @FXML private ScrollPane scrollPane;
    private static ChatController chatController = new ChatController();
    Chat chat; // TODO get ID of game

    // Reads the input and updates the chat with it
    @FXML private void sendMessage() {
        if(messageField.getText().length() > 0 && messageField.getText().length() < 100) {
            ChatMessage message = new ChatMessage(messageField.getText());
            chat.addChatMessage(message);
            messageField.clear();
            DatabaseConnector.getInstance().updateChat(chat);
            updateChatView();
        }
        else { messageField.clear(); }
    }

    // Updates the chat by overwriting the chat String
    private void updateChatView() {
        chatBox.clear();
        chatBox.appendText(chat.generateChatString());
    }

    /** Updates the chat
     * @param chat Chat object that contains the entire chat */
    @Override public void update(Chat chat) {
        if (chat.getChatMessages().size() > this.chat.getChatMessages().size()) {
            for (int i = this.chat.getChatMessages().size(); i < chat.getChatMessages().size(); i++) {
                this.chat.addChatMessage(chat.getChatMessages().get(i));
            }
            updateChatView();
        }
    }

    /** Initializes the chat */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        chatController = this;
        chat = new Chat(App.getCurrentGame().getCode().intValue());
        chatBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D)); // Scroll to bottom of chat with each update
    }

    public static ChatController getInstance() {
        if (chatController == null) {
            chatController = new ChatController();
        }
        return chatController;
    }
}