package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Helper.fxmlHelper;

import java.io.IOException;

public class mainController {

    @FXML
    public void startGame() throws IOException {
        //App.setRoot(fxmlHelper.loadFXML("Views/createView"));
    }

    public void joinGame() throws IOException {
        App.setRoot("Views/joinView");
    }

    public void viewRules() throws IOException {
        App.setRoot("Views/regelsView");
    }
}
