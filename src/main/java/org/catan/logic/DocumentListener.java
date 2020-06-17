package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import javafx.fxml.FXMLLoader;
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
        System.out.println("collection: " + collection);
        System.out.println("Doc id: " + documentId);
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
                    switch (collection) {
                        case "games":
                            updateGameDocument(snapshot);
                            break;
                        case "chats":
                            updateChatDocument(snapshot);
                            break;
                    }
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


    private void updateGameDocument(DocumentSnapshot snapshot) {
        ObjectMapper mapper = new ObjectMapper();
        Game game = mapper.convertValue(snapshot.getData(), Game.class);

        switch (game.getStatus()) {
            case "open":
                System.out.println("Start lobby update");
                FXMLLoader loader = new FXMLLoader();
                LobbySchermController.getInstance().update(game);
                BuildSettlementController.getInstance().update(game);
                break;
            case "going":
                System.out.println("going game");
                System.out.println(App.getCurrentGame().getStatus());
                if (App.getCurrentGame().getStatus().equals("going")) {
                    System.out.println("updating logs");
                    LogController.getInstance().update(game);
                }
                if (App.getCurrentGame().getStatus().equals("open")) {
                    DocumentListener chatListener = new DocumentListener("chats", String.valueOf(game.getCode()));
                    App.addListener(chatListener);
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
