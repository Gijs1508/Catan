package org.catan.Controller;

//import model.Speler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.catan.Model.Player;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

public class ScoreController implements Initializable, Observable {

    @FXML private Label player1name; @FXML private Label player1points;
    @FXML private Label player1roads; @FXML private Label player1villages;
    @FXML private Label player1cities;

    @FXML private Label player2name; @FXML private Label player2points;
    @FXML private Label player2roads; @FXML private Label player2villages;
    @FXML private Label player2cities;

    @FXML private Label player3name; @FXML private Label player3points;
    @FXML private Label player3roads; @FXML private Label player3villages;
    @FXML private Label player3cities;

    @FXML private Label player4name; @FXML private Label player4points;
    @FXML private Label player4roads; @FXML private Label player4villages;
    @FXML private Label player4cities;

    @FXML private Label bankDevelopmentCards;

    private static ScoreController scoreController;




//    private Speler speler;
    private int score;
    private HashMap<String, Label> player1labels;
    private HashMap<String, Label> player2labels;
    private HashMap<String, Label> player3labels;
    private HashMap<String, Label> player4labels;

    private HashMap<String, HashMap<String, Label>> colorToLabels;

    public ScoreController() {
        scoreController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeColorToScore();
    }

    /** Assigns labels to player colors
     * @author Jeroen */
    private void initializeColorToScore() {
        player1labels = new HashMap<>(){{
            put("name", player1name); put("points", player1points);
            put("roads", player1roads); put("villages", player1villages);
            put("cities", player1cities);
        }};
        player2labels = new HashMap<>(){{
            put("name", player2name); put("points", player2points);
            put("roads", player2roads); put("villages", player2villages);
            put("cities", player2cities);
        }};
        player3labels = new HashMap<>(){{
            put("name", player3name); put("points", player3points);
            put("roads", player3roads); put("villages", player3villages);
            put("cities", player3cities);
        }};
        player4labels = new HashMap<>(){{
            put("name", player4name); put("points", player4points);
            put("roads", player4roads); put("villages", player4villages);
            put("cities", player4cities);
        }};

        colorToLabels = new HashMap<>(){{
            put("red", player1labels);
            put("blue", player2labels);
            put("green", player3labels);
            put("yellow", player4labels);
        }};
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {

    }

    /** Searches for the color's labels in the score view and updates the victory points label.
     * @param color the player's color
     * @param score the player's score in victory points
     * @author Jeroen */
    public void addVictoryPointToPlayer(String color, int score) {
        colorToLabels.get(color).get("points").setText(Integer.toString(score));
    }

    public void removeDevelopmentCardFromBankView() {
        bankDevelopmentCards.setText(String.valueOf(Integer.parseInt(bankDevelopmentCards.getText()) - 1));
    }

    public static ScoreController getInstance() {
        return scoreController;
    }

    @Override
    public void update(Game game) {

    }
}
