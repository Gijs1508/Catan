package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Inventory;
import org.catan.Model.Sound;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Manages the pop up view to hand in cards
 * @author Kaz
 */

public class HandInController implements Initializable {

    @FXML private Text requiredAmount;
    @FXML private Text giveWood;
    @FXML private Text giveBrick;
    @FXML private Text giveOre;
    @FXML private Text giveWool;
    @FXML private Text giveWheat;
    @FXML private ImageView acceptBtn;

    private ScreenController screenController = ScreenController.getInstance();
    private int requiredCards = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calcRequiredAmount();
        requiredAmount.setText(Integer.toString(requiredCards));
        giveWood.setText("0");
        giveBrick.setText("0");
        giveOre.setText("0");
        giveWool.setText("0");
        giveWheat.setText("0");
    }

    /**
     * This method changes the player inventory when the required amount of cards are selected
     * @author Kaz
     */
    public void accept() {
        Sound.playClick();

        if(textToInt(requiredAmount) == 0){
            getInventory().changeCards("wood", -textToInt(giveWood));
            getInventory().changeCards("brick", -textToInt(giveBrick));
            getInventory().changeCards("ore", -textToInt(giveOre));
            getInventory().changeCards("wool", -textToInt(giveWool));
            getInventory().changeCards("wheat", -textToInt(giveWheat));
            screenController.hideHandInPopUp();
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        }
    }

    /**
     * This method resets the selected cards to 0
     * @author Kaz
     */
    public void reset() {
        calcRequiredAmount();
        requiredAmount.setText(Integer.toString(requiredCards));
        giveWood.setText("0");
        giveBrick.setText("0");
        giveOre.setText("0");
        giveWool.setText("0");
        giveWheat.setText("0");
    }

    private Inventory getInventory(){
        return App.getClientPlayer().getPlayerInventory();
    }

    private int[] getInventoryCards(){
        return getInventory().getCards();
    }

    private void calcRequiredAmount(){
        requiredCards = getInventory().cardsTotalGetter() / 2;
    }

    /**
     * This method raises the wheat counter
     * @author Kaz
     */
    public void raiseWheat() {
        giveResource(giveWheat, 4);
    }

    /**
     * This method raises the wood counter
     * @author Kaz
     */
    public void raiseWood() {
        giveResource(giveWood, 0);
    }

    /**
     * This method raises the brick counter
     * @author Kaz
     */
    public void raiseBrick() {
        giveResource(giveBrick, 1);
    }

    /**
     * This method raises the wool counter
     * @author Kaz
     */
    public void raiseWool() {
        giveResource(giveWool, 3);
    }

    /**
     * This method raises the ore counter
     * @author Kaz
     */
    public void raiseOre() {
        giveResource(giveOre, 2);
    }

    private int textToInt(Text resource){
        return Integer.parseInt(resource.getText());
    }

    private String raiseResource(Text resource){
        return Integer.toString(textToInt(resource) + 1);
    }

    private void giveResource(Text resource, int inventoryIndex){
        int inventoryCard = getInventoryCards()[inventoryIndex];
        if(textToInt(resource) < inventoryCard && textToInt(requiredAmount) > 0){
            resource.setText(raiseResource(resource));
            requiredAmount.setText(Integer.toString(textToInt(requiredAmount) - 1));
        }
    }

}
