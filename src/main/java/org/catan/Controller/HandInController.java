package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import java.net.URL;
import java.util.ResourceBundle;

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
//            Stage stage = (Stage) acceptBtn.getScene().getWindow();
//            stage.close();
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
        return Player.getMainPlayer().getPlayerInventory();
    }

    private int[] getInventoryCards(){
        return getInventory().getCards();
    }

    private void calcRequiredAmount(){
        requiredCards = getInventory().getCardsTotal() / 2;
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
