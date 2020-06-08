package org.catan.logic;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;

import javax.annotation.Nullable;

public class DocumentListener {
    public DocumentListener(String gameId) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        DocumentReference documentReference = dbConnector.getDb().collection("games").document(gameId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }
}
