package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.catan.Model.Player;

public class TradeController {

    @FXML
    private Label giveWheatCount;
    @FXML
    private Label takeWheatCount;
    @FXML
    private Label giveWoodCount;
    @FXML
    private Label takeWoodCount;
    @FXML
    private Label giveOreCount;
    @FXML
    private Label takeOreCount;
    @FXML
    private Label giveSheepCount;
    @FXML
    private Label takeSheepCount;
    @FXML
    private Label giveBrickCount;
    @FXML
    private Label takeBrickCount;


    @FXML
    public void bankTrade() {
    }

    @FXML
    public void playerTrade() {
    }

    @FXML
    public void buyDevelopmentCard() {
    }

    @FXML
    public void sendTrade() {
    }


    @FXML
    public void resetTrade() {
        takeWoodCount.setText("0");
        giveWoodCount.setText("0");
        takeBrickCount.setText("0");
        giveBrickCount.setText("0");
        takeOreCount.setText("0");
        giveOreCount.setText("0");
        takeSheepCount.setText("0");
        giveSheepCount.setText("0");
        takeWheatCount.setText("0");
        giveWheatCount.setText("0");
    }

    @FXML
    public void giveMoreWood() {
        int wood = getInventoryCards()[0];
        if(resourceToInt(giveWoodCount) < wood){
            giveWoodCount.setText(raiseResource(giveWoodCount));
        }
    }

    @FXML
    public void takeMoreWood() {
        takeWoodCount.setText(raiseResource(takeWoodCount));
    }

    @FXML
    public void giveMoreBrick() {
        int brick = getInventoryCards()[1];
        if(resourceToInt(giveBrickCount) < brick){
            giveBrickCount.setText(raiseResource(giveBrickCount));
        }
    }

    @FXML
    public void takeMoreBrick() {
        takeBrickCount.setText(raiseResource(takeBrickCount));
    }

    @FXML
    public void giveMoreSheep() {
        int sheep = getInventoryCards()[3];
        if(resourceToInt(giveSheepCount) < sheep){
            giveSheepCount.setText(raiseResource(giveSheepCount));
        }
    }

    @FXML
    public void takeMoreSheep() {
        takeSheepCount.setText(raiseResource(takeSheepCount));
    }

    @FXML
    public void giveMoreOre() {
        int ore = getInventoryCards()[2];
        if(resourceToInt(giveOreCount) < ore){
            giveOreCount.setText(raiseResource(giveOreCount));
        }
    }

    @FXML
    public void takeMoreOre() {
        takeOreCount.setText(raiseResource(takeOreCount));
    }

    @FXML
    public void giveMoreWheat() {
        int wheat = getInventoryCards()[4];
        if(resourceToInt(giveWheatCount) < wheat){
            giveWheatCount.setText(raiseResource(giveWheatCount));
        }
    }

    @FXML
    public void takeMoreWheat() {
        takeWheatCount.setText(raiseResource(takeWheatCount));
    }

    private String raiseResource(Label resource){
        return Integer.toString(resourceToInt(resource) + 1);
    }

    private String lowerResource(Label resource){
        return Integer.toString(resourceToInt(resource) + 1);
    }

    private int resourceToInt(Label resource){
        return Integer.parseInt(resource.getText());
    }

    private int[] getInventoryCards(){
        return Player.getMainPlayer().getPlayerInventory().getCards();
    }

}
