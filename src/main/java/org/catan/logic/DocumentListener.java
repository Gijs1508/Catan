package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Controller.BuildSettlementController;
import org.catan.Controller.LobbySchermController;
import org.catan.Model.Game;
import org.catan.Model.Player;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentListener {

    private DocumentReference docRef;

    public DocumentListener(String collection, String documentId) {
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
                    ObjectMapper mapper = new ObjectMapper();
                    Game game = mapper.convertValue(snapshot.getData(), Game.class);
                    System.out.println(game.getStatus());

                    switch (game.getStatus()) {
                        case "open":
                            System.out.println("Start lobby update");
                            FXMLLoader loader = new FXMLLoader();
                            LobbySchermController.getInstance().update(game);
                            BuildSettlementController.getInstance().update(game);
                            break;
                        case "going":
                            if (App.getCurrentGame().getStatus().equals("open")) {
                                try {
                                    App.setRoot("./Views/screenView");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;
                    }
                    updateClientPlayer(game.getPlayers());
                    App.setCurrentGame(game);
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }

    private void updateClientPlayer(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getIdentifier() == App.getClientPlayer().getIdentifier()) {
                App.setClientPlayer(player);
            }
        }
    }
}
