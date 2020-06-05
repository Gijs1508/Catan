package org.catan.Controller;

//import model.Dobbelsteen;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DobbelsteenController {

    LogController logController = LogController.getInstance();
    @FXML private ImageView dice1_img;
    @FXML private ImageView dice2_img;

//    private Dobbelsteen dobbelsteen_1;
//    private Dobbelsteen dobbelsteen_2;

    public void randomGetal() {

    }

//    public int[] getDobbelsteenGetallen() {
//        return int[]; // Dit moet worden gewijzigd
//    }

    public void startAnimatie() {

    }

    public void stopAnimatie() {

    }

    public void showDobbelstenen() {

    }

    @FXML
    public void throwDie() {
        HashMap<Integer, ArrayList<String>> diceResult = Dice.throwDice();
        Map.Entry<Integer,ArrayList<String>> entry = diceResult.entrySet().iterator().next();
        Integer total = entry.getKey();
        ArrayList<String> values = entry.getValue();
        dice1_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(0) + ".png"))));
        dice2_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/die/die" + values.get(1) + ".png"))));
        logController.logRollEvent(values.get(0), values.get(1));

//        ArrayList<String> receivedCards = new ArrayList<>();  // Move to method to controller that handles this
//        receivedCards.add("wheat");
//        receivedCards.add("ore");
//        receivedCards.add("wood");
//        logController.logReceiveEvent(receivedCards);
    }
}
