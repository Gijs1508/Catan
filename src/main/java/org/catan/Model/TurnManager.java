package org.catan.Model;

import org.catan.App;
import org.catan.Controller.LogController;

import java.util.ArrayList;

public class TurnManager {

    public static int turn = 0;

    public static void nextTurn(){
        Player lastPlayer = App.getCurrentGame().turnPlayerGetter();
        ArrayList<Player> allPlayers = App.getCurrentGame().getPlayers();
        for (Player player : allPlayers){
            if (player.equals(lastPlayer)){
                if (allPlayers.indexOf(lastPlayer) == (allPlayers.size() - 1)){
                    turn = 0;
                } else {
                    turn++;
                }

                System.out.println(lastPlayer.getName() + " ended their turn. " + allPlayers.get(turn).getName() + "'s turn is up next.");

                lastPlayer.setTurn(false);
                App.getCurrentGame().getPlayers().get(turn).setTurn(true);

                if(App.getClientPlayer().isTurn()) {
                    Sound.playStartTurnJingle();
                }
                LogController.setPlayer();
                LogController.getInstance().logStartTurnEvent();
            }
        }
    }
}
