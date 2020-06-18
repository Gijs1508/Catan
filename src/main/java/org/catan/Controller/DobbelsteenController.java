package org.catan.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.catan.App;
import org.catan.Model.Dice;
import org.catan.Model.Player;
import org.catan.Model.Game;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the die a player must throw to proceed with a turn.
 */

public class DobbelsteenController implements Observable {

    LogController logController = LogController.getInstance();
    @FXML private ImageView dice1_img;
    @FXML private ImageView dice2_img;
    Dice dice = new Dice();

    /*
    This method gets called when the player presses the throw dice button.
    it uses the Dice class to get random numbers and then sets the images
    of the dices to the numbers rolled.
    TODO disable throwing multiple times a turn
     */
    @FXML public void throwDie() {
        System.out.println("is players turn: " + App.getClientPlayer().isTurn());
        if(App.getClientPlayer().isTurn()){
            Sound.playDiceShuffle();
            // Throw the dice 1.5 seconds after starting the shuffle sound effect
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5), actionEvent -> {
                HashMap<Integer, ArrayList<String>> diceResult;
                try {
                    diceResult = dice.throwDice();

                    Map.Entry<Integer,ArrayList<String>> entry = diceResult.entrySet().iterator().next();
                    Integer total = entry.getKey();
                    ArrayList<String> values = entry.getValue();
                    dice1_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(0) + ".png"))));
                    dice2_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(1) + ".png"))));
                    Sound.playDiceThrow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            delay.play();
        }
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't roll when it's not your turn.");
        }
    }

    @Override
    public void update(Game game) {

    }
}
