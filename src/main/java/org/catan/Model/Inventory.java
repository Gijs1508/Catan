package org.catan.Model;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

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

    public void changeCards(int index, int aantal){
        cards[index] += aantal;
    }

}
