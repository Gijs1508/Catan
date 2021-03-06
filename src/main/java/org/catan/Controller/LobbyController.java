package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;
import org.catan.logic.DocumentListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Handles the interactivity and functionality of the lobby screen.
 */

public class LobbyController implements Initializable, Observable {
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
    @FXML private Pane alertPopup;

    private Long gameCode;
    private static LobbyController lobbyController = new LobbyController();
    private AnchorPane alertPopupView;


    /** Initializes the lobby screen with the information that exists. */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Sound.pauseMenuMusic();

        player1pane.setVisible(false);
        player2pane.setVisible(false);
        player3pane.setVisible(false);
        player4pane.setVisible(false);
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();

        try {
            alertPopupView = (AnchorPane) App.loadFXML("Views/alertPopUpView");
        } catch (IOException e) {
            e.printStackTrace();
        }
        alertPopup.getChildren().setAll(alertPopupView);

        if (lobbyController.getGameCode() != null) {
            Game game = dbConnector.getGameById(lobbyController.getGameCode());
            game_code.setText("Game code: " + game.getCode());
            setupGamePlayers(game.getPlayers());
        } else {
            Game game = new Game();
            game.addSpeler(App.getClientPlayer());
            dbConnector.createGame(game);
            game_code.setText("Game code: " + game.getCode());
            App.setCurrentGame(game);
            DocumentListener gameListener = new DocumentListener("games", String.valueOf(game.getCode()));
            setupGamePlayers(game.getPlayers());
            App.setGameListener(gameListener);
        }

        initializePopup(alertPopup);

        lobbyController = this;
    }

    // Starts the game
    @FXML private void startGame() throws IOException {
        Sound.playClick();

        Game game = App.getCurrentGame();
        game.getPlayers().get(0).setTurn(true);
        game.setStatus("going");
        DatabaseConnector.getInstance().updateGame(game);
        Chat chat = new Chat(game.getCode().intValue());
        DatabaseConnector.getInstance().createChat(chat);
        App.setChatListener(new DocumentListener("chats", String.valueOf(game.getCode())));
        App.setRoot("./Views/screenView");
    }

    /** Takes the player to the game screen */
    public void updateScreenRoot() {
        try {
            App.setRoot("./Views/screenView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void leaveGame() throws IOException {
        Sound.playClick();

        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        Game game = dbConnector.getGameById(App.getCurrentGame().getCode());
        game.removePlayer(App.getClientPlayer());
        App.resetListeners();
        updatePlayers(game);
        dbConnector.updateGame(game);
        App.setStageHeight(718);
        App.setRoot("Views/mainView");

        if(Sound.introMusicIsPlaying()) {
            Sound.playMenuMusic();
        }
    }

    /** Updates the game. Enables/disables the start buttons for the host according to the player count. */
    @Override public void update(Game game) {
        setupGamePlayers(game.getPlayers());
        if (game.getPlayers().size() > 1 && App.getClientPlayer().isHost()) {
            startGameBtn.setDisable(false);
        } else {
            startGameBtn.setDisable(true);
        }
    }

    // Update the game with the players that joined the lobby
    private void updatePlayers(Game game) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (i == 0) {
                game.getPlayers().get(i).setHost(true);
            }
            if (game.getPlayers().get(i).getIdentifier() == App.getClientPlayer().getIdentifier()) {
                App.setClientPlayer(game.getPlayers().get(i));
            }
            updatePlayerColors(i, game.getPlayers());
        }
    }

    // Player clicked on the code, to copy it to their clipboard
    @FXML private void textClicked() {
        Clipboard cb = Clipboard.getSystemClipboard();
        ClipboardContent cbc = new ClipboardContent();
        cbc.putString(game_code.getText().replace("Game code: ", ""));
        cb.setContent(cbc);
        createAlert();
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

    // Creates an alert
    private void createAlert() {
        showAlertPopup();
        AlertPopUpController.getInstance().setAlertTitle("Copied!");
        AlertPopUpController.getInstance().setAlertPlacedController(this.getClass());
        AlertPopUpController.getInstance().setAlertDescription("Game code is copied to the clipboard.");
    }

    // Updates the colors of the players
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


    // Makes sure popups are ready to show when needed
    private void initializePopup(Pane popupPane) {
        popupPane.setVisible(false);
        popupPane.setOpacity(1);
        popupPane.setStyle("-fx-background-color: none;");
    }

    public void showAlertPopup() {
        alertPopup.setVisible(true);
    }
    public void hideAlertPopup() {
        alertPopup.setVisible(false);
    }

    public void setGameCode(Long gameCode) {
        this.gameCode = gameCode;
    }

    public Long getGameCode() {
        return this.gameCode;
    }

    public static LobbyController getInstance() {
        if (lobbyController == null) {
            lobbyController = new LobbyController();
        }
        return lobbyController;
    }
}
