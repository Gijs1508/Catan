package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
 *
 * @Author Gijs van der Weijden
 */
public class JoinController implements Observable, Initializable {
    @FXML private TextField code_input;
    @FXML private Label error_text;

    private Long gameCode;
    private LobbySchermController lobbySchermController = LobbySchermController.getInstance();
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
    private void createGame() throws IOException{
        Sound.playClick();
        App.setRoot("./views/createView");
    }

    @FXML
    private void handleButtonJoinAction(MouseEvent mouseEvent) throws IOException{
        Sound.playClick();

        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        Long code = Long.valueOf(code_input.getText());
        Game game = dbConnector.getGameById(code);
        if (game.getCode().equals(code)) {
            if (game.getStatus().equals("open")) {
                if (game.getPlayers().size() < 4) {
                    this.lobbySchermController.setGameCode(code);
                    setPlayerColor(game.getPlayers().size(), App.getClientPlayer());
                    App.getClientPlayer().setHost(false);
                    App.setCurrentGame(game);
                    game.addSpeler(App.getClientPlayer());
                    dbConnector.updateGame(game);
                    addGameListener(game);
                    App.setStageHeight(837);
                    App.setRoot("./views/lobbyView");
                } else {
                    this.error_text.setText("Het spel zit al vol");
                    this.error_text.setVisible(true);
                }
            } else {
                this.error_text.setText("Dit spel kan niet meer worden gejoined");
                this.error_text.setVisible(true);
            }
        } else {
            this.error_text.setText("Invalide code ingevoerd");
            this.error_text.setVisible(true);
        }
    }

    private void addGameListener(Game game) {
        DocumentListener gameListener = new DocumentListener("games", String.valueOf(game.getCode()));
        App.addListener(gameListener);
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
