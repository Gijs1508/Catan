package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.catan.App;
import org.catan.Model.Inventory;
import org.catan.Model.Player;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.ResourceBundle;

/** Forces the player to hand in half of their resource cards.
 * The player can pick those cards.
 * @author Kaz */

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

    public void accept() {
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

    public void raiseWheat(MouseEvent mouseEvent) {
        giveResource(giveWheat, 4);
    }

    public void raiseWood(MouseEvent mouseEvent) {
        giveResource(giveWood, 0);
    }

    public void raiseBrick(MouseEvent mouseEvent) {
        giveResource(giveBrick, 1);
    }

    public void raiseWool(MouseEvent mouseEvent) {
        giveResource(giveWool, 3);
    }

    public void raiseOre(MouseEvent mouseEvent) {
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
