package org.catan.Model;

import org.catan.Controller.*;

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

    /**
     *  This creates two random integers between 1-6.
     * @return diceResult: a hashmap containing the total of the two integers
     * and an arraylist with the two separate integers
     * @author Gijs
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

        ResourcesController.getInstance().setPlayerResources(total);

        LogController.getInstance().logRollEvent(Integer.toString(dice1), Integer.toString(dice2));

        return diceResult;
    }

}
