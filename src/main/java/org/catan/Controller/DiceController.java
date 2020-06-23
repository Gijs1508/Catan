package org.catan.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.catan.App;
import org.catan.Model.Dice;
import org.catan.Model.Game;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the die a player must throw to proceed with a turn.
 * @author Gijs van der Weijden
 */
public class DiceController implements Observable {
    @FXML private ImageView dice1_img;
    @FXML private ImageView dice2_img;
    @FXML private Button throwButton;
    private Dice dice = new Dice();
    private boolean diceThrown;

    private static DiceController diceController;

    public DiceController() {
        diceController = this;
    }

    public static DiceController getInstance(){
        if(diceController == null){
            diceController = new DiceController();
        }
        return diceController;
    }

    /**
     * This method gets called when the player presses the throw dice button.
     * it uses the Dice class to get random numbers and then sets the images
     * of the dices to the numbers rolled.
     * @author Gijs
     */
    @FXML public void throwDie() {
        if(App.getClientPlayer().isTurn() && !StartPhaseController.getInstance().isStartPhaseActive() && !diceThrown){
            Sound.playDiceShuffle();
            // Throw the dice 1.5 seconds after starting the shuffle sound effect
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.5), actionEvent -> {
                HashMap<Integer, ArrayList<String>> diceResult;
                try {
                    diceResult = dice.throwDice();
                    for (int key : diceResult.keySet()) {
                        if (key == 7) {
                            App.getCurrentGame().setSevenThrown(true);
                            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
                            App.getCurrentGame().setSevenThrown(false); // Another update necessary to ensure sevenThrown won't stay true
                            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
                            GameSchermController.getInstance().highlightTiles(App.getCurrentGame().getBoard().getThief().getTile());
                        }
                    }
                    Map.Entry<Integer,ArrayList<String>> entry = diceResult.entrySet().iterator().next();
                    ArrayList<String> values = entry.getValue();
                    dice1_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(0) + ".png"))));
                    dice2_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(1) + ".png"))));
                    Sound.playDiceThrow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            diceThrown = true;
            delay.play();
        }
        else if (StartPhaseController.getInstance().isStartPhaseActive()) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't roll during the start phase.");
        }
        else if (diceThrown) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can only roll once during your turn.");
        }
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't roll when it's not your turn.");
        }
    }


    @Override
    public void update(Game game) throws IOException {
        if(App.getCurrentGame().isSevenThrown() != game.isSevenThrown()){
            App.getCurrentGame().setSevenThrown(game.isSevenThrown());
            if(App.getClientPlayer().getPlayerInventory().cardsTotalMinusKnightGetter() > 7 && game.isSevenThrown()){
                ScreenController.getInstance().showHandInPopUp();
            }
        }
    }

    /** Grays out the throw button when player is unable to use it */
    public void disableButton() {
        throwButton.setTextFill(Color.GRAY);
        throwButton.setOpacity(0.8);
    }

    /** Makes throw button visible again when it's player's turn */
    public void enableButton() {
        throwButton.setTextFill(Color.BLACK);
        throwButton.setOpacity(1);
    }

    public boolean isDiceThrown() {
        return diceThrown;
    }

    public void setDiceThrown(boolean diceThrown) {
        this.diceThrown = diceThrown;
    }
}
