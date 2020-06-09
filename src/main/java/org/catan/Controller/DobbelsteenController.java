package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Dice;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DobbelsteenController implements Observable {

    LogController logController = LogController.getInstance();
    @FXML private ImageView dice1_img;
    @FXML private ImageView dice2_img;
    Dice dice = new Dice();

    /*
    This method gets called when the player presses the throw dice button.
    it uses the Dice class to get random numbers and then sets the images
    of the dices to the numbers rolled.
     */
    @FXML
    public void throwDie() throws IOException {
        HashMap<Integer, ArrayList<String>> diceResult = dice.throwDice();
        Map.Entry<Integer,ArrayList<String>> entry = diceResult.entrySet().iterator().next();
        Integer total = entry.getKey();
        ArrayList<String> values = entry.getValue();
        dice1_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(0) + ".png"))));
        dice2_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(1) + ".png"))));
        logController.logRollEvent(values.get(0), values.get(1));
    }

    @Override
    public void update(Game game) {

    }
}
