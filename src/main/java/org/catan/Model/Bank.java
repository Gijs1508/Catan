package org.catan.Model;

import org.catan.Controller.ScoreController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Bank {
    private Inventory bankInventory;
    private HashMap<String, Integer> developmentCardStock;
    private static Bank bank = new Bank();
    private static ScoreController scoreController = ScoreController.getInstance();

    public Bank(){
        bank = this;
        initializeInventory();
    }

    private void initializeInventory() {
        this.bankInventory = new Inventory();
        // Kaarten toevoegen aan bank, kijken hoe we dit kunnen veranderen naar kaarten over in stapel
        this.bankInventory.changeCards("wood", 50);
        this.bankInventory.changeCards("brick", 50);
        this.bankInventory.changeCards("ore", 50);
        this.bankInventory.changeCards("wool", 50);
        this.bankInventory.changeCards("wheat", 50);
        this.bankInventory.changeCards("knight", 25); // Represents all development cards (also victory point cards)

        developmentCardStock = new HashMap<>(){{
           put("victoryPoint", 7);
           put("knight", 18);
        }};
    }

    public String takeDevelopmentCard() {
        if(bankInventory.getDevelopmentCardsLeft() > 0) {
            // Get random key from developmentCardStock
            String developmentCard = null;
            for (int i = 0; i < 1; i++) {
                Object developmentCardObject = developmentCardStock.keySet().toArray()[new Random().nextInt(developmentCardStock.keySet().toArray().length)];
                developmentCard = developmentCardObject.toString();
                if (developmentCardStock.get(developmentCard) <= 0) {
                    i--;
                }
            }

            // Remove that development card from the developmentCardStock, the inventory and update the score view
            developmentCardStock.replace(developmentCard, developmentCardStock.get(developmentCard) - 1);
            bankInventory.changeCards("knight", -1);
            scoreController.removeDevelopmentCardFromBankView();
            
            return developmentCard;
        }
        else return "bankEmpty";
    }

    public Inventory getCards(){
        return this.bankInventory;
    }

    public static Bank getBank() {
        return bank;
    }

}