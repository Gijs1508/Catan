package org.catan.Model;

import org.catan.Controller.CreateController;
import org.catan.Controller.BuildSettlementController;
import org.catan.Helper.BuildVillages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Dice {

    /*
    This method returns a arraylist containing two random numbers
    between 1-6. 
     */
    public HashMap<Integer, ArrayList<String>> throwDice() {
        HashMap<Integer, ArrayList<String>> diceResult = new HashMap<>();
        ArrayList<String> dices = new ArrayList<>();
        Random rand = new Random();
        int dice1 = (rand.nextInt(6) + 1);
        int dice2 = (rand.nextInt(6) + 1);
        Integer total = dice1 + dice2;
        dices.add(String.valueOf(dice1));
        dices.add(String.valueOf(dice2));
        diceResult.put(total, dices);

        setPlayerResources();
        return diceResult;
    }

    private void setPlayerResources(){
        if(BuildVillages.getBuildVillages() != null){
            for (Village village : BuildVillages.getBuildVillages()) {
                for (Tile tile : village.getConnectedTiles()){
                    int amount;
                    if(village.isUpgraded()) amount = 2;
                    else amount = 1;
                    Player.getMainPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                }
            }
        }
    }
}
