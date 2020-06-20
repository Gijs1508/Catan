package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Manages the popup that shows when a player receives a trade offer.
 * Player can decline or accept this offer. Popup can be dragged around the screen.
 * @author Kaz, Jeroen
 */

public class TradePopUpController implements Initializable, Observable {
    @FXML private AnchorPane popupPane;
    @FXML private ImageView declineBtn;
    @FXML private Text popupTitle;
    @FXML private Text playerName;
    @FXML private Text woodOffer;
    @FXML private Text brickOffer;
    @FXML private Text oreOffer;
    @FXML private Text woolOffer;
    @FXML private Text wheatOffer;
    @FXML private Text woodRequest;
    @FXML private Text brickRequest;
    @FXML private Text oreRequest;
    @FXML private Text woolRequest;
    @FXML private Text wheatRequest;

    private ScreenController screenController = ScreenController.getInstance();

    private double x;
    private double y;

    private HashMap<String, Double> paneInfo = screenController.getTradePopupLayout();
    private double paneLayoutXinRoot = paneInfo.get("layoutX");
    private double paneLayoutYinRoot = paneInfo.get("layoutY");
    private double paneWidth;
    private double paneHeight;

    public static Player sender;
    public static String senderName = "%PLAYER%";
    public static int[] offer = {0, 0, 0, 0, 0};
    public static int[] request = {0, 0, 0, 0, 0};
    public static boolean offerLock = false;  // Lock to make sure offer cannot be accepted multiple times

    private static TradePopUpController tradePopUpController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sound.playPop();

        paneWidth = popupPane.getPrefWidth();
        paneHeight = popupPane.getPrefHeight();

        // Shows the offer
        popupTitle.setText(popupTitle.getText().replaceAll("%PLAYER%", senderName));
        woodOffer.setText(resourceToString(offer[0]));
        brickOffer.setText(resourceToString(offer[1]));
        oreOffer.setText(resourceToString(offer[2]));
        woolOffer.setText(resourceToString(offer[3]));
        wheatOffer.setText(resourceToString(offer[4]));
        woodRequest.setText(resourceToString(request[0]));
        brickRequest.setText(resourceToString(request[1]));
        oreRequest.setText(resourceToString(request[2]));
        woolRequest.setText(resourceToString(request[3]));
        wheatRequest.setText(resourceToString(request[4]));
        offerLock = false;

        initializeDrag();
    }

    /** Makes the popup draggable.
     * @author Jeroen */
    private void initializeDrag() {
        popupPane.setOnMousePressed(mouseEvent -> {
            x = popupPane.getLayoutX() - mouseEvent.getSceneX();
            y = popupPane.getLayoutY() - mouseEvent.getSceneY();
            popupPane.setCursor(Cursor.MOVE);
        });
        popupPane.setOnMouseReleased(mouseEvent -> {
            popupPane.setCursor(Cursor.HAND);
            if(draggedOutOfScreen()) { // If the popup is dragged out of the screen, reset the position
                popupPane.setLayoutY(0);
                popupPane.setLayoutX(0);
            }
        });
        popupPane.setOnMouseDragged(mouseEvent -> {
            popupPane.setLayoutX(mouseEvent.getSceneX() + x);
            popupPane.setLayoutY(mouseEvent.getSceneY() + y);
        });
        popupPane.setOnMouseEntered(mouseEvent -> popupPane.setCursor(Cursor.HAND));
    }

    /** Checks whether the pane has been dragged outside of the screen.
     * @return true: dragged out of screen / false: didn't drag out of screen
     * @author Jeroen */
    private boolean draggedOutOfScreen() {
        // Collisions are recognized, but I can't find a way to make use of it.
        // X Collisions
        if(popupPane.getLayoutX() + paneLayoutXinRoot + paneWidth  >  screenController.getRoot().getPrefWidth()) { // right collision
            return true;
        }
        else if(popupPane.getLayoutX() + paneLayoutXinRoot  <  0) { // left collision
            return true;
        }
        // Y Collisions
        else if(popupPane.getLayoutY() + paneLayoutYinRoot + paneHeight > screenController.getRoot().getPrefHeight()) { // bottom collision
            return true;
        }
        else if(popupPane.getLayoutY() + paneLayoutYinRoot < 0) { // top collision
            return true;
        }
        return false;
    }

    public static void updateTradeOffer(TradeOffer tradeOffer){
        sender = tradeOffer.getSender();
        senderName = sender.getName();
        offer = tradeOffer.getOfferedCards();
        request = tradeOffer.getRequestedCards();
    }

    @FXML
    public void acceptTrade() {
        Sound.playClick();

        Inventory playerInventory = App.getClientPlayer().getPlayerInventory();
        int[] cards = playerInventory.getCards();
        if(!offerLock && ownCards()){
            playerInventory.changeCards("wood", offer[0]);
            playerInventory.changeCards("brick", offer[1]);
            playerInventory.changeCards("ore", offer[2]);
            playerInventory.changeCards("wool", offer[3]);
            playerInventory.changeCards("wheat", offer[4]);

            playerInventory.changeCards("wood", -request[0]);
            playerInventory.changeCards("brick", -request[1]);
            playerInventory.changeCards("ore", -request[2]);
            playerInventory.changeCards("wool", -request[3]);
            playerInventory.changeCards("wheat", -request[4]);
            offerLock = true;

            App.getCurrentGame().setTradeStatus("accepted");
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());

            screenController.hideTradePopup();
        }
    }

    public void declineTrade(MouseEvent mouseEvent) {
        Sound.playClick();
        App.getCurrentGame().getTradeOffers().get(App.getCurrentGame().getTradeOffers().size() - 1).addRejection();
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());

        screenController.hideTradePopup();
    }

    // Check if player owns all the requested cards
    public boolean ownCards(){
        boolean ownCards = true;
        int[] cards = App.getClientPlayer().getPlayerInventory().getCards();
        for (int i = 0; i < request.length; i++){
            if (!(cards[i] >= request[i])){
                return false;
            }
        }
        return ownCards;
    }

    @Override
    public void update(Game game) {
        if(game.getTradeStatus().equals("accepted")){
            screenController.hideTradePopup();
        }
    }

    public static TradePopUpController getInstance(){
        if(tradePopUpController == null){
            tradePopUpController = new TradePopUpController();
        }
        return tradePopUpController;
    }

    private String resourceToString(int resource){
        return Integer.toString(resource);
    }
}
