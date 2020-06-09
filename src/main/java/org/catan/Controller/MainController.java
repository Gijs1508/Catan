package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Model.Player;

import java.io.IOException;

public class MainController {

    // Routes
    @FXML
    private void joinGame() throws IOException {
        App.setRoot("./Views/joinView");
    }

    @FXML
    private void startGame() throws IOException {
        App.setRoot("./Views/createView");
    }

    @FXML
    private void viewRules() throws IOException {
        App.setRoot("./Views/regelsView");
    }
}
