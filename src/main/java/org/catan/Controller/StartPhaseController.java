package org.catan.Controller;

public class StartPhaseController {
    private static int startPhaseCount;
    private static boolean startPhaseActive = true;

    public static int getStartPhaseCount() {
        return startPhaseCount;
    }

    public static boolean isStartPhaseActive() {
        return startPhaseActive;
    }

    public static void setStartPhaseCount(int startPhaseCount) {
        StartPhaseController.startPhaseCount = startPhaseCount;
    }

    public static void setStartPhaseActive(boolean startPhaseActive) {
        StartPhaseController.startPhaseActive = startPhaseActive;
    }
}
