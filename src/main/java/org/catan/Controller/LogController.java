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

    /** Initializes the LogController */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        logsBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D)); // Scroll to bottom with each update
        logController = this;
    }

    /** Updates the logs for all players */
    @Override public void update(Game game) {
        int oldLogSize = logs.getLogs().size();
        if (game.getLogs().size() > oldLogSize) {
            for (int i = logs.getLogs().size(); i < game.getLogs().size(); i++) {
                if (game.getLogs().get(i).getLogType().equals("txt")) {
                    addTxtLogToLogsPane(game.getLogs().get(i));
                }
                if (game.getLogs().get(i).getLogType().equals("img")) {
                    addImgLogToLogsPane(game.getLogs().get(i));
                }
                logs.addLog(game.getLogs().get(i));

            }
            checkForResourceLogs(game, oldLogSize);
            App.getCurrentGame().setLogs(game.getLogs());
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

    /** Logs the event when a player throws the die.
     * @param dice1 String value of first dice result
     * @param dice2 String value of second dice result */
    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player);
        log.addImgPath(dice1);
        log.addImgPath(dice2);
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event when a player receives a resource card.
     * @param receivedCards The cards (String) that are received */
    public void logReceiveEvent(ArrayList<String> receivedCards) {
        try {
            Log log = new Log("receive", App.getClientPlayer().getName());
            for (String receivedCard : receivedCards) {
                log.addImgPath(receivedCard);
            }
            addImgLogToLogsPane(log);
            storeLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Logs the event when a card is stolen from another player.
     * @param opponent The player that was stolen from */
    public void logStealEvent(Player opponent) {
        Log log = new Log("steal", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event when a trade is sent. */
    public void logTradeSentEvent() {
        Log log = new Log("tradesent", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event when a trade has succeeded.
     * @param senderName The name of the sender of the trade offer */
    public void logTradeSucceededEvent(String senderName) {
        Log log = new Log("tradesucceeded", player, senderName);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the start of a player's turn. */
    public void logStartTurnEvent() {
        Log log = new Log("startturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the end of a player's turn. */
    public void logEndTurnEvent() {
        Log log = new Log("endturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a player upgrades a settlement to a city. */
    public void logUpgradeEvent() {
        Log log = new Log("upgrade", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a road is built. */
    public void logRoadEvent() {
        Log log = new Log("road", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a settlement is built. */
    public void logSettlementEvent() {
        Log log = new Log("settlement", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where the robber is moved. */
    public void logRobberEvent() {
        Log log = new Log("robber", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a knight is activated. */
    public void logKnightEvent() {
        Log log = new Log("knight", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /** Logs the event where a development card is bought. */
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

    private void checkForResourceLogs(Game game, int oldLogsSize) {
        for (int i = oldLogsSize; i < game.getLogs().size(); i++) {
            if (game.getLogs().get(i).getLogType().equals("img")) {
                if (game.getLogs().get(i).getEventType().equals("roll")) {
                    ResourcesController.getInstance().updateByLog(game.getLogs().get(i));
                }
            }
        }
    }
}
