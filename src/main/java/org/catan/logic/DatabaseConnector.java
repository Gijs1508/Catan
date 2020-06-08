package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.catan.Model.Game;
import org.catan.Model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class DatabaseConnector {
    private Firestore db;

    public static DatabaseConnector connector;

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
        connector = this;
    }

    public static DatabaseConnector getInstance() {
        if (connector == null) {
            connector = new DatabaseConnector();
        }
        return connector;
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = this.db.collection("games").get();

        try {
            QuerySnapshot snapshot = query.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                games.add(document.toObject(Game.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public Game getGameById(String id) {
        Game game = new Game();
        CollectionReference collectionReference = this.db.collection("games");
        Query query = collectionReference.whereEqualTo("code", id);
        ObjectMapper objectMapper = new ObjectMapper();

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> gameData = document.getData();
                game = objectMapper.convertValue(gameData, Game.class);
                return game;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    public ArrayList<Game> getGamesByStatus(String status) {
        ArrayList<Game> games = new ArrayList<>();
        CollectionReference collectionReference = this.db.collection("games");
        Query query = collectionReference.whereEqualTo("status", status);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                games.add(document.toObject(Game.class));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public void updateGame(Game game) {
        DocumentReference documentReference = this.db.collection("games").document(String.valueOf(game.getCode()));

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Map> playerMap = converPlayersToHashMaps(game.getPlayers());

        HashMap<String, Object> dataMap = objectMapper.convertValue(game, HashMap.class);
        dataMap.put("players", playerMap);
        ApiFuture<WriteResult> result = documentReference.update(dataMap);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Map> converPlayersToHashMaps(ArrayList<Player> playerList){
        ArrayList<Map> playerMapList = new ArrayList<>();

        int index = 0;
        ObjectMapper objectMapper = new ObjectMapper();

        for (Player player : playerList) {
            Map<String, Object> playerMap = objectMapper.convertValue(player, Map.class);
            playerMap.put("playerInventory", objectMapper.convertValue(player.getPlayerInventory(), Map.class));
            playerMapList.add(playerMap);
        }

        return playerMapList;
    }

    public void createGame(Game game) {
        DocumentReference documentReference = this.db.collection("games").document(String.valueOf(game.getCode()));

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Map> playerMap = converPlayersToHashMaps(game.getPlayers());

        HashMap<String, Object> dataMap = objectMapper.convertValue(game, HashMap.class);
        dataMap.put("players", playerMap);
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

    public Firestore getDb() {
        return this.db;
    }
}
