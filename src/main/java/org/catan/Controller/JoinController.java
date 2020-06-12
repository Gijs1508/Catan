package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Observable {
    @FXML private TextField code_input;
    @FXML private Text error_text;

    private Long gameCode;
    private LobbySchermController lobbySchermController = LobbySchermController.getInstance();

    // Routes
    @FXML
    private void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }

    @FXML
    private void createGame() throws IOException{
        App.setRoot("./views/createView");
    }

    @FXML
    private void handleButtonJoinAction(ActionEvent actionEvent) throws IOException{
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        Long code = Long.valueOf(code_input.getText());
        Game game = dbConnector.getGameById(code);
        if (game.getCode().equals(code)) {
            if (game.getPlayers().size() < 4) {
                this.lobbySchermController.setGameCode(code);
                setPlayerColor(game.getPlayers().size(), App.getClientPlayer());
                game.addSpeler(App.getClientPlayer());
                dbConnector.updateGame(game);

                App.setRoot("./views/lobbyView");
            } else {
                this.error_text.setText("Het spel zit al vol");
                this.error_text.setVisible(true);
            }
        } else {
            this.error_text.setVisible(true);
        }
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

    @Override
    public void update(Game game) {

    }
}
