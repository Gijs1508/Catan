package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.catan.Model.Player;

public class TradeController {

    private String tradeType = "player";
    private boolean tradeGiveLock = false;
    private boolean tradeTakeLock = false;

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
    private Button playerTradeBtn;
    @FXML
    private Button bankTradeBtn;


    @FXML
    public void bankTrade() {
        if (tradeType == "player"){
            tradeType = "bank";
            bankTradeBtn.setFont(new Font("System Bold", 14));
            playerTradeBtn.setFont(new Font("System", 14));
            resetTrade();
        }
    }

    @FXML
    public void playerTrade() {
        if (tradeType == "bank"){
            tradeType = "player";
            bankTradeBtn.setFont(new Font("System", 14));
            playerTradeBtn.setFont(new Font("System Bold", 14));
            resetTrade();
        }
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
        tradeGiveLock = false;
        tradeTakeLock = false;
    }

    @FXML
    public void giveMoreWood() {
        int wood = getInventoryCards()[0];
        if(tradeType == "player"){
            if(resourceToInt(giveWoodCount) < wood){
                giveWoodCount.setText(raiseResource(giveWoodCount));
            }
        } else if(wood >= 4 && tradeGiveLock == false){
            for(int i = 0; i < 4; i++){
                giveWoodCount.setText(raiseResource(giveWoodCount));
                tradeGiveLock = true;
            }
        }
    }

    @FXML
    public void takeMoreWood() {
        if(tradeType == "player"){
            takeWoodCount.setText(raiseResource(takeWoodCount));
        } else if(tradeTakeLock == false){
            takeWoodCount.setText(raiseResource(takeWoodCount));
            tradeTakeLock = true;
        }
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
