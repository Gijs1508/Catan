package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.catan.Model.*;
import org.catan.logic.DatabaseConnector;
import org.catan.Controller.TradePopUpController;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;
import org.catan.logic.GameDataPrinter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    private AnchorPane screenView;
    private AnchorPane boardView;
    private AnchorPane stockView;
    private AnchorPane logView;
    private AnchorPane chatView;


    @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnector dbConnector = new DatabaseConnector();
//        Game game = new Game();
//        Player jan = new Player("Jan");
//        Player sabrina = new Player("Sabrina");
//        sabrina.setColor("Red");
//        jan.setColor("Pink");
//        game.addSpeler(jan);
//        game.addSpeler(sabrina);
//        Village janVillage = new Village(20, 40, "blue", new ArrayList<>());
//        Tile tile1 = new Tile("wood", "tile1", 3);
//        Tile tile2 = new Tile("ore", "tile2", 8);
//        Tile tile3 = new Tile("stone", "tile3", 10);
//        janVillage.addConnectedTile(tile1);
//        janVillage.addConnectedTile(tile2);
//        janVillage.addConnectedTile(tile3);
//        Village sabrinaVillage = new Village(40, 20, "red", new ArrayList<>());
//        Road janRoad = new Road(10, 30, "blue");
//        Road sabrinaRoad = new Road(20, 40, "red");
//        game.getBoard().addSettlement(janVillage);
//        game.getBoard().addSettlement(sabrinaVillage);
//        game.getBoard().addRoad(janRoad);
//        game.getBoard().addRoad(sabrinaRoad);
//        Log log = new Log("upgrade", jan.getName());
//        log.addImgPath("4");
//        log.addImgPath("3");
//        game.addLog(log);
//        dbConnector.createGame(game);
//        System.out.println(game.getCode());
        Game game = dbConnector.getGameById("Y5H81CJIK9");
        GameDataPrinter.printGameDetails(game);

//        scene = new Scene(loadFXML("Views/screenView"));
//        scene = new Scene(loadFXML("Views/mainView"));

//        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
//        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("assets/img/appicon.png"))));
//        stage.setTitle("Kolonisten van Catan");
//        stage.setScene(scene);
//        stage.setResizable(false);
//
//        // Main player = Player controlling the instance of the game
//        Player testPlayer1 = new Player("testPlayer"); //TODO Moet aangemaakt worden bij het opstarten/joinen van het spel
//        testPlayer1.setMainPlayer(testPlayer1);
//
//        // Other test players
//        Player testPlayer2 = new Player("testPlayer2");
//        Player testPlayer3 = new Player("testPlayer3");
//        Player testPlayer4 = new Player("testPlayer4");
//
//        // Initialize first active player
//        Player.setActivePlayer(testPlayer1);
//
//        stage.show();
    }

    public static void tradePopUp() throws IOException{
        scene = new Scene(loadFXML("Views/tradePopUpView"));
        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Trade offer");
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