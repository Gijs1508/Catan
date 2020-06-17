package org.catan.Controller;
import org.catan.App;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;

public class StartPhaseController {
    private static int startPhaseCount;
    //TODO Adding to the database if possible
    // So that a log can be displayed when startPhase has ended
    private static int startPhaseCountGlobal;
    private static boolean startPhaseActive = true;

    public StartPhaseController() {
        GameSchermController.getInstance().startPhaseButtonsInvisible();
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
        GameSchermController.getInstance().villageStartPhase();
    }
}
