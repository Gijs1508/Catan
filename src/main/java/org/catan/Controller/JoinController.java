package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.catan.App;

import java.io.IOException;

public class JoinController {
    @FXML private TextField code_input;
    @FXML private Text error_text;

    // Routes
    @FXML
    private void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }

    @FXML
    private void createGame() throws IOException{
        App.setRoot("./views/createView");
    }

    @FXML
    private void handleButtonJoinAction(ActionEvent actionEvent) throws IOException{
        var code_text = code_input.getText();
        // TODO Werner: check if game code exists + is open!
        if(code_text.equals("testcode")){
            //TODO Jeroen: Create route to gameview here :)
            //App.setRoot("./Views/gameView");
            System.out.println("haha leuk je code klopt");
        } else {
            error_text.setText("Code is onjuist!");
        }
    }

}
