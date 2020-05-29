package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Helper.createGameCode;

import java.io.IOException;

public class createController {
    @FXML private Text game_code_text;

    // Routes
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }
    public void joinGame() throws IOException{
        App.setRoot("./views/joinView");
    }

    // Create new game
    @FXML
    public void createGame(ActionEvent actionEvent) {
        // TODO Werner: add code to db and check if it does not exist
        var code = createGameCode.randomCodeGen(6);
        game_code_text.setText(code);
    }


}
