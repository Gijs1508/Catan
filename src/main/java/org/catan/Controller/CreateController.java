package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.CreateGameCode;

import java.io.IOException;

public class CreateController {
    @FXML private Text game_code_text;

    public CreateController(){
    }

    // Routes
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainViewTrade");
    }
    public void joinGame() throws IOException{
        App.setRoot("./views/joinView");
    }

    // Create new game
    public void createGame(ActionEvent actionEvent) {
        // TODO Werner: add code to db and check if it does not exist
        long gameCode = CreateGameCode.getSeed();
        game_code_text.setText(String.valueOf(gameCode));
    }
}
