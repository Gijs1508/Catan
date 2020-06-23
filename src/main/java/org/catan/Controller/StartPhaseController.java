package org.catan.Controller;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

public class StartPhaseController implements Observable {
    private static int startPhaseCount;
    private static boolean startPhaseActive = true;
    private boolean startPhaseHappened = false;
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

    public boolean isStartPhaseActive() {
        return startPhaseActive;
    }

    public void startPhaseCount() {
        StartPhaseController.startPhaseCount++;
    }

    public void checkStartPhase() {
        if (startPhaseCount == 2) {
            startPhaseActive = false;
            BoardController.getInstance().enableButtons();
        }
    }

    public void activateBuildingStartPhase() {
        if (startPhaseActive) {
            startPhaseHappened = true;
            BoardController.getInstance().villageStartPhase();
        }
    }

    @Override
    public void update(Game game) {
        if(game.turnPlayerGetter().getIdentifier() == App.getClientPlayer().getIdentifier() && !startPhaseHappened){
            activateBuildingStartPhase();
        }
        if (game.turnPlayerGetter().getIdentifier() != App.getClientPlayer().getIdentifier() && startPhaseHappened) {
            startPhaseHappened = false;
        }
    }

}
