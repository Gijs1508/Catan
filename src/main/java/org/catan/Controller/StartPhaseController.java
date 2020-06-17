package org.catan.Controller;

public class StartPhaseController {
    private static int startPhaseCount;
    private static boolean startPhaseActive = true;

    public StartPhaseController() {
        activateStartPhase();
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

    private void activateStartPhase() {
        GameSchermController.getInstance().startPhaseButtonsInvisible();
    }
}
