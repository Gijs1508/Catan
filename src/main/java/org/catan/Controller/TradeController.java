package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.catan.Model.Game;
import org.catan.Model.Inventory;
import org.catan.Model.Player;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class TradeController implements Observable {

    private String tradeType = "player";
    private boolean tradeGiveLock, tradeTakeLock = false;

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
    private Label giveWoolCount;
    @FXML
    private Label takeWoolCount;
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
        Sound.playSwitch();

        if (tradeType == "player" && Player.mainPlayerActive){
            tradeType = "bank";
            bankTradeBtn.setFont(new Font("System Bold", 14));
            playerTradeBtn.setFont(new Font("System", 14));
            resetTrade();
        }
    }

    @FXML
    public void playerTrade() {
        Sound.playSwitch2();

        if (tradeType == "bank" && Player.mainPlayerActive){
            tradeType = "player";
            bankTradeBtn.setFont(new Font("System", 14));
            playerTradeBtn.setFont(new Font("System Bold", 14));
            resetTrade();
        }
    }

    @FXML
    public void buyDevelopmentCard() {
        if(getInventoryCards()[2] >= 1 && getInventoryCards()[3] >= 1 && getInventoryCards()[4] >= 1 && Player.mainPlayerActive){
            getInventory().changeCards("ore", -1);
            getInventory().changeCards("wool", -1);
            getInventory().changeCards("wheat", -1);
            // TODO add development card functionality
        }
    }

    @FXML
    public void sendTrade() throws IOException {
        if(tradeType == "bank" && Player.mainPlayerActive){
            int netWood = netResource(giveWoodCount, takeWoodCount);
            getInventory().changeCards("wood", netWood);
            int netBrick = netResource(giveBrickCount, takeBrickCount);
            getInventory().changeCards("brick", netBrick);
            int netOre = netResource(giveOreCount, takeOreCount);
            getInventory().changeCards("ore", netOre);
            int netWool = netResource(giveWoolCount, takeWoolCount);
            getInventory().changeCards("wool", netWool);
            int netWheat = netResource(giveWheatCount, takeWheatCount);
            getInventory().changeCards("wheat", netWheat);
            resetTrade();
        } else if(tradeType == "player" && Player.mainPlayerActive){
            String playerName = Player.getMainPlayer().getName();
            String[] offerArray = {giveWoodCount.getText(), giveBrickCount.getText(), giveOreCount.getText(), giveWoolCount.getText(), giveWheatCount.getText()};
            String[] requestArray = {takeWoodCount.getText(), takeBrickCount.getText(), takeOreCount.getText(), takeWoolCount.getText(), takeWheatCount.getText()};
            TradePopUpController.updateTradeOffer(playerName, offerArray, requestArray);
//            App.tradePopUp();
            ScreenController.getInstance().showTradePopup(); //TODO Moet alleen verschijnen bij de andere spelers, dus NIET bij MainPlayer
        }
    }


    @FXML
    public void resetTrade() {
        takeWoodCount.setText("0");
        giveWoodCount.setText("0");
        takeBrickCount.setText("0");
        giveBrickCount.setText("0");
        takeOreCount.setText("0");
        giveOreCount.setText("0");
        takeWoolCount.setText("0");
        giveWoolCount.setText("0");
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
    public void giveMoreWool() {
        //Inventory index of Wool is 3
        giveResource(giveWoolCount, 3);
    }

    @FXML
    public void takeMoreWool() {
        takeResource(takeWoolCount);
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

    private Inventory getInventory(){
        return Player.getMainPlayer().getPlayerInventory();
    }

    private int[] getInventoryCards(){
        return getInventory().getCards();
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

    private int netResource(Label givenResource, Label receivedResource){
        return (resourceToInt(receivedResource) - resourceToInt(givenResource));
    }

    @Override
    public void update(Game game) {

    }
}
