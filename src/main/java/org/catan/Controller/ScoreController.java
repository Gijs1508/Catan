package org.catan.Controller;

//import model.Speler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreController {

    @FXML private Label bankDevelopmentCards;
    private static ScoreController scoreController;
//    private Speler speler;
    private int score;

    public ScoreController() {
        scoreController = this;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {

    }

    public void removeDevelopmentCardFromBankView() {
        bankDevelopmentCards.setText(String.valueOf(Integer.parseInt(bankDevelopmentCards.getText()) - 1));
    }

    public static ScoreController getInstance() {
        return scoreController;
    }

}
