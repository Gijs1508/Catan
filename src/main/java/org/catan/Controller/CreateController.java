package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.CreateGameCode;

import java.io.IOException;

public class CreateController {
    @FXML private Text game_code_text;

    // Routes
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainViewTrade");
    }
    public void joinGame() throws IOException{
        App.setRoot("./views/joinView");
    }

    // Create new game
    @FXML
    public void createGame(ActionEvent actionEvent) {
        // TODO Werner: add code to db and check if it does not exist
        var code = CreateGameCode.getSeed();
        game_code_text.setText(String.valueOf(code));
    }


}
