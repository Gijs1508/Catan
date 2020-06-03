package org.catan.Controller;

//import model.Dobbelsteen;

import javafx.fxml.FXML;
import java.util.ArrayList;

public class DobbelsteenController {

    LogController logController = LogController.getInstance();

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
        logController.logRollEvent("4", "3"); // Replace parameters with the results of the throw

//        ArrayList<String> receivedCards = new ArrayList<>();  // Move to method to controller that handles this
//        receivedCards.add("wheat");
//        receivedCards.add("ore");
//        receivedCards.add("wood");
//        logController.logReceiveEvent(receivedCards);
    }
}
