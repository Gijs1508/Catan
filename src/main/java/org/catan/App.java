package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.catan.Model.*;
import org.catan.logic.CreateTestGame;
import org.catan.logic.DatabaseConnector;
import org.catan.Controller.TradePopUpController;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;
import org.catan.logic.DocumentListener;
import org.catan.logic.GameDataPrinter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Sound.initializeSounds();

        scene = new Scene(loadFXML("Views/screenView"));
        //scene = new Scene(loadFXML("Views/mainView"));

        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("assets/img/appicon.png"))));
        stage.setTitle("Kolonisten van Catan");
        stage.setScene(scene);
        stage.setResizable(false);

        // Main player = Player controlling the instance of the game
        Player testPlayer1 = new Player("testPlayer"); //TODO Moet aangemaakt worden bij het opstarten/joinen van het spel
        testPlayer1.setMainPlayer(testPlayer1);

        // Other test players
        Player testPlayer2 = new Player("testPlayer2");
        Player testPlayer3 = new Player("testPlayer3");
        Player testPlayer4 = new Player("testPlayer4");

        // Initialize first active player
        Player.setActivePlayer(testPlayer1);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}