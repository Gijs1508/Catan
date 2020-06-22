package org.catan.Model;

import org.catan.App;
import org.catan.Controller.GameSchermController;
import org.catan.Controller.LogController;
import org.catan.Controller.ScreenController;
import org.catan.Controller.ThiefController;
import org.catan.Helper.BuildVillages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Used to handle a dice throw.
 * Gets called when the player clicks on the throw dice button.
 * Returns a hashmap containing the total of the result and an arraylist of the first and second dice number.
 *
 * @Author Gijs van der Weijden
 */
public class Dice {

    private ScreenController screenController = ScreenController.getInstance();

    /*
    This method returns a arraylist containing two random numbers
    between 1-6. 
     */
    public HashMap<Integer, ArrayList<String>> throwDice() throws IOException {
        HashMap<Integer, ArrayList<String>> diceResult = new HashMap<>();
        ArrayList<String> dices = new ArrayList<>();
        Random rand = new Random();
        int dice1 = (rand.nextInt(6) + 1);
        int dice2 = (rand.nextInt(6) + 1);
        Integer total = dice1 + dice2;
        dices.add(String.valueOf(dice1));
        dices.add(String.valueOf(dice2));
        diceResult.put(total, dices);

        LogController.getInstance().logRollEvent(Integer.toString(dice1), Integer.toString(dice2));

        setPlayerResources(total);
        return diceResult;
    }

    private void setPlayerResources(int total){
        ArrayList<Tile> usedTiles = new ArrayList<>();
        if(App.getCurrentGame().getBoard().getSettlements() != null) {
            for (Village village : App.getCurrentGame().getBoard().getSettlements()) {
                for (Tile tile : village.getConnectedTiles()){
                    int amount;
                    if(village.isUpgraded()){
                        amount = 2;
                    } else {
                        amount = 1;
                    }
                    if(total == tile.getNumber() && !usedTiles.contains(tile)){
                        addResourcesToPlayer(App.getClientPlayer().getColor(), tile, amount);
                        usedTiles.add(tile);
                    }
                }
            }
        }
    }


    private void addResourcesToPlayer(String playerColor, Tile tile, int amount){
        ArrayList<Village> usedVillages = new ArrayList<>();
        ArrayList<String> receivedResources = new ArrayList<>();

        System.out.println("==============================");
        for(Village vill : App.getCurrentGame().getBoard().getSettlements()){
            System.out.println("villagekleur + playerkleur: " + vill.getColor() + "\t" + playerColor);
            if(vill.getColor().equals(playerColor) && !usedVillages.contains(vill)){
                System.out.println("ik geef je " + tile.getType());
                System.out.println();
                System.out.println(vill.getColor() + "\t" + vill.getX() + "\t" + vill.getY());

                App.getClientPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                receivedResources.add(tile.getType());
                usedVillages.add(vill);
            }
        }
        System.out.println("==============================");


        if(!receivedResources.isEmpty()) {
            LogController.getInstance().logReceiveEvent(receivedResources);
        }


    }
}
