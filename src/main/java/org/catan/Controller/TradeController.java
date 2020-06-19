package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TradeController implements Initializable, Observable {

    private String tradeType = "player";
    private boolean tradeGiveLock, tradeTakeLock = false;
    private boolean tradeSent = false;

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

    @FXML private Label wheatRatio; @FXML private Label woodRatio;
    @FXML private Label brickRatio; @FXML private Label woolRatio;
    @FXML private Label oreRatio;

    private HashMap<Integer, String> indexToResource = new HashMap<>(){{
        put(0, "wood");
        put(1, "brick");
        put(2, "ore");
        put(3, "wool");
        put(4, "wheat");
    }};

    private static TradeController tradeController;


    public TradeController(){
    }

    public static TradeController getInstance(){
        if(tradeController == null){
            tradeController = new TradeController();
        }
        return tradeController;
    }

    @FXML
    public void bankTrade() {
        Sound.playSwitch();

        if (tradeType.equals("player")) {
            tradeType = "bank";
            bankTradeBtn.setFont(new Font("System Bold", 14));
            playerTradeBtn.setFont(new Font("System", 14));
            resetTrade();
        }
    }

    @FXML
    public void playerTrade() {
        Sound.playSwitch2();

        if (tradeType.equals("bank")) {
            tradeType = "player";
            bankTradeBtn.setFont(new Font("System", 14));
            playerTradeBtn.setFont(new Font("System Bold", 14));
            resetTrade();
        }
    }

    /** The player buys a development card and will receive a random card from the bank.
     * @author Kaz, Jeroen */
    @FXML public void buyDevelopmentCard() {
        // If player has enough resources
        if(getInventoryCards()[2] >= 1 && getInventoryCards()[3] >= 1 && getInventoryCards()[4] >= 1 && isClientPlayerActive()){
            // If player has enough resources
            String developmentCard = Bank.getBank().takeDevelopmentCard();

            // If bank is empty
            if(developmentCard.equals("bankEmpty")) {
                ScreenController.getInstance().showAlertPopup();
                AlertPopUpController.getInstance().setAlertDescription("The bank doesn't have any development cards left to give.");
                return;
            }

            // Executes if bank has development cards left, takes resources
            getInventory().changeCards("ore", -1);
            getInventory().changeCards("wool", -1);
            getInventory().changeCards("wheat", -1);

            // Bank gave a victory point card
            if(developmentCard.equals("victoryPoint")) {
                ScreenController.getInstance().showDevCardPopup();
                DevCardPopUpController.getInstance().setPointImage();

                App.getCurrentGame().turnPlayerGetter().addVictoryPoint();
            }
            // Bank gave a knight card
            else {
                ScreenController.getInstance().showDevCardPopup();
                DevCardPopUpController.getInstance().setKnightImage();
                App.getCurrentGame().turnPlayerGetter().getPlayerInventory().changeCards("knight", 1);
            }
            DevCardPopUpController.getInstance().playAnimation();
            LogController.getInstance().logDevelopmentCardEvent();
        }
        // Player doesn't have enough resources
        else if (isClientPlayerActive()){
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You don't have enough resources to buy a development card.");
            return;
        }
        // It's not player's turn
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't buy a development card outside of your turn.");
        }
    }

    @FXML
    public void sendTrade() throws IOException {
        if(tradeType.equals("bank") && isClientPlayerActive()){
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
        } else if(tradeType.equals("player") && isClientPlayerActive()){
            String[] offerArray = {giveWoodCount.getText(), giveBrickCount.getText(), giveOreCount.getText(), giveWoolCount.getText(), giveWheatCount.getText()};
            String[] requestArray = {takeWoodCount.getText(), takeBrickCount.getText(), takeOreCount.getText(), takeWoolCount.getText(), takeWheatCount.getText()};

            App.getCurrentGame().fetchTradeOffer().updateOffer(App.getClientPlayer(), offerArray, requestArray);
            App.getCurrentGame().setTradeSent(true);
        }
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't send trade offers outside of your turn.");
        }
    }

    public void receiveTrade() throws IOException{
        ScreenController.getInstance().showTradePopup();
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

    public void updateRatioView(String type, int ratio) {
        switch (type) {
            case "wheat":
                wheatRatio.setText(ratio + ":1"); break;
            case "wood":
                woodRatio.setText(ratio + ":1"); break;
            case "brick":
                brickRatio.setText(ratio + ":1"); break;
            case "wool":
                woolRatio.setText(ratio + ":1"); break;
            case "ore":
                oreRatio.setText(ratio + ":1"); break;
            case "any":
                wheatRatio.setText(ratio + ":1");
                woodRatio.setText(ratio + ":1");
                brickRatio.setText(ratio + ":1");
                woolRatio.setText(ratio + ":1");
                oreRatio.setText(ratio + ":1");
                break;
        }
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
        return App.getClientPlayer().getPlayerInventory();
    }

    private int[] getInventoryCards(){
        return getInventory().getCards();
    }


    private void giveResource(Label resource, int inventoryIndex){
        int inventoryCard = getInventoryCards()[inventoryIndex];
        if(tradeType.equals("player")){
            if(resourceToInt(resource) < inventoryCard){
                resource.setText(raiseResource(resource));
            }
        } else { // tradeType is bank
            String resourceType = indexToResource.get(inventoryIndex);
            int cost = App.getClientPlayer().getResourceToCost().get(resourceType); // Gets the player's cost for the resource type

            if(inventoryCard >= cost && tradeGiveLock == false){
                for(int i = 0; i < cost; i++){
                    resource.setText(raiseResource(resource));
                    tradeGiveLock = true;
                }
            }
        }
    }

    private void takeResource(Label resourceCount){
        if(tradeType.equals("player")){
            resourceCount.setText(raiseResource(resourceCount));
        } else if(tradeTakeLock == false){
            resourceCount.setText(raiseResource(resourceCount));
            tradeTakeLock = true;
        }
    }

    private int netResource(Label givenResource, Label receivedResource){
        return (resourceToInt(receivedResource) - resourceToInt(givenResource));
    }

    private boolean isClientPlayerActive(){
        return App.getClientPlayer().isTurn();
    }

    @Override
    public void update(Game game) throws IOException {
        if(!(App.getClientPlayer().getIdentifier() == App.getCurrentGame().turnPlayerGetter().getIdentifier()) && game.isTradeSent()){
            TradeOffer trade = new TradeOffer();
            Player sender = game.fetchTradeOffer().fetchSender();
            String[] offer = game.fetchTradeOffer().fetchOfferedCards();
            String[] request = game.fetchTradeOffer().fetchRequestedCards();
            System.out.println(sender + " " + offer + " " + request);
//            App.getCurrentGame().fetchTradeOffer().updateOffer(sender, offer, request);
            receiveTrade();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tradeController = this;
    }
}
