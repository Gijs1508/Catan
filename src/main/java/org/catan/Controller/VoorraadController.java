package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

public class VoorraadController {

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

    private Text[] viewInventory = {woodCount, brickCount, oreCount, sheepCount, wheatCount, knightCount};

    @FXML
    private void initialize(){}

    public static void updateWood(Text woodCount){
        int[] cards = Player.mainPlayer.getPlayerInventory().getCards();
        woodCount.setText(Integer.toString(cards[0]));
        System.out.println();
//        brickCount.setText(Integer.toString(cards[1]));
//        oreCount.setText(Integer.toString(cards[2]));
//        sheepCount.setText(Integer.toString(cards[3]));
//        wheatCount.setText(Integer.toString(cards[4]));
//        knightCount.setText(Integer.toString(cards[5]));
    }

    public static void updateBrick(Text brickCount){
        int[] cards = Player.mainPlayer.getPlayerInventory().getCards();
        brickCount.setText(Integer.toString(cards[1]));
    }

    public static void updateOre(Text oreCount){
        int[] cards = Player.mainPlayer.getPlayerInventory().getCards();
        oreCount.setText(Integer.toString(cards[2]));
    }

    public static void updateSheep(Text sheepCount){
        int[] cards = Player.mainPlayer.getPlayerInventory().getCards();
        sheepCount.setText(Integer.toString(cards[3]));
    }

    public void testResources(){
        Inventory playerInventory = Player.getMainPlayer().getPlayerInventory();

        playerInventory.changeCards(0, 5);
        updateWood(woodCount);
        playerInventory.changeCards(1, 3);
        updateBrick(brickCount);
        playerInventory.changeCards(2, 2);
        updateOre(oreCount);
    }

}
