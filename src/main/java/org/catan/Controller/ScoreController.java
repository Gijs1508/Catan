package org.catan.Controller;

//import model.Speler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Model.Bank;
import org.catan.Model.Player;

import java.net.URL;
import java.util.*;

import org.catan.Model.Game;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import javax.print.Doc;

/**
 * Updates the scores for the players.
 *
 * @author Jeroen
 */

public class ScoreController implements Initializable, Observable {

    @FXML private Pane player1pane;
    @FXML private Label player1name; @FXML private Label player1points;
    @FXML private Label player1roads; @FXML private Label player1villages;
    @FXML private Label player1cities;

    @FXML private Pane player2pane;
    @FXML private Label player2name; @FXML private Label player2points;
    @FXML private Label player2roads; @FXML private Label player2villages;
    @FXML private Label player2cities;

    @FXML private Pane player3pane;
    @FXML private Label player3name; @FXML private Label player3points;
    @FXML private Label player3roads; @FXML private Label player3villages;
    @FXML private Label player3cities;

    @FXML private Pane player4pane;
    @FXML private Label player4name; @FXML private Label player4points;
    @FXML private Label player4roads; @FXML private Label player4villages;
    @FXML private Label player4cities;

    @FXML private Label bankDevelopmentCards;

    private static ScoreController scoreController;

    private HashMap<String, Label> player1labels;
    private HashMap<String, Label> player2labels;
    private HashMap<String, Label> player3labels;
    private HashMap<String, Label> player4labels;
    private ArrayList<Pane> playerPanes = new ArrayList<>();

    private HashMap<String, HashMap<String, Label>> colorToLabels;

    public ScoreController() {
        scoreController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collections.addAll(playerPanes, player1pane, player2pane, player3pane, player4pane);
        for(Pane playerPane : playerPanes) {
            playerPane.setVisible(false);
        }

        initializeColorToScore();
        scoreController = this;

        switch(App.getCurrentGame().getPlayers().size()){
            case 1:
                setPlayer1name(App.getCurrentGame().getPlayers().get(0).getName());
                break;
            case 2:
                setPlayer1name(App.getCurrentGame().getPlayers().get(0).getName());
                setPlayer2name(App.getCurrentGame().getPlayers().get(1).getName());
                break;
            case 3:
                setPlayer1name(App.getCurrentGame().getPlayers().get(0).getName());
                setPlayer2name(App.getCurrentGame().getPlayers().get(1).getName());
                setPlayer3name(App.getCurrentGame().getPlayers().get(2).getName());
                break;
            case 4:
                setPlayer1name(App.getCurrentGame().getPlayers().get(0).getName());
                setPlayer2name(App.getCurrentGame().getPlayers().get(1).getName());
                setPlayer3name(App.getCurrentGame().getPlayers().get(2).getName());
                setPlayer4name(App.getCurrentGame().getPlayers().get(3).getName());
                break;
        }
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            playerPanes.get(i).setVisible(true); // Only show playerPane if that player is in the game
        }
    }

    // Assigns labels to player colors
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

    // Searches for the color's labels in the score view and updates the victory points label.
    private void setVictoryPointsForPlayer(String color, int score) {
        colorToLabels.get(color).get("points").setText(Integer.toString(score));
    }

    // Searches for the color's labels in the score view and updates the player's roads label.
    private void setRoadPointsForPlayer(String color, int road) {
        colorToLabels.get(color).get("roads").setText(Integer.toString(road));
    }

    // Searches for the color's labels in the score view and updates the player's villages label.
    private void setVillagePointsForPlayer(String color, int village) {
        colorToLabels.get(color).get("villages").setText(Integer.toString(village));
    }
    // Searches for the color's labels in the score view and updates the player's cities label.
    private void setCityPointsForPlayer(String color, int city) {
        colorToLabels.get(color).get("cities").setText(Integer.toString(city));
    }

    // Gets the amount of resource cards the bank has left and updates the bank in the scoreboard.
    private void setDevelopmentCardsLeftForBank(int cardsLeft) {
        bankDevelopmentCards.setText(Integer.toString(cardsLeft));
    }

    public static ScoreController getInstance() {
        return scoreController;
    }

    public void setPlayer1name(String player1name) {
        this.player1name.setText(player1name);
    }

    public void setPlayer2name(String player2name) {
        this.player2name.setText(player2name);
    }

    public void setPlayer3name(String player3name) {
        this.player3name.setText(player3name);
    }

    public void setPlayer4name(String player4name) {
        this.player4name.setText(player4name);
    }

    @Override
    public void update(Game game) {
        for (Player player : game.getPlayers()) {
            setCityPointsForPlayer(player.getColor(), player.getCityScore());
            setRoadPointsForPlayer(player.getColor(), player.getRoadScore());
            setVillagePointsForPlayer(player.getColor(), player.getVillageScore());
            setVictoryPointsForPlayer(player.getColor(), player.getScore());
        }
        setDevelopmentCardsLeftForBank(game.getBank().getBankInventory().developmentCardsLeftGetter());
    }
}