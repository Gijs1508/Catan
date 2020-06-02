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
        //Inventory index of Wood is 0
        giveResource(giveWoodCount, 0);
    }

    @FXML
    public void takeMoreWood() {
        takeResource(takeWoodCount);
    }

    @FXML
    public void giveMoreBrick() {
        //Inventory index of Brick is 1
        giveResource(giveBrickCount, 1);
    }

    @FXML
    public void takeMoreBrick() {
        takeResource(takeBrickCount);
    }

    @FXML
    public void giveMoreSheep() {
        //Inventory index of Sheep is 4
        giveResource(giveSheepCount, 4);
    }

    @FXML
    public void takeMoreSheep() {
        takeResource(takeSheepCount);
    }

    @FXML
    public void giveMoreOre() {
        //Inventory index of Ore is 2
        giveResource(giveOreCount, 2);
    }

    @FXML
    public void takeMoreOre() {
        takeResource(takeOreCount);
    }

    @FXML
    public void giveMoreWheat() {
        //Inventory index of Wheat is 4
        giveResource(giveWheatCount, 4);
    }

    @FXML
    public void takeMoreWheat() {
        takeResource(takeWheatCount);
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

    private void giveResource(Label resource, int inventoryIndex){
        int inventoryCard = getInventoryCards()[inventoryIndex];
        if(tradeType == "player"){
            if(resourceToInt(resource) < inventoryCard){
                resource.setText(raiseResource(resource));
            }
        } else if(inventoryCard >= 4 && tradeGiveLock == false){
            for(int i = 0; i < 4; i++){
                resource.setText(raiseResource(resource));
                tradeGiveLock = true;
            }
        }
    }

    private void takeResource(Label resourceCount){
        if(tradeType == "player"){
            resourceCount.setText(raiseResource(resourceCount));
        } else if(tradeTakeLock == false){
            resourceCount.setText(raiseResource(resourceCount));
            tradeTakeLock = true;
        }
    }

}
