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
 * @author Werner
 */
public class App extends Application {

    private static Scene scene;
    private static String viewName;
    private static Stage appStage;
    private static Player clientPlayer;
    private static Game currentGame;
    private static DocumentListener gameListener;
    private static DocumentListener chatListener;

    /**
     * The main function that start the whole game
     * @param stage
     * @throws IOException
     */
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

    /**
     * Static function that allows for changing the scene root
     * @param fxml
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        viewName = fxml;
    }

    /**
     * Static function that gets the view name of the current root
     * @return
     */
    public static String getViewNameGlobally() {
        return viewName;
    }


    /**
     * Static function that changes the window height of the application
     * @param height
     */
    public static void setStageHeight(int height) {
        appStage.setHeight(height);
    }

    /**
     * Static function that loads a new fxml file
     * @param fxml
     * @return
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Static function that gets the Player object that is bound to the instance of the game that is running
     * @return
     */
    public static Player getClientPlayer() {
        return clientPlayer;
    }

    /**
     * Static function that sets the Player object that is bound to the instance of the game that is running
     * @param player
     */
    public static void setClientPlayer(Player player) {
        clientPlayer = player;
    }

    /**
     * The main function
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Static function that gets the Game that is going on in the client instance
     * @return
     */
    public static Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Static function that sets the Game that is going on in the client instance
     * @param game
     */
    public static void setCurrentGame(Game game) {
        currentGame = game;
    }

    /**
     * Static function that returns the DocumentListener that is bound to the currentGame object
     * @return
     */
    public static DocumentListener getGameListener() {
        return gameListener;
    }

    /**
     * Static function that sets the DocumentListener that is bound to the currentGame object
     * @param gameListener
     */
    public static void setGameListener(DocumentListener gameListener) {
        App.gameListener = gameListener;
    }

    /**
     * Static function that returns the DocumentListener that is bound to the Chat object which belongs to the game object
     * @return
     */
    public static DocumentListener getChatListener() {
        return chatListener;
    }

    /**
     * Static function that sets the DocumentListener that is bound to the Chat object which belongs to the game object
     * @param chatListener
     */
    public static void setChatListener(DocumentListener chatListener) {
        App.chatListener = chatListener;
    }

    /**
     * Static function that resets the documentListeners
     */
    public static void resetListeners() {
        gameListener = null;
        chatListener = null;
    }
}