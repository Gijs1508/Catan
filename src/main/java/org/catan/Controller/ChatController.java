package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.ChatObservable;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Takes care of the user input and updates the chat.
 * @author Jeroen
 * */

public class ChatController implements Initializable, ChatObservable {
    @FXML private TextField messageField;
    @FXML private TextArea chatBox;
    @FXML private ScrollPane scrollPane;
    @FXML private Text msgContent;
    @FXML private Text sender;
    private static ChatController chatController = new ChatController();
    Chat chat; // TODO get ID of game

    /** Reads the input and gives it to the chat view. */
    @FXML private void sendMessage() {
        if(messageField.getText().length() > 0 && messageField.getText().length() < 100) {
            ChatMessage message = new ChatMessage(messageField.getText());
            chat.addChatMessage(message);
            messageField.clear();
            DatabaseConnector.getInstance().updateChat(chat);
            updateChatView();
            new ChatThread(message.getMessage(), 5);
        }
        else { messageField.clear(); }
    }

    private void updateChatView() {
        chatBox.clear();
        chatBox.appendText(chat.generateChatString());
    }

    @Override
    public void update(Chat chat) {
        if (chat.getChatMessages().size() > this.chat.getChatMessages().size()) {
            for (int i = this.chat.getChatMessages().size(); i < chat.getChatMessages().size(); i++) {
                this.chat.addChatMessage(chat.getChatMessages().get(i));
            }
            updateChatView();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatController = this;
        chat = new Chat(App.getCurrentGame().getCode().intValue());
        chatBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));
    }

    public static ChatController getInstance() {
        if (chatController == null) {
            chatController = new ChatController();
        }
        return chatController;
    }
}