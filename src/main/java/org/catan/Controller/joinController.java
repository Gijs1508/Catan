package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;

import java.io.IOException;

public class joinController {
    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }
}
