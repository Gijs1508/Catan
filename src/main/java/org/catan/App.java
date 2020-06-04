package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.catan.Model.Log;
import org.catan.Model.Game;
import org.catan.logic.DatabaseConnector;

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
        Game game = new Game();
        Speler jan = new Speler("Jan");
        Speler sabrina = new Speler("Sabrina");
        sabrina.setKleur("Red");
        jan.setKleur("Pink");
        game.addSpeler(jan);
        game.addSpeler(sabrina);
        Log log = new Log("roll", jan.getNaam());
        log.createImage("4");
        log.createImage("3");
        game.addLog(log);
        dbConnector.createGame(game);
        System.out.println(game.getCode());

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