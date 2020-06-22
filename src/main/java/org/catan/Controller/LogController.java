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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/** Logs all events that happen in the game, so players get notified of those events.
 * @author Jeroen
 */

public class LogController implements Initializable, Observable {
    @FXML private VBox logsBox;
    @FXML private ScrollPane scrollPane;

    private Logs logs = new Logs();
    private static String player;
    private static String opponent;

    private static LogController logController;


    public LogController() {
        player = App.getClientPlayer().getName();
    }

    /**
     * Function inherited by the Initializable interface, used to setup things in the view of the controller, when the view
     * is being made
     * @author Werner
     * @param url
     * @param resourceBundle
     */
    @Override // Scroll to bottom with each update
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logsBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));
        logController = this;
    }

    /**
     * Function to add a new LogPane object to the box that contains the list of logs
     * @author Werner
     * @param logPane
     */
    private void addToLogsBox(LogPane logPane) { // Add a new LogPane to the LogsBox
        logsBox.getChildren().add(logPane.getLogGrid()); }

    /**
     * Function that adds images to a LogPane object, should it need them
     * @author Werner
     * @param logPane
     * @param imagePaths
     */
    private void addImagesToLogPane(LogPane logPane, ArrayList<String> imagePaths) {
        for (int i = 0; i < imagePaths.size(); i++) {
            Image image = new Image(imagePaths.get(i));
            logPane.addImage(image);
        }
    }

    /**
     * Function to add a text only LogPane to the logsBox
     * @author Werner
     * @param log
     */
    private void addTxtLogToLogsPane(Log log) { // Add a new log to a new LogPane for text events
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
    }

    /**
     * Function to add a LogPane to the logsBox that has images
     * @author Werner
     * @param log
     */
    private void addImgLogToLogsPane(Log log) { // Add a new log to a new LogPane for image events
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
        addImagesToLogPane(logPane, log.getImgPaths());
    }

    /**
     * Add a Roll event log to the logs box and store it
     * @author Werner
     * @param dice1 The 1st dice roll result
     * @param dice2 The 2nd dice roll result
     */
    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player);
        log.addImgPath(dice1);
        log.addImgPath(dice2);
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player received resources event log to the logs box and store it
     * @author Werner
     * @param receivedCards
     */
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

    /**
     * Add a Player stole from other player event log to the logs box and store it
     * @author Werner
     * @param opponent
     */
    public void logStealEvent(Player opponent) {
        Log log = new Log("steal", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player traded with other player event log to the logs box and store it
     * @author Werner
     * @param opponent
     */
    public void logTradeEvent(Player opponent) {
        Log log = new Log("trade", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a player starts their turn event log to the logs box and store it
     * @author Werner
     */
    public void logStartTurnEvent() {
        Log log = new Log("startturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player ends their turn event log to the logs box and store it
     * @author Werner
     */
    public void logEndTurnEvent() {
        Log log = new Log("endturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player upgrades a settlement event log to the logs box and store it
     * @author Werner
     */
    public void logUpgradeEvent() {
        Log log = new Log("upgrade", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player builds a Road event log to the logs box and store it
     * @author Werner
     */
    public void logRoadEvent() {
        Log log = new Log("road", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player builds a settlement log to the logs box and store it
     * @author Werner
     */
    public void logSettlementEvent() {
        Log log = new Log("settlement", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player moves the Robber event log to the logs box and store it
     * @author Werner
     */
    public void logRobberEvent() {
        Log log = new Log("robber", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player wins the game event log to the logs box and store it
     * @author Werner
     */
    public void logWinEvent() {
        Log log = new Log("win", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Roll Player gets a victory point log to the logs box and store it
     * @author Werner
     */
    public void logPointEvent() {
        Log log = new Log("point", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player uses a Knight event log to the logs box and store it
     * @author Werner
     */
    public void logKnightEvent() {
        Log log = new Log("knight", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Add a Player uses a DevelopmentCard event log to the logs box and store it
     * @author Werner
     */
    public void logDevelopmentCardEvent() {
        Log log = new Log("development", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    /**
     * Function that stores a new Log in the logs list, and calls the db to save the log
     * @author Werner
     * @param log
     */
    private void storeLog(Log log) {
        logs.addLog(log);
        App.getCurrentGame().setLogs(logs.getLogs());
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame()); // Meaning that methods that create logs don't have to update the database within that method
    }

    /**
     * Function to get the instance of the LogController object, to ensure that there is always only one instance of the object
     * @return logController
     */
    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }

    /**
     * Function to set the player name of the player that belongs to the running client, so that their logs get the
     * proper name
     * @author Werner
     */
    public static void setPlayer(){
        player = App.getCurrentGame().turnPlayerGetter().getName();
    }

    public static void setOpponent(Player opponentPlayer){
        opponent = opponentPlayer.getName();
        //TODO Opponent moet bij alle acties waar de opponent in voorkomt aangegeven worden met behulp van deze methode
    }

    /**
     * Update function that is inherited from Observable interface, this function should contain all functionality
     * Regarding things that must be done when the game document is update in Firebase
     * @author Werner
     * @param game
     * @throws IOException
     */
    @Override
    public void update(Game game) {
        int oldLogsSize = logs.getLogs().size();
        if (game.getLogs().size() > logs.getLogs().size()) {
            for (int i = logs.getLogs().size(); i < game.getLogs().size(); i++) {
                if (game.getLogs().get(i).getLogType().equals("txt")) {
                    addTxtLogToLogsPane(game.getLogs().get(i));
                }
                if (game.getLogs().get(i).getLogType().equals("img")) {
                    System.out.println(game.getLogs().get(i).getEventType());
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
}
