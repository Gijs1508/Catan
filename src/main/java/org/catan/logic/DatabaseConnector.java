package org.catan.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.catan.App;
import org.catan.Model.Chat;
import org.catan.Model.Game;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Class that contains the functionality to communicate with the database
 *
 * @author werner
 */
public class DatabaseConnector {
    private Firestore db;

    public static DatabaseConnector connector;

    /**
     * Constructor for db connector, will establish the connection with the db so that it can be used in future functions
     */
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

    /**
     * Function to get the databaseConnector instance, to ensure only one instance is used
     * @return DatabaseConnector
     */
    public static DatabaseConnector getInstance() {
        if (connector == null) {
            connector = new DatabaseConnector();
        }
        return connector;
    }

    /**
     * Function to get all games
     * @return ArrayList<Game>()
     */
    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        ApiFuture<QuerySnapshot> query = this.db.collection("games").get();

        try {
            QuerySnapshot snapshot = query.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> gameData = document.getData();
                games.add(objectMapper.convertValue(gameData, Game.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    /**
     * Function to get a game by it's unique id
     * @param id
     * @return
     */
    public Game getGameById(Long id) {
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

    /**
     * Function to get games, by their status
     *
     * @param status
     * @return
     */
    public ArrayList<Game> getGamesByStatus(String status) {
        ArrayList<Game> games = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        CollectionReference collectionReference = this.db.collection("games");
        Query query = collectionReference.whereEqualTo("status", status);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                games.add(objectMapper.convertValue(document.getData(), Game.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    /**
     * Function to update the data of an existing game
     * @param game
     */
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


    public void updateStock(Inventory inventory) {
        DocumentReference documentReference = this.db.collection("games").document(String.valueOf(App.getCurrentGame().getCode()));
        documentReference.addSnapshotListener((snapshot, e) -> {
            if (e != null) { System.out.println("Listen failed: " + e); return; }

            if(snapshot != null && snapshot.exists()) {
                System.out.println();
            }
        });
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

    /**
     * Function to create a new game in the database
     * @param game
     */
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

    public Chat getChatById(int id) {
        Chat chat = new Chat();
        CollectionReference collectionReference = this.db.collection("chats");
        Query query = collectionReference.whereEqualTo("gameId", id);
        ObjectMapper objectMapper = new ObjectMapper();
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> gameData = document.getData();
                chat = objectMapper.convertValue(gameData, Chat.class);
                return chat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chat;
    }

    public void updateChat(Chat chat) {
        DocumentReference documentReference = this.db.collection("chats").document(String.valueOf(chat.getGameId()));
        System.out.println(documentReference.getPath());
        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, Object> dataMap = objectMapper.convertValue(chat, HashMap.class);
        ApiFuture<WriteResult> result = documentReference.update(dataMap);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createChat(Chat chat) {
        DocumentReference documentReference = this.db.collection("chats").document(String.valueOf(chat.getGameId()));

        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, Object> dataMap = objectMapper.convertValue(chat, HashMap.class);
        ApiFuture<WriteResult> result = documentReference.set(dataMap);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getDb() {
        return this.db;
    }
}
