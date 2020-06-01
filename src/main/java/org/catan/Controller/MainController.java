package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Model.Player;

import java.io.IOException;

public class MainController {

    // Routes
    @FXML
    public void joinGame() throws IOException {
        App.setRoot("./Views/joinView");
    }

    @FXML
    public void startGame() throws IOException {
        App.setRoot("./Views/createView");
    }

    @FXML
    public void viewRules() throws IOException {
        App.setRoot("./Views/regelsView");
    }

    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./Views/mainViewTrade");
    }

    @FXML
    public void tradeTest() throws IOException {
        Player player1 = new Player("testplayer");
        player1.getPlayerInventory().changeCards(0, 5);
        player1.getPlayerInventory().changeCards(4, 2);

        //App.tradePopUp(player1);
    }
}
