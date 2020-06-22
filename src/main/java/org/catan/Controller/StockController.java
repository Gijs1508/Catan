package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Manages a player's stock view based on their inventory
 * @author Kaz, Jeroen
 */

public class StockController implements Initializable, Observable {
    //Make controller available to other classes
    private static StockController stockController;

    @FXML private Text wheatCount;
    @FXML private Text woodCount;
    @FXML private Text brickCount;
    @FXML private Text woolCount;
    @FXML private Text oreCount;
    @FXML private Text knightCount;

    @FXML private Pane animationResourcesPane;
    @FXML private ImageView animationKnightCard;
    @FXML private ImageView animationWheat;
    @FXML private ImageView animationWood;
    @FXML private ImageView animationBrick;
    @FXML private ImageView animationSheep;
    @FXML private ImageView animationOre;
    private boolean animationIsActive = false;

    private LogController logController = LogController.getInstance();
    private HashMap<String, ImageView> animationCardForResource;

    public StockController(){
        stockController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animationResourcesPane.setVisible(true);
        initializeAnimationCardMap();
    }

    /**
     * This method updates the resources in the players' stock view and triggers card animations if necessary
     * @author Jeroen, Kaz
     */
    public void updateResources(){
        int[] oldResources = new int[6];
        oldResources[0] = Integer.parseInt(woodCount.getText());
        oldResources[1] = Integer.parseInt(brickCount.getText());
        oldResources[2] = Integer.parseInt(oreCount.getText());
        oldResources[3] = Integer.parseInt(woolCount.getText());
        oldResources[4] = Integer.parseInt(wheatCount.getText());
        oldResources[5] = Integer.parseInt(knightCount.getText());

        int[] cards = App.getClientPlayer().getPlayerInventory().getCards();
        woodCount.setText(Integer.toString(cards[0]));
        brickCount.setText(Integer.toString(cards[1]));
        oreCount.setText(Integer.toString(cards[2]));
        woolCount.setText(Integer.toString(cards[3]));
        wheatCount.setText(Integer.toString(cards[4]));
        knightCount.setText(Integer.toString(cards[5]));

        //System.out.println("New wheatcount after stockController update: " + cards[4]);

        // If the new count is less than before, a card (or more) has been taken
        ArrayList<String> removedResources = new ArrayList<>();
        if(cards[0] < oldResources[0])
            removedResources.add("wood");
        if(cards[1] < oldResources[1])
            removedResources.add("brick");
        if(cards[2] < oldResources[2])
            removedResources.add("ore");
        if(cards[3] < oldResources[3])
            removedResources.add("wool");
        if(cards[4] < oldResources[4])
            removedResources.add("wheat");

        // Play the take card sound effect if any cards have been removed
        if(!removedResources.isEmpty()) {
            Sound.playTakeCard(); }

        // Play card animation for each removed card
        for (int i = 0; i < removedResources.size(); i++) {
            removeCardAnimation(animationCardForResource.get(removedResources.get(i))); }
    }

    // TODO leave this out of the final product
    public void testResources(){
        Inventory inventory = App.getClientPlayer().getPlayerInventory();
        inventory.changeCards("wood", 1);
        inventory.changeCards("brick", 2);
        inventory.changeCards("ore", 3);
        inventory.changeCards("wool", 4);
        inventory.changeCards("wheat", 5);
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
    }

    /** Animation that plays when a card is taken out of a player's inventory.
     * @param animationCard ImageView that moves out of the screen when that card is removed
     * @author Jeroen */
    private void removeCardAnimation(ImageView animationCard){
        animationCard.setVisible(true);

        double x = animationCard.getTranslateX();
        double y = animationCard.getTranslateY();

        if(!animationIsActive){
            AnimationTimer animationTimer = new AnimationTimer() {
                int tick = 0;
                @Override
                public void handle(long l) {
                    tick++;
                    animationIsActive = true;

                    animationCard.setTranslateY(animationCard.getTranslateY() - 2.8);   // Move the card up

                    if(tick > 40) {
                        animationCard.setOpacity(animationCard.getOpacity() - 0.06);    // After 40 ticks, start decreasing opacity per tick
                    }

                    if(animationCard.getOpacity() <= 0) {   // If card isn't visible anymore, stop animation and reset the card
                        this.stop();

                        animationCard.setTranslateX(x);
                        animationCard.setTranslateY(y);
                        animationCard.setOpacity(1);
                        animationCard.setVisible(false);

                        tick = 0;
                        animationIsActive = false;
                    }
                }
            };
            animationTimer.start();
        }
    }

    /** Player clicked on the knight card to replace the thief.
     * @author Jeroen */
   @FXML public void activateKnight() {
       // Check if it's player's turn
       if(App.getCurrentGame().getTradeStatus().equals("pending")) {
           ScreenController.getInstance().showAlertPopup();
           AlertPopUpController.getInstance().setAlertDescription("You can't activate a knight card when your trade offer is still pending.");
           return;
       }
       if(!App.getClientPlayer().isTurn()) {
           ScreenController.getInstance().showAlertPopup();
           AlertPopUpController.getInstance().setAlertDescription("You can't activate a knight card outside of your turn.");
           return;
       }
       // Checks if there still are knight cards left
       if(App.getCurrentGame().turnPlayerGetter().getPlayerInventory().getCards()[5] <= 0) {  // 5-knight
           ScreenController.getInstance().showAlertPopup();
           AlertPopUpController.getInstance().setAlertDescription("You don't have any knight cards left to activate.");
           return;
       }

       App.getCurrentGame().turnPlayerGetter().getPlayerInventory().changeCards("knight", -1);
       StockController.getInstance().updateResources();
       removeCardAnimation(animationKnightCard);
       GameSchermController.getInstance().highlightTiles(App.getCurrentGame().getBoard().getThief().getTile());

       logController.logKnightEvent();
       Sound.playSword();
    }

    @Override
    public void update(Game game) {
       updateClientPlayerInventory(game.getPlayers());
       updateResources();
    }

    private void updateClientPlayerInventory(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (App.getClientPlayer().getIdentifier() == App.getCurrentGame().getPlayers().get(i).getIdentifier()) {
                App.getClientPlayer().setPlayerInventory(players.get(i).getPlayerInventory());
            }
        }

    }

    /** Shows details about the knight card when the knight card is hovered */
    @FXML public void showKnightDetails() {
        ScreenController.getInstance().showKnightPopup();
    }
    @FXML public void hideKnightDetails() {
        ScreenController.getInstance().hideKnightPopup();
    }

    /** Assigns the animation cards to a resource */
    private void initializeAnimationCardMap() {
        animationCardForResource = new HashMap<>() {{
            put("wood", animationWood);
            put("brick", animationBrick);
            put("ore", animationOre);
            put("wool", animationSheep);
            put("wheat", animationWheat);
        }};
    }

    public static StockController getInstance(){
        if(stockController == null){
            stockController = new StockController();
        }
        return stockController;
    }
}
