package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.catan.App;

import java.io.IOException;

public class RulesController {
    // Routes
    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }
}
