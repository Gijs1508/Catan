package org.catan.Controller;
import org.catan.App;
import org.catan.Model.Player;
import org.catan.Model.TurnManager;

public class StartPhaseController {
    private static int startPhaseCount;
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
        } else if (startPhaseCount == (App.getCurrentGame().getPlayers().size() * 2)){
            //            LogController.getInstance().logStartPhase();
        }
    }

    public void activateBuildingStartPhase() {
        GameSchermController.getInstance().villageStartPhase();
    }
}
