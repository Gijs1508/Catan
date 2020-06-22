package org.catan.Model;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Controller.StockController;
import org.catan.logic.DatabaseConnector;

import java.util.HashMap;

/**
 * Used to keep track of player & bank inventory.
 * All 4 resources plus the knight cards are stored in a player's inventory.
 *
 * @Author Kaz
 */
public class Inventory {

    private int ownerID;

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

    public int[] getCards(){
        return this.cards;
    }

    public String[] strCardsGetter(){
        return new String[]{"wood", "brick", "ore", "wool", "wheat", "knight"};
    }


    /**
     * This method updates the player resources
     * @param type The resource that needs to be changed
     */
    public void changeCards(String type, int amount){
        switch(type){
            case "wood": cards[0] += amount; break;
            case "brick": cards[1] += amount; break;
            case "ore": cards[2] += amount; break;
            case "wool": cards[3] += amount; break;
            case "wheat": cards[4] += amount; break;
            case "knight": cards[5] += amount; break;
        }
    }

    public int developmentCardsLeftGetter() {
        return cards[5];
    }


    /**
     * This method converts the player's resources into a hashmap
     * @return the hashmap
     */
    public HashMap<String, Integer> resourceToAmountGetter() {
        HashMap<String, Integer> resourceToAmount = new HashMap<>() {{
            put("wood", cards[0]);
            put("brick", cards[1]);
            put("ore", cards[2]);
            put("wool", cards[3]);
            put("wheat", cards[4]);
        }};
        return resourceToAmount;
    }

    /**
     * This method counts all of the player's cards
     * @return the total amount of cards
     */
    public int cardsTotalGetter(){
        int total = 0;
        for(int card : cards){
            total += card;
        }
        return total;
    }

    /**
     * This method deducts the knight cards from the player's total card count
     * @return the total amount of resources
     */
    public int cardsTotalMinusKnightGetter(){
        return cardsTotalGetter() - cards[5];
    }

    public int getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

}
