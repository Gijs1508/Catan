package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Controller.*;
import org.catan.Model.Chat;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.w3c.dom.Document;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentListener {

    private DocumentReference docRef;
    private String collection;

    public DocumentListener(String collection, String documentId) {
        this.collection = collection;
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.docRef = dbConnector.getDb().collection(collection).document(documentId);
        this.docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }
                // In this if statement, all the controllers that should be called when a game document is updated, should be put
                if (snapshot != null && snapshot.exists()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (collection) {
                                case "games":
                                    try {
                                        updateGameDocument(snapshot);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                    break;
                                case "chats":
                                    updateChatDocument(snapshot);
                                    break;
                            }
                        }
                    });
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }

    private void updateChatDocument(DocumentSnapshot snapshot) {
        ObjectMapper mapper = new ObjectMapper();
        Chat chat = mapper.convertValue(snapshot.getData(), Chat.class);
        ChatController.getInstance().update(chat);
    }


    private void updateGameDocument(DocumentSnapshot snapshot) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Game game = mapper.convertValue(snapshot.getData(), Game.class);

        switch (game.getStatus()) {
            case "open":
                LobbySchermController.getInstance().update(game);
                break;
            case "going":
                if (App.getCurrentGame().getStatus().equals("going")) {
                    BuildSettlementController.getInstance().update(game);
                    GameSchermController.getInstance().update(game);
                    ThiefController.getInstance().update(game);
                    LogController.getInstance().update(game);
                    StockController.getInstance().update(game);
                    TradeController.getInstance().update(game);
                    StartPhaseController.getInstance().update(game);
                    TradePopUpController.getInstance().update(game);
                }
                if (App.getCurrentGame().getStatus().equals("open")) {
                    DocumentListener chatListener = new DocumentListener("chats", String.valueOf(game.getCode()));
                    App.setChatListener(chatListener);
                    LobbySchermController.getInstance().updateScreenRoot();
                }
                break;
        }
        updateClientPlayer(game.getPlayers());
        App.setCurrentGame(game);
    }

    private void updateClientPlayer(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getIdentifier() == App.getClientPlayer().getIdentifier()) {
                App.setClientPlayer(player);
            }
        }
    }

    public String getCollection() {
        return this.collection;
    }
}
