package org.catan.Controller;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class StartPhaseController implements Observable {
    private static int startPhaseCount;
    //TODO Adding to the database if possible
    // So that a log can be displayed when startPhase has ended
    private static int startPhaseCountGlobal;
    private static boolean startPhaseActive = true;
    private boolean startPhaseHappened = false;
    private static StartPhaseController startPhaseController;

    public StartPhaseController() {
        startPhaseController = this;
    }

    public void setStartPhaseHappened(boolean happened) {
        this.startPhaseHappened = happened;
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
            GameSchermController.getInstance().startPhaseButtonsVisible();
        } else if (startPhaseCountGlobal == (App.getCurrentGame().getPlayers().size() * 2)){
            LogController.getInstance().logStartPhaseEnded();
        }
    }

    public void activateBuildingStartPhase() {
        if (startPhaseActive)
            startPhaseHappened = true;
            GameSchermController.getInstance().villageStartPhase();
    }

    @Override
    public void update(Game game) throws IOException {
        if(game.turnPlayerGetter().getIdentifier() == App.getClientPlayer().getIdentifier() && !startPhaseHappened){
            activateBuildingStartPhase();
        }
    }

}
