package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.catan.Model.Log;
import org.catan.Model.Spel;
import org.catan.Model.Speler;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.util.HashMap;

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
        Spel game = new Spel();
        Speler jan = new Speler("Jan");
        Speler sabrina = new Speler("Sabrina");
        sabrina.setKleur("Red");
        jan.setKleur("Pink");
        game.addSpeler(jan);
        game.addSpeler(sabrina);
        dbConnector.createGame(game);
//        Log log = new Log("txt");
//        game.addLog(log);
//        Spel game = dbConnector.getGameById("L5QR6HV14W");
//        System.out.println(game.getCode());

//        scene = new Scene(loadFXML("Views/screenView"));
//        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
//        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("assets/img/appicon.png"))));
//        stage.setTitle("Kolonisten van Catan");
//        stage.setScene(scene);
//
//        stage.setResizable(false);
//        stage.show();

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