package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.App;
import org.catan.Model.Player; // Only for testing purposes, can be removed

import java.io.IOException;

public class mainController {

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
        Player player1 = new Player("Lorem");
        player1.getPlayerInventory().changeCards(0, 5);
        App.tradePopUp(player1);
        tradeController.updateInventory(player1);
    }
}
