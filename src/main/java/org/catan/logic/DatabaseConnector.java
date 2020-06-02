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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ArrayList<Spel> getAllGames() {
        ArrayList<Spel> games = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = this.db.collection("games").get();
        try {
            QuerySnapshot snapshot = query.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                games.add(document.toObject(Spel.class));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public Spel getGameById(String id) {
        Spel game = new Spel();
        CollectionReference collectionReference = this.db.collection("games");
        Query query = collectionReference.whereEqualTo("code", id);
        ObjectMapper objectMapper = new ObjectMapper();

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> gameData = document.getData();
                game = objectMapper.convertValue(gameData, Spel.class);
                System.out.println(game.getCode());
                return game;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return game;
    }

    public ArrayList<Spel> getGamesByStatus(String status) {
        ArrayList<Spel> games = new ArrayList<>();
        CollectionReference collectionReference = this.db.collection("games");
        Query query = collectionReference.whereEqualTo("status", status);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                games.add(document.toObject(Spel.class));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public void updateGame(Spel game) {
        DocumentReference documentReference = this.db.collection("games").document(game.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> dataMap = objectMapper.convertValue(game, HashMap.class);
        dataMap.put("spelers", objectMapper.convertValue(game.getSpelers(), HashMap.class));
        ApiFuture<WriteResult> result = documentReference.set(dataMap);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createGame(Spel game) {
        DocumentReference documentReference = this.db.collection("games").document(game.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> dataMap = objectMapper.convertValue(game, HashMap.class);
        dataMap.put("spelers", objectMapper.convertValue(game.getSpelers(), HashMap.class));
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
