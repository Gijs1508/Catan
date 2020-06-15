package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class MainController implements Observable {

    private LobbySchermController lobbySchermController = LobbySchermController.getInstance();

    // Routes
    @FXML
    private void joinGame() throws IOException {
        App.setRoot("./Views/joinView");
    }

    @FXML
    private void startGame() throws IOException {
        App.setStageSize(1200, 810);
        App.setRoot("./Views/lobbyView");
    }

    @FXML
    private void viewRules() throws IOException {
        App.setRoot("./Views/regelsView");
    }

    @Override
    public void update(Game game) {

    }
}
