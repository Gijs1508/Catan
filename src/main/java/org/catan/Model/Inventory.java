package org.catan.Model;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.Controller.StockController;

import java.util.HashMap;

public class Inventory {

    /* Array of all resources
    INDEXES:
    [0]: Wood
    [1]: Brick
    [2]: Ore
    [3]: Wool
    [4]: Wheat
    [5]: Knight
     */
    private int[] cards = {0, 0, 0, 0, 0, 0};

    public Inventory(){}

    public int[] getCards(){
        return this.cards;
    }

    //Changing a player's cards
    public void changeCards(String type, int amount){
        System.out.println(type + "\t" + String.valueOf(amount));
        switch(type){
            case "wood": cards[0] += amount; break;
            case "brick": cards[1] += amount; break;
            case "ore": cards[2] += amount; break;
            case "sheep": cards[3] += amount; break;
            case "wheat": cards[4] += amount; break;
            case "knight": cards[5] += amount; break;

        }
        StockController.getInstance().updateResources();
    }

}
