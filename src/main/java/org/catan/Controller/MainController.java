package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class MainController implements Observable {

    @FXML
    private TextField player_name_input;
    // Routes
    @FXML
    private void joinGame() throws IOException {
        if(nameIsSet()){
            Player player = new Player(player_name_input.getText());
            App.setRoot("./Views/joinView");
        } else {
            createAlert();

        }
    }


    @FXML
    private void startGame() throws IOException {
        if(nameIsSet()){
            Player player = new Player(player_name_input.getText());
            App.setRoot("./Views/createView");
        } else {
            createAlert();
        }
    }

    @FXML
    private void viewRules() throws IOException {
        App.setRoot("./Views/regelsView");
    }

    @Override
    public void update(Game game) {

    }

    private void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("You didn't fill in a name!");
        alert.show();
    }

    private boolean nameIsSet(){
        return !player_name_input.getText().equals("");
    }
}
