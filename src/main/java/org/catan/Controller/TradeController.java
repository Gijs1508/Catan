package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.catan.App;
import org.catan.Model.*;
import org.catan.Model.Timer;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Manages the trade view
 *
 * @author Kaz
 */

public class TradeController implements Initializable, Observable {

    private String tradeType = "player";
    private boolean tradeGiveLock, tradeTakeLock = false;
    private boolean tradeSent = false;
    private int tradeRejections = 0;


    //ArrayList of trade offers
    ArrayList<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();

    @FXML private Label giveWheatCount;
    @FXML private Label takeWheatCount;
    @FXML private Label giveWoodCount;
    @FXML private Label takeWoodCount;
    @FXML private Label giveOreCount;
    @FXML private Label takeOreCount;
    @FXML private Label giveWoolCount;
    @FXML private Label takeWoolCount;
    @FXML private Label giveBrickCount;
    @FXML private Label takeBrickCount;
    @FXML private Button playerTradeBtn;
    @FXML private Button bankTradeBtn;

    @FXML private Label wheatRatio; @FXML private Label woodRatio;
    @FXML private Label brickRatio; @FXML private Label woolRatio;
    @FXML private Label oreRatio;

    @FXML private Button buyDevelopmentCardBtn;
    @FXML private Pane handleTradeButtons;

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

    /**
     * This method switches the trade type from player to bank if the 'bank' button is clicked
     * @author Kaz
     */
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

