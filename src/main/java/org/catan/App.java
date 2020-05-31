package org.catan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.catan.Controller.tradeController;
import org.catan.Model.Player;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Views/mainViewTrade"));
        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void tradePopUp(Player player) throws IOException {
        scene = new Scene(loadFXML("Views/tradeView2"));
        scene.getStylesheets().add(App.class.getResource("assets/style/style.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        //tradeController.updateInventory(player);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}