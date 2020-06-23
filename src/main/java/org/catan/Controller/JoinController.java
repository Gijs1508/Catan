package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.Model.MenuMusicHandler;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;
import org.catan.logic.DocumentListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Used to handle button clicks on the join screen.
 * @Author Gijs van der Weijden
 */
public class JoinController implements Observable, Initializable {
    @FXML private TextField code_input;
    @FXML private Label error_text;

    private Long gameCode;
    private LobbyController lobbyController = LobbyController.getInstance();
    @FXML private ImageView musicBtn;

    // Routes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    @FXML
    private void backToMenu() throws IOException {
        Sound.playClick();
        App.setRoot("./views/mainView");
    }

    @FXML
    private void handleButtonJoinAction() throws IOException{
        Sound.playClick();
        this.error_text.setVisible(false);

        try {
            DatabaseConnector dbConnector = DatabaseConnector.getInstance();
            Long code = Long.valueOf(code_input.getText());
            Game game = dbConnector.getGameById(code);
            if (game.getCode().equals(code)) {
                if (game.getStatus().equals("open")) {
                    if (game.getPlayers().size() < 4) {
                        this.lobbyController.setGameCode(code);
                        setPlayerColor(game.getPlayers().size(), App.getClientPlayer());
                        App.getClientPlayer().setHost(false);
                        App.setCurrentGame(game);
                        game.addSpeler(App.getClientPlayer());
                        dbConnector.updateGame(game);
                        addGameListener(game);
                        App.setStageHeight(837);
                        App.setRoot("./views/lobbyView");
                    } else {
                        this.error_text.setText("Game is full");
                        this.error_text.setVisible(true);
                    }
                } else {
                    this.error_text.setText("Game couldn't be joined");
                    this.error_text.setVisible(true);
                }
            }
        }
        catch(NumberFormatException e) {
            this.error_text.setText("Code is invalid");
            this.error_text.setVisible(true);
        }
    }

    private void addGameListener(Game game) {
         App.setGameListener(new DocumentListener("games", String.valueOf(game.getCode())));

    }

    private void setPlayerColor(int playerSize, Player player) {
        switch (playerSize) {
            case 1:
                player.setColor("blue");
                break;
            case 2:
                player.setColor("green");
                break;
            case 3:
                player.setColor("yellow");
        }
    }

    private void setGameCode(Long gameCode) {
        this.gameCode = gameCode;
    }

    @FXML
    private void toggleMusic() {
        Sound.playClick();
        MenuMusicHandler.toggleMusic(musicBtn);
    }

    @Override
    public void update(Game game) {

    }
}
