package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Player;
import org.catan.View.panes.LogPane;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/** Logs all events that happen in the game, so players get notified of those events.
 *
 * @author Jeroen
 */

public class LogController implements Initializable, Observable {
    @FXML private VBox logsBox;
    @FXML private ScrollPane scrollPane;

    private Logs logs = new Logs();
    private static String player;
    private static LogController logController;


    public LogController() {
        player = App.getClientPlayer().getName();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logsBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D)); // Scroll to bottom with each update
        logController = this;
    }

    /** Updates the logs on each database update.
     * @author Werner, Jeroen */
    @Override public void update(Game game) {
        int oldLogsSize = logs.getLogs().size();
        if (game.getLogs().size() > logs.getLogs().size()) {
            for (int i = logs.getLogs().size(); i < game.getLogs().size(); i++) {
                if (game.getLogs().get(i).getLogType().equals("txt")) { // Log is a text log
                    addTxtLogToLogsPane(game.getLogs().get(i));
                }
                if (game.getLogs().get(i).getLogType().equals("img")) { // Log is an image log
                    addImgLogToLogsPane(game.getLogs().get(i));
                }
                logs.addLog(game.getLogs().get(i));
            }
            App.getCurrentGame().setLogs(logs.getLogs());
            checkForResourceLogs(game, oldLogsSize);
        }
    }

    private void checkForResourceLogs(Game game, int oldLogsSize) {
        for (int i = oldLogsSize; i < game.getLogs().size(); i++) {
            if (game.getLogs().get(i).getLogType().equals("img")) {
                if (game.getLogs().get(i).getEventType().equals("roll")) {
                    System.out.println("Start resource update");
                    ResourcesController.getInstance().updateByLog(game.getLogs().get(i));
                }
            }
        }
    }

    private void addToLogsBox(LogPane logPane) { // Add a new LogPane to the LogsBox
        logsBox.getChildren().add(logPane.getLogGrid()); }

    private void addImagesToLogPane(LogPane logPane, ArrayList<String> imagePaths) {
        for (int i = 0; i < imagePaths.size(); i++) {
            Image image = new Image(imagePaths.get(i));
            logPane.addImage(image);
        }
    }

    private void addTxtLogToLogsPane(Log log) { // Add a new log to a new LogPane for text events
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
    }

    private void addImgLogToLogsPane(Log log) { // Add a new log to a new LogPane for image events
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
        addImagesToLogPane(logPane, log.getImgPaths());
    }

    /** Logs a roll event.
     * @param dice1 number of dice 1 in String
     * @param dice2 number of dice 2 in String */
    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player);
        log.addImgPath(dice1);
        log.addImgPath(dice2);
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player received resource cards.
     * @param receivedCards ArrayList of the cards the player received */
    public void logReceiveEvent(ArrayList<String> receivedCards) {
        try {
            Log log = new Log("receive", player);
            for (int i = 0; i < receivedCards.size(); i++) {
                log.addImgPath(receivedCards.get(i));
            }
            addImgLogToLogsPane(log);
            storeLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Logs the event where a resource card was stolen from another player.
     * @param opponent The player that was stolen from */
    public void logStealEvent(Player opponent) {
        Log log = new Log("steal", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a trade has happened between two players.
     * @param opponent The player that was traded with */
    public void logTradeEvent(Player opponent) {
        Log log = new Log("trade", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player starts their turn. */
    public void logStartTurnEvent() {
        Log log = new Log("startturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player ends their turn. */
    public void logEndTurnEvent() {
        Log log = new Log("endturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player upgraded a settlement to a city. */
    public void logUpgradeEvent() {
        Log log = new Log("upgrade", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player built a road. */
    public void logRoadEvent() {
        Log log = new Log("road", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player built a settlement. */
    public void logSettlementEvent() {
        Log log = new Log("settlement", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where the robber had been moved by a player. */
    public void logRobberEvent() {
        Log log = new Log("robber", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player wins the game. */
    public void logWinEvent() {
        Log log = new Log("win", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player activated a knight card. */
    public void logKnightEvent() {
        Log log = new Log("knight", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player bought a development card. */
    public void logDevelopmentCardEvent() {
        Log log = new Log("development", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    // Stores log in logs
    private void storeLog(Log log) {
        logs.addLog(log);
        App.getCurrentGame().setLogs(logs.getLogs());
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame()); // Meaning that methods that create logs don't have to update the database within that method
    }

    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }
}
