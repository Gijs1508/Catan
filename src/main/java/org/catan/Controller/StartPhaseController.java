package org.catan.Controller;
import javafx.fxml.Initializable;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPhaseController implements Observable {
    private static int startPhaseCount;
    //TODO Adding to the database if possible
    // So that a log can be displayed when startPhase has ended
    private static int startPhaseCountGlobal;
    private static boolean startPhaseActive = true;
    private static boolean waitState = true;
    private static StartPhaseController startPhaseController;

    public StartPhaseController() {
        startPhaseController = this;
    }

    public static StartPhaseController getInstance(){
        if(startPhaseController == null){
            startPhaseController = new StartPhaseController();
        }
        return startPhaseController;
    }

//    public void run() {
//        while(true) {
//            if (App.getClientPlayer().isTurn()) {
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {}
//                activateBuildingStartPhase();
//                while(waitState) {
//                    System.out.println("I am looping");
//                    try {
//                        System.out.println("sleepy time");
//                        Thread.sleep(500);
//                    } catch (Exception e){}
//
//                    // Can't use wait because then we need to multithread the program
//                }
//                try {
//                    Thread.sleep(3000);
//                } catch (Exception e) {}
//            } else if(!startPhaseActive) {
//                break;
//            }
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException ei) {}
//            waitState = true;
//        }
//    }

//    public static void setWaitState(boolean waitState1) {
//        waitState = waitState1;
//    }

    public boolean isStartPhaseActive() {
        return startPhaseActive;
    }

    public void startPhaseCount() {
        StartPhaseController.startPhaseCount++;
    }

    public void checkStartPhase() {
        if (startPhaseCount == 2) {
            startPhaseActive = false;
            GameSchermController.getInstance().startPhaseButtonsVisible();
        } else if (startPhaseCountGlobal == (App.getCurrentGame().getPlayers().size() * 2)){
            LogController.getInstance().logStartPhaseEnded();
        }
    }

    public void activateBuildingStartPhase() {
        if (startPhaseActive)
            GameSchermController.getInstance().villageStartPhase();
    }

    @Override
    public void update(Game game) throws IOException {
        if(game.turnPlayerGetter().equals(App.getClientPlayer())){
            activateBuildingStartPhase();
        }
    }

}
