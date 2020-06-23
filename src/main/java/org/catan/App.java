package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.catan.Controller.StockController;
import org.catan.Model.*;
import org.catan.Model.Player;
import org.catan.logic.DocumentListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.Random;

/**
 * JavaFX App. First class to get accessed.
 */
public class App extends Application {

    private static Scene scene;
    private static String viewName;
    private static Stage appStage;
    private static Player clientPlayer;
    private static Game currentGame;
    private static DocumentListener gameListener;
    private static DocumentListener chatListener;

    @Override
    public void start(Stage stage) throws IOException {
        Sound.initializeSounds();
        clientPlayer = new Player("");
        scene = new Scene(loadFXML("Views/mainView"));
//        MainController.getInstance().playAnimation();

        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("assets/img/appicon.png"))));
        stage.setTitle("Kolonisten van Catan");
        stage.setScene(scene);
        stage.setResizable(false);
        appStage = stage;

        // Main player = Player controlling the instance of the game
        //Player testPlayer1 = new Player("testPlayer"); //TODO Moet aangemaakt worden bij het opstarten/joinen van het spel
        //testPlayer1.setMainPlayer(testPlayer1);

        // Initialize first active player
        Random random = new Random();

        // TODO dit moet ergens anders maar ik weet niet waar. als het spel start moet een random speler als mainspeler gezet worden!
        //Player.setActivePlayer(Player.getAllPlayers().get(random.nextInt(4)));

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        viewName = fxml;
    }

    public static String getViewNameGlobally() {
        return viewName;
    }


    public static void setStageHeight(int height) {
        appStage.setHeight(height);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Player getClientPlayer() {
        return clientPlayer;
    }

    public static void setClientPlayer(Player player) {
        clientPlayer = player;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game game) {
        currentGame = game;
    }

    public static DocumentListener getGameListener() {
        return gameListener;
    }

    public static void setGameListener(DocumentListener gameListener) {
        App.gameListener = gameListener;
    }

    public static DocumentListener getChatListener() {
        return chatListener;
    }

    public static void setChatListener(DocumentListener chatListener) {
        App.chatListener = chatListener;
    }

    public static void resetListeners() {
        gameListener = null;
        chatListener = null;
    }
}