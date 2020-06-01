package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.catan.Model.Spel;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

public class DatabaseConnector {
    Firestore db;

    public DatabaseConnector() {

        try {
            File accountKey = new File("src/main/resources/org/catan/credentials/FirestoreKey.json");
            FileInputStream serviceAccount =
                    new FileInputStream(accountKey);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://iipsene-test.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.db = FirestoreClient.getFirestore();
    }

    public void getAllGames() {

    }

    public void getGameById(String id) {
        ApiFuture<QuerySnapshot> query = this.db.collection("games").get();
        try {
            QuerySnapshot snapshot = query.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Spel game = document.toObject(Spel.class);
                System.out.println(game.getCode());
                System.out.println(game.getSpelers().get("Jan").getNaam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGamesByStatus(String status) {

    }

    public void updateGame(Spel game) {

    }

    public void createGame(Spel game) {
        DocumentReference documentReference = this.db.collection("games").document(game.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> dataMap = objectMapper.convertValue(game, HashMap.class);
        dataMap.put("spelers", objectMapper.convertValue(game.getSpelers(), HashMap.class));
        System.out.println(dataMap);
        ApiFuture<WriteResult> result = documentReference.set(dataMap);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getChats() {

    }

    public void getChatById(int id) {

    }

    public void updateChat() {

    }

    public void createChat() {

    }
}
