package org.catan.Controller;
import org.catan.App;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;

public class StartPhaseController implements Runnable {
    private static int startPhaseCount;
    //TODO Adding to the database if possible
    // So that a log can be displayed when startPhase has ended
    private static int startPhaseCountGlobal;
    private static boolean startPhaseActive = true;

    public void run() {
        while(true) {
            if (App.getClientPlayer().isTurn()) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                activateBuildingStartPhase();
                try {
                    Thread.sleep(8000);
//                    wait(startPhaseCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(!startPhaseActive) {
                break;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ei) {
                System.out.println("Im doing sleepy time");
            }
        }
    }

    public static boolean isStartPhaseActive() {
        return startPhaseActive;
    }

    public static void startPhaseCount() {
        StartPhaseController.startPhaseCount++;
    }

    public static void checkStartPhase() {
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
}
