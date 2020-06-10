package org.catan.Model;

import org.catan.App;
import org.catan.Controller.GameSchermController;
import org.catan.Controller.ScreenController;
import org.catan.Helper.BuildVillages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
        // Throwing 7 properties
        if(total == 7){
            //TODO Andere spelers ook laten inleveren
            if(Player.getMainPlayer().getPlayerInventory().cardsTotalGetter() > 7){
                System.out.println("Speler moet kaarten inleveren");
                screenController.showHandInPopUp();
//                App.HandInPopUp();
            }
            //TODO Rover verzetten
            GameSchermController.getInstance().highlightTiles(Thief.getTile());
        }

        setPlayerResources(total);
        return diceResult;
    }

    private void setPlayerResources(int total){
        if(BuildVillages.getBuildVillages() != null){
            for (Village village : BuildVillages.getBuildVillages()) {
                for (Tile tile : village.getConnectedTiles()){
                    int amount;
                    if(village.isUpgraded()){
                        amount = 2;
                    } else {
                        amount = 1;
                    }
                    if(total == tile.getNumber()){
                        Player.getMainPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                    }
                }
            }
        } else {
            System.out.println("ja jan ze blijven leeg");
        }
    }
}
