package org.catan.Model;

import org.catan.Controller.CreateController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Dice {

    public static HashMap<Integer, ArrayList<String>> throwDice() {
        HashMap<Integer, ArrayList<String>> diceResult = new HashMap<>();
        ArrayList<String> dices = new ArrayList<>();
        Random rand = new Random();
        int dice1 = (rand.nextInt(6) + 1);
        int dice2 = (rand.nextInt(6) + 1);
        Integer total = dice1 + dice2;
        dices.add(String.valueOf(dice1));
        dices.add(String.valueOf(dice2));
        diceResult.put(total, dices);

        return diceResult;
    }
}
