package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class tradeController{

    // FXML fields
    @FXML
    private Text woodAmount;
    @FXML
    private Label takeWoodCount;
    @FXML
    private Label giveWoodCount;
    @FXML
    private Text brickAmount;
    @FXML
    private Text oreAmount;
    @FXML
    private Text sheepAmount;
    @FXML
    private Text wheatAmount;

    private static Player tradingPlayer;
    private Inventory playerInventory;
    private int[] playerCards;
    private int[] temp;

    public static void setTradingPlayer(Player player){
        tradingPlayer = player;
        System.out.println(tradingPlayer.getName());
    }

    private void setTradingInventory(Player player){
        playerInventory = player.getPlayerInventory();
        playerCards = playerInventory.getCards();
        temp = playerCards.clone();
    }

    private void raiseResource(Text text){
        text.setText(Integer.toString(Integer.parseInt(text.getText()) + 1));
    }

    private void lowerResource(Text text){
        text.setText(Integer.toString(Integer.parseInt(text.getText()) - 1));
    }

    private void raiseResource(Label text){
        text.setText(Integer.toString(Integer.parseInt(text.getText()) + 1));
    }

    private void lowerResource(Label text){
        text.setText(Integer.toString(Integer.parseInt(text.getText()) - 1));
    }

    // Routes
    @FXML
    public void giveMoreWheat(MouseEvent mouseEvent) {
    }

    public void takeMoreWheat(MouseEvent mouseEvent) {
    }

    public void giveMoreWood(MouseEvent mouseEvent) {
        if(temp[0] > 0){
            lowerResource(woodAmount);
            raiseResource(giveWoodCount);
            temp[0] -= 1;
            updateInventory();
        }
    }

    public void takeMoreWood(MouseEvent mouseEvent) {
        if(Integer.parseInt(giveWoodCount.getText()) > 0){
            raiseResource(woodAmount);
            lowerResource(giveWoodCount);
            temp[0] += 1;
            updateInventory();
        }
    }

    public void giveMoreBrick(MouseEvent mouseEvent) {
    }

    public void takeMoreBrick(MouseEvent mouseEvent) {
    }

    public void giveMoreSheep(MouseEvent mouseEvent) {
    }

    public void takeMoreSheep(MouseEvent mouseEvent) {
    }

    public void giveMoreOre(MouseEvent mouseEvent) {
    }

    public void takeMoreOre(MouseEvent mouseEvent) {
    }

    public void sendTrade(MouseEvent mouseEvent) {
    }

    public void resetTrade(MouseEvent mouseEvent) {
        setTradingInventory(tradingPlayer);
        // Reset to match player inventory
        System.out.println(temp[0]);
        woodAmount.setText(Integer.toString(playerCards[0]));
        takeWoodCount.setText("0");
        giveWoodCount.setText("0");
        brickAmount.setText(Integer.toString(playerCards[1]));
        oreAmount.setText(Integer.toString(playerCards[2]));
        sheepAmount.setText(Integer.toString(playerCards[3]));
        wheatAmount.setText(Integer.toString(playerCards[4]));
    }

    public void bankTrade(KeyEvent keyEvent) {
    }

    public void playerTrade(KeyEvent keyEvent) {
    }

    //Update player inventory
    public void updateInventory(){
        System.out.println("Inventory update");
        woodAmount.setText(Integer.toString(temp[0]));
        brickAmount.setText(Integer.toString(temp[1]));
        oreAmount.setText(Integer.toString(temp[2]));
        sheepAmount.setText(Integer.toString(temp[3]));
        wheatAmount.setText(Integer.toString(temp[4]));
    }

}
