package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import org.catan.Model.Game;

import javax.annotation.Nullable;

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
                    System.out.println("data: " + snapshot.getData());
                    ObjectMapper mapper = new ObjectMapper();
                    Game game = mapper.convertValue(snapshot.getData(), Game.class);
                    System.out.println("updated game code: " + game.getCode());
                    GameDataPrinter.printGameDetails(game);
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }

    public void removeListener() {
        this.docRef = null;
    }

}
