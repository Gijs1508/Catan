package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Model.Sound;

import java.net.URL;
import java.util.ResourceBundle;

public class GameEndController {
    @FXML private Pane background;
    @FXML private Pane victoryPane;
    @FXML private Pane defeatPane;
    @FXML private Label gameEndTitle;
    @FXML private Label defeatText;
    private static GameEndController gameEndController;

    public GameEndController() { gameEndController = this; }

    public void initializeVictory() {
        victoryPane.setVisible(true);
        defeatPane.setVisible(false);
        Sound.playVictoryJingle();
        gameEndTitle.setText("Victory!");
        background.setStyle("-fx-background-color: #2ecc71;"); // green bg
    }

    public void initializeDefeat() {
        defeatPane.setVisible(true);
        victoryPane.setVisible(false);
        Sound.playDefeatJingle();
        gameEndTitle.setText("Defeat!");
        background.setStyle("-fx-background-color: #e74c3c;"); // red bg
        defeatText.setText(defeatText.getText().replaceAll("%PLAYERWON%", "testPlayer")); // TODO Find name of player who won
    }

    @FXML
    public void leaveBtnClicked() {
        Sound.playClick();
        ScreenController.getInstance().hideGameEnd();
        // TODO leave game and go back to main menu
//        App.getCurrentGame().removePlayer(App.getClientPlayer());
    }

    public static GameEndController getInstance() {
        return gameEndController;
    }
}
