package org.catan.Model;

import org.catan.Controller.ScoreController;

import java.util.*;

/**
 * Bank that sells the players random development cards.
 * @author Sabrina, Jeroen
 */

public class Bank {
    private Inventory bankInventory;
    private LinkedHashMap<String, Integer> developmentCardStock;
    private static Bank bank = new Bank();
    private static ScoreController scoreController = ScoreController.getInstance();

    public Bank(){
        bank = this;
        initializeInventory();
    }

    // Creates a new inventory for the bank
    private void initializeInventory() {
        this.bankInventory = new Inventory();
        this.bankInventory.changeCards("knight", 25); // Represents all development cards (also victory point cards)

        // Each development card the bank has with its amount left
        // Bank has 25 cards, of which 18 are knight cards and 7 are victory point cards
        developmentCardStock = new LinkedHashMap<>(){{
           put("victoryPoint", 7);
           put("knight", 18);
        }};
    }

    /** Takes a random development card from the bank's inventory.
     * Calculates the odds of getting each card.
     * @author Jeroen
     * @return type of development card () */
    public String takeDevelopmentCard() {
        if(bankInventory.developmentCardsLeftGetter() > 0) {
            // Get random key from developmentCardStock
            int randomIndex;
            double r = new Random().nextDouble();
            if (r < 0.35) randomIndex = 0;   // Chance of getting victoryPoint is 35%
            else randomIndex = 1;           // Chance of getting knight is 65%

            String developmentCard = null;
            for (int i = 0; i < 1; i++) {
                Object developmentCardObject = developmentCardStock.keySet().toArray()[randomIndex]; //

                developmentCard = developmentCardObject.toString();
                // If this specific card is out of stock
                if (developmentCardStock.get(developmentCard) <= 0) {
                    // Repeat process, but take the other card
                    switch (randomIndex) {
                        case 0:
                            randomIndex = 1;
                            break;
                        case 1:
                            randomIndex = 0;
                            break;
                    }
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