    /**
     * This method switches the trade type from bank to player if the 'player' button is clicked
     * @author Kaz
     */
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
        Sound.playClick();
        // If player has enough resources
        if(
                getInventoryCards()[2] >= 1 && getInventoryCards()[3] >= 1 && getInventoryCards()[4] >= 1 && isClientPlayerActive()
                && !StartPhaseController.getInstance().isStartPhaseActive()
                && !App.getCurrentGame().getTradeStatus().equals("pending"))
        {
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
                getInventory().changeCards("knight", 1);
            }
            DevCardPopUpController.getInstance().playAnimation();
            LogController.getInstance().logDevelopmentCardEvent();
        }
        else if (App.getCurrentGame().getTradeStatus().equals("pending")) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't buy a development card while your trade offer is pending.");
        }
        // Start phase is active
        else if (StartPhaseController.getInstance().isStartPhaseActive()) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't roll during the start phase.");
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

    public void disableButtons() {
        buyDevelopmentCardBtn.setOpacity(0.8);
        buyDevelopmentCardBtn.setTextFill(Color.GRAY);
    }

    public void enableButtons() {
        buyDevelopmentCardBtn.setOpacity(1);
        buyDevelopmentCardBtn.setTextFill(Color.BLACK);
    }

    /**
     * This method manages bank trades and sends out trade requests for player type trades
     * @throws IOException
     * @author Kaz
     */
    @FXML
    public void sendTrade() {
        Sound.playClick();

        // Bank trade
        if (
                tradeType.equals("bank") && isClientPlayerActive()
                && !StartPhaseController.getInstance().isStartPhaseActive() && !App.getCurrentGame().getTradeStatus().equals("pending")
                && DobbelsteenController.getInstance().isDiceThrown()
        ) {
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
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        }

        // Player trade
        else if (
                tradeType.equals("player") && isClientPlayerActive()
                && App.getCurrentGame().getTradeStatus().equals("closed")
                && !StartPhaseController.getInstance().isStartPhaseActive()
                && DobbelsteenController.getInstance().isDiceThrown()
        ) {
            int[] offerArray = {resourceToInt(giveWoodCount), resourceToInt(giveBrickCount), resourceToInt(giveOreCount), resourceToInt(giveWoolCount), resourceToInt(giveWheatCount)};
            int[] requestArray = {resourceToInt(takeWoodCount), resourceToInt(takeBrickCount), resourceToInt(takeOreCount), resourceToInt(takeWoolCount), resourceToInt(takeWheatCount)};

            Timer timer = new Timer();
            TradeOffer trade = new TradeOffer();
            trade.updateOffer(App.getClientPlayer(), offerArray, requestArray);
            tradeOffers.add(trade);
            App.getCurrentGame().setTradeOffers(tradeOffers);
            App.getCurrentGame().setTradeStatus("pending");
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
            resetTrade();
        }

        // Alerts
        else if(!DobbelsteenController.getInstance().isDiceThrown()) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You must throw the dice before sending a trade.");
        }
        else if (!App.getCurrentGame().getTradeStatus().equals("pending")) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't send a trade offer while one is still pending.");
        }
        else if (StartPhaseController.getInstance().isStartPhaseActive()) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't send a trade offer during the start phase.");
        }
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't send a trade offer outside outside of your turn.");
        }
    }

    /**
     * This method makes inbound trade offers show up on screen in a popup window
     * @throws IOException
     * @author Kaz
     */
    public void receiveTrade() throws IOException{
        ScreenController.getInstance().showTradePopup();
    }

    /**
     * This method resets all trade counters to 0
     * @author Kaz
     */
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

    /**
     * This method adds wood to the upper trade counter
     * @author Kaz
     */
    @FXML
    public void giveMoreWood() {
        //Inventory index of Wood is 0
        giveResource(giveWoodCount, 0);
    }

    /**
     * This method adds wood to the lower trade counter
     * @author Kaz
     */
    @FXML
    public void takeMoreWood() {
        takeResource(takeWoodCount);
    }

    /**
     * This method adds brick to the upper trade counter
     * @author Kaz
     */
    @FXML
    public void giveMoreBrick() {
        //Inventory index of Brick is 1
        giveResource(giveBrickCount, 1);
    }

    /**
     * This method adds brick to the lower trade counter
     * @author Kaz
     */
    @FXML
    public void takeMoreBrick() {
        takeResource(takeBrickCount);
    }

    /**
     * This method adds wool to the upper trade counter
     * @author Kaz
     */
    @FXML
    public void giveMoreWool() {
        //Inventory index of Wool is 3
        giveResource(giveWoolCount, 3);
    }

    /**
     * This method adds wool to the lower trade counter
     * @author Kaz
     */
    @FXML
    public void takeMoreWool() {
        takeResource(takeWoolCount);
    }

    /**
     * This method adds ore to the upper trade counter
     * @author Kaz
     */
    @FXML
    public void giveMoreOre() {
        //Inventory index of Ore is 2
        giveResource(giveOreCount, 2);
    }

    /**
     * This method adds ore to the lower trade counter
     * @author Kaz
     */
    @FXML
    public void takeMoreOre() {
        takeResource(takeOreCount);
    }

    /**
     * This method adds wheat to the upper trade counter
     * @author Kaz
     */
    @FXML
    public void giveMoreWheat() {
        //Inventory index of Wheat is 4
        giveResource(giveWheatCount, 4);
    }

    /**
     * This method adds wheat to the lower trade counter
     * @author Kaz
     */
    @FXML
    public void takeMoreWheat() {
        takeResource(takeWheatCount);
    }

    /**
     * This method updates the bank trading resource ratio
     * @param type
     * @param ratio
     * @author Jeroen
     */
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

    //Raise given resource by one before returning as String
    private String raiseResource(Label resource){
        return Integer.toString(resourceToInt(resource) + 1);
    }

    //Convert Label to int
    private int resourceToInt(Label resource){
        return Integer.parseInt(resource.getText());
    }

    private Inventory getInventory(){
        return App.getClientPlayer().getPlayerInventory();
    }

    private int[] getInventoryCards(){
        return getInventory().getCards();
    }

    //Check if player owns required resource and add to the upper trade counter
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

    //Raise the lower trade counter of a resource
    private void takeResource(Label resourceCount){
        if(tradeType.equals("player")){
            resourceCount.setText(raiseResource(resourceCount));
        } else if(tradeTakeLock == false && (resourceToInt(giveWoodCount) > 0 || resourceToInt(giveBrickCount) > 0 || resourceToInt(giveOreCount) > 0 || resourceToInt(giveWoolCount) > 0 || resourceToInt(giveWheatCount) > 0)){
            resourceCount.setText(raiseResource(resourceCount));
            tradeTakeLock = true;
        }
    }

    //Calculate the net gain/loss of a resource after a trade
    private int netResource(Label givenResource, Label receivedResource){
        return (resourceToInt(receivedResource) - resourceToInt(givenResource));
    }

    //Check if client is active
    private boolean isClientPlayerActive(){
        return App.getClientPlayer().isTurn();
    }

    @Override
    public void update(Game game) throws IOException {

        App.getCurrentGame().setTradeStatus(game.getTradeStatus());

        ArrayList<TradeOffer> currentOffers = App.getCurrentGame().getTradeOffers();
        ArrayList<TradeOffer> updatedOffers = game.getTradeOffers();
        if(!(App.getClientPlayer().getIdentifier() == App.getCurrentGame().turnPlayerGetter().getIdentifier()) && currentOffers.size() < updatedOffers.size()){
            tradeOffers = updatedOffers;
            App.getCurrentGame().setTradeOffers(tradeOffers);

            TradePopUpController.updateTradeOffer(updatedOffers.get(tradeOffers.size() - 1));
            receiveTrade();
        }

        if(game.getTradeStatus().equals("pending")){
            TradeOffer currentOffer = App.getCurrentGame().getTradeOffers().get(game.getTradeOffers().size() - 1);
            TradeOffer updatedOffer = updatedOffers.get(game.getTradeOffers().size() - 1);
            int currentRejections = currentOffer.getRejections();
            int updatedRejections = updatedOffer.getRejections();

            if(currentRejections < updatedRejections){
                currentOffer.addRejection();
                if((currentOffer.getRejections() >= App.getCurrentGame().getPlayers().size() - 1) && App.getClientPlayer().isTurn()){
                    ScreenController.getInstance().showAlertPopup();
                    AlertPopUpController.getInstance().setAlertDescription("All players declined your trade offer");
                    App.getCurrentGame().setTradeStatus("closed");
                    DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
                }
            }
        } else if(game.getTradeStatus().equals("accepted") && App.getClientPlayer().isTurn()){
            tradeAccepted();
        } else if(game.getTradeStatus().equals("timeout") && App.getClientPlayer().isTurn()){
            App.getCurrentGame().setTradeStatus("closed");
            AlertPopUpController.getInstance().setAlertDescription("Nobody responded to the trade offer in time");
            ScreenController.getInstance().showAlertPopup();
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        }
    }

    /**
     * This method updates the player resources after their trade has been accepted by another player
     */
    public void tradeAccepted(){
        TradeOffer tradeOffer = tradeOffers.get(tradeOffers.size() - 1);
        int[] offer = tradeOffer.getOfferedCards();
        int[] request = tradeOffer.getRequestedCards();
        Inventory playerInventory = getInventory();

        playerInventory.changeCards("wood", -offer[0]);
        playerInventory.changeCards("brick", -offer[1]);
        playerInventory.changeCards("ore", -offer[2]);
        playerInventory.changeCards("wool", -offer[3]);
        playerInventory.changeCards("wheat", -offer[4]);

        playerInventory.changeCards("wood", request[0]);
        playerInventory.changeCards("brick", request[1]);
        playerInventory.changeCards("ore", request[2]);
        playerInventory.changeCards("wool", request[3]);
        playerInventory.changeCards("wheat", request[4]);

        App.getCurrentGame().setTradeStatus("closed");
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());

        ScreenController.getInstance().showAlertPopup();
        AlertPopUpController.getInstance().setAlertDescription("Your trade offer has been accepted!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tradeController = this;
    }
}
