package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

public class StockController {


    //Make controller available to other classes
    private static StockController stockController;

    @FXML
    private Text wheatCount;
    @FXML
    private Text woodCount;
    @FXML
    private Text brickCount;
    @FXML
    private Text sheepCount;
    @FXML
    private Text oreCount;
    @FXML
    private Text knightCount;
    @FXML
    private Button testResources;

    public StockController(){
        stockController = this;
    }

    public static StockController getInstance(){
        if(stockController == null){
            stockController = new StockController();
        }
        return stockController;
    }

    @FXML
    private void initialize(){}

    public void testResources(){
        Inventory inventory = Player.getMainPlayer().getPlayerInventory();
        inventory.changeCards(0, 1);
        inventory.changeCards(1, 2);
        inventory.changeCards(2, 3);
        inventory.changeCards(3, 4);
        inventory.changeCards(4, 5);
        updateResources();
    }

    public void updateResources(){
        int[] cards = Player.getMainPlayer().getPlayerInventory().getCards();
        woodCount.setText(Integer.toString(cards[0]));
        brickCount.setText(Integer.toString(cards[1]));
        oreCount.setText(Integer.toString(cards[2]));
        sheepCount.setText(Integer.toString(cards[3]));
        wheatCount.setText(Integer.toString(cards[4]));
        knightCount.setText(Integer.toString(cards[5]));
    }

    public void activateKnight(MouseEvent mouseEvent) {
    }

    public void showKnightDetails(MouseEvent mouseEvent) {
    }

    public void hideKnightDetails(MouseEvent mouseEvent) {
    }
}
