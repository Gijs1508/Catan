package org.catan.Controller;
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
        }
    }

    private void activateBuildingStartPhase(Player lastPlayer) {
        // Point A
        GameSchermController.getInstance().villageStartPhase();
        // Wait till road is build
        // If roadArray Is bigger then add point A go further in application
        TurnManager.nextTurn(lastPlayer);
    }
}
