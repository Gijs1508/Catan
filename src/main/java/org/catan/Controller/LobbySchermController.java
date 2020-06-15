package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;
import org.catan.logic.DocumentListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LobbySchermController implements Initializable, Observable {

    private Long gameCode;
    private static LobbySchermController lobbySchermController = new LobbySchermController();
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
    @FXML Text game_code;
    @FXML Button startGameBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1pane.setVisible(false);
        player2pane.setVisible(false);
        player3pane.setVisible(false);
        player4pane.setVisible(false);
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        System.out.println(lobbySchermController.getGameCode());
        if (lobbySchermController.getGameCode() != null) {
            Game game = dbConnector.getGameById(lobbySchermController.getGameCode());
            game_code.setText("Game code: " + game.getCode());
            setupGamePlayers(game.getPlayers());
        } else {
            Game game = new Game();
            game.addSpeler(App.getClientPlayer());
            dbConnector.createGame(game);
            game_code.setText("Game code: " + game.getCode());
            App.setCurrentGameCode(game.getCode());
            DocumentListener gameListener = new DocumentListener(String.valueOf(game.getCode()));
            setupGamePlayers(game.getPlayers());
            App.addListener(gameListener);
        }
        lobbySchermController = this;
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
                player2pane.setVisible(false);
                player3pane.setVisible(false);
                player4pane.setVisible(false);
                break;
            case 2:
                System.out.println("Making 2 players visible");
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                player2pane.setVisible(true);
                player2name.setText(players.get(1).getName());
                player3pane.setVisible(false);
                player4pane.setVisible(false);
                break;
            case 3:
                player1pane.setVisible(true);
                player1name.setText(players.get(0).getName());
                player2pane.setVisible(true);
                player2name.setText(players.get(1).getName());
                player3pane.setVisible(true);
                player3name.setText(players.get(2).getName());
                player4pane.setVisible(false);
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
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        System.out.println("Start removing player");
        Game game = dbConnector.getGameById(App.getCurrentGameCode());
        game.removePlayer(App.getClientPlayer());
        dbConnector.updateGame(game);
        System.out.println("Player removed");
        App.setStageSize(960, 540);
        App.setRoot("Views/mainView");
    }

    private void genoegAantalSpelers() {

    }

    private void startTimer() {

    }

    @Override
    public void update(Game game) {
        setupGamePlayers(game.getPlayers());
        if (game.getPlayers().size() > 1 && App.getClientPlayer().isHost()) {
            startGameBtn.setDisable(false);
        } else {
            startGameBtn.setDisable(true);
        }
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

    private void updatePlayers(Game game) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getIdentifier() == App.getClientPlayer().getIdentifier()) {
                App.setClientPlayer(game.getPlayers().get(i));
            }
            updatePlayerColors(i, game.getPlayers());
        }
    }

    private void updatePlayerColors(int i, ArrayList<Player> players) {
        switch (i) {
            case 0:
                players.get(i).setColor("red");
                players.get(i).setHost(true);
                break;
            case 1:
                players.get(i).setColor("blue");
                break;
            case 2:
                players.get(i).setColor("green");
                break;
            case 3:
                players.get(i).setColor("yellow");
        }
    }
}
