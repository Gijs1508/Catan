package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

import java.io.IOException;

/**
 * Used to handle button clicks on the rules screen.
 *
 * @Author Gijs van der Weijden
 */
public class RulesController implements Observable {
    // Routes
    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./Views/mainViewTrade");
    }

    @Override
    public void update(Game game) {

    }
}
