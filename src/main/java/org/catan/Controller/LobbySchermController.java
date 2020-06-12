package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LobbySchermController implements Initializable, Observable {

    private Long gameCode;
    private static LobbySchermController lobbySchermController;
    private int aantalSpelers;
    private int minimumAantalSpelers;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @FXML Pane player1pane;
    @FXML Pane player2pane;
    @FXML Pane player3pane;
    @FXML Pane player4pane;
    @FXML Text player1name;
    @FXML Text player2name;
    @FXML Text player3name;
    @FXML Text player4name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        System.out.println(lobbySchermController.getGameCode());
        if (lobbySchermController.getGameCode() != null) {
            Game game = dbConnector.getGameById(lobbySchermController.getGameCode());
            setupGamePlayers(game.getPlayers());
        }
    }

    @FXML
    private void startGame() {
        // Deze functionaliteit zal verplaatst en uitgebreid worden (voor wanneer een speler de sessie betreed).
        player1pane.setVisible(true);
    }

    private void setupGamePlayers(ArrayList<Player> players) {
        switch (players.size()) {
            case 1:
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                break;
            case 2:
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                player2pane.setVisible(true);
                player2name.setText(players.get(1).getName());
                break;
            case 3:
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                player2pane.setVisible(true);
                player2name.setText(players.get(1).getName());
                player3pane.setVisible(true);
                player3name.setText(players.get(2).getName());
                break;
            case 4:
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                player2pane.setVisible(true);
                player2name.setText(players.get(1).getName());
                player3pane.setVisible(true);
                player3name.setText(players.get(2).getName());
                player4pane.setVisible(true);
                player4name.setText(players.get(3).getName());
                break;
        }
    }

    @FXML
    private void leaveGame() throws IOException {
        App.setRoot("Views/joinView");
    }

    private void genoegAantalSpelers() {

    }

    private void startTimer() {

    }

    @Override
    public void update(Game game) {

    }

    public static LobbySchermController getInstance() {
        if (lobbySchermController == null) {
            lobbySchermController = new LobbySchermController();
        }

        return lobbySchermController;
    }

    public void setGameCode(Long gameCode) {
        this.gameCode = gameCode;
    }

    public Long getGameCode() {
        return this.gameCode;
    }
}
