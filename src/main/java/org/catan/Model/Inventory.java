package org.catan.Model;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.Controller.StockController;

import java.util.HashMap;

public class Inventory {

    private int[] cards = {0, 0, 0, 0, 0, 0};
    /* INDEXES
    [0]: Wood
    [1]: Brick
    [2]: Ore
    [3]: Sheep
    [4]: Wheat
    [5]: Knight
     */

    public Inventory(){}

    public int[] getCards(){
        return this.cards;
    }

    public void changeCards(String type, int amount){
        if (type == "wood"){
            cards[0] += amount;
        } else if (type == "brick"){
            cards[1] += amount;
        } else if (type == "ore"){
            cards[2] += amount;
        } else if (type == "sheep"){
            cards[3] += amount;
        } else if (type == "wheat"){
            cards[4] += amount;
        } else if (type == "knight"){
            cards[5] += amount;
        }
        StockController.getInstance().updateResources();
    }

}
