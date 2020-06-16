package org.catan.Model;

import org.catan.App;
import org.catan.Controller.LogController;
import org.catan.logic.DatabaseConnector;

import java.util.ArrayList;

public class TurnManager {

    public static int turn = 0;

    public static void nextTurn(Player lastPlayer){
        ArrayList<Player> allPlayers = App.getCurrentGame().getPlayers();
        for (Player player : allPlayers){
            if (player == lastPlayer){
                if (allPlayers.indexOf(lastPlayer) == (allPlayers.size() - 1)){
                    turn = 0;
                } else {
                    turn += 1;
                }
                System.out.println(lastPlayer.getName() + " ended their turn. " + allPlayers.get(turn).getName() + "'s turn is up next.");
                lastPlayer.setTurn(false);
                allPlayers.get(turn).setTurn(true);
                LogController.setPlayer();
                LogController.getInstance().logStartTurnEvent();
            }
        }
    }
}
