package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;
import java.io.IOException;

/**
 * Handles the pop up that appears when the game has ended.
 * Shows whether you won or lost the game and allows the player to return to the main menu.
 * 
 * @author Jeroen, Jan
 */

public class GameEndController implements Observable {
    @FXML private Pane background;
    @FXML private Pane victoryPane;
    @FXML private Pane defeatPane;
    @FXML private Label gameEndTitle;
    @FXML private Label defeatText;
    private static GameEndController gameEndController;

    public GameEndController() {
        gameEndController = this;
    }

    private void initializeVictory() {
        victoryPane.setVisible(true);
        defeatPane.setVisible(false);
        Sound.playVictoryJingle();
        gameEndTitle.setText("Victory!");
        background.setStyle("-fx-background-color: #2ecc71;"); // green bg
    }

    private void initializeDefeat() {
        defeatPane.setVisible(true);
        victoryPane.setVisible(false);
        Sound.playDefeatJingle();
        gameEndTitle.setText("Defeat!");
        background.setStyle("-fx-background-color: #e74c3c;"); // red bg
        defeatText.setText(defeatText.getText().replaceAll("%PLAYERWON%", winPlayerGetter()));
    }

    private String winPlayerGetter() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getScore() >= 10) {
                return player.getName();
            }
        }
        return "Not You ";
    }

    private boolean didSomeoneWin() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getScore() >= 10)
                return true;
        }
        return false;
    }

    /** Player clicked the button to leave the game and go back to the main menu. */
    @FXML public void leaveBtnClicked() throws IOException {
        Sound.playClick();
        ScreenController.getInstance().hideGameEnd();
        App.setStageHeight(715);
        App.setRoot("Views/mainView");
        App.getCurrentGame().removePlayer(App.getClientPlayer());
    }

    public static GameEndController getInstance() {
        return gameEndController;
    }

    @Override
    public void update(Game game) {
        if (didSomeoneWin()) {
            ScreenController.getInstance().showGameEnd();
            if (App.getClientPlayer().getScore() >= 10)
                initializeVictory();
            else
                initializeDefeat();
        }
    }
}
