package org.catan.Model;

import org.catan.App;
import org.catan.Controller.LogController;
import org.catan.Controller.StartPhaseController;
import org.catan.logic.DatabaseConnector;

import java.util.ArrayList;

public class TurnManager {

    public static void nextPlayer(){
        Player currentPlayer = App.getCurrentGame().turnPlayerGetter();
        Player nextPlayer;
        ArrayList<Player> allPlayers = App.getCurrentGame().getPlayers();
        int lastIndex = allPlayers.size() - 1;
        for (Player player : allPlayers){
            if (currentPlayer == allPlayers.get(lastIndex)){
                nextPlayer = allPlayers.get(0);
            } else{
                nextPlayer = allPlayers.get(allPlayers.indexOf(currentPlayer) + 1);
            }
            currentPlayer.setTurn(false);
            nextPlayer.setTurn(true);
            App.getCurrentGame().setTradeStatus("closed");
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        }
    }
}
