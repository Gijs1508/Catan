package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.catan.Model.Game;
import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Player;
import org.catan.View.panes.LogPane;
import org.catan.interfaces.Observable;

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
        logController = this; }

    @Override // Scroll to bottom with each update
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logsBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D)); }

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

    public void update() {
    }


    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player);
        log.addImgPath(dice1);
        log.addImgPath(dice2);
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    public void logReceiveEvent(ArrayList<String> receivedCards) {
        Log log = new Log("receive", player);
        for (int i = 0; i < receivedCards.size(); i++) {
            log.addImgPath(receivedCards.get(i));
        }
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    public void logStealEvent(Player opponent) {
        Log log = new Log("steal", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logTradeEvent(Player opponent) {
        Log log = new Log("trade", player, opponent.getName());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logStartTurnEvent() {
        Log log = new Log("startturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logEndTurnEvent() {
        Log log = new Log("endturn", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logUpgradeEvent() {
        Log log = new Log("upgrade", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logRoadEvent() {
        Log log = new Log("road", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logSettlementEvent() {
        Log log = new Log("settlement", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logRobberEvent() {
        Log log = new Log("robber", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logWinEvent() {
        Log log = new Log("win", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logPointEvent() {
        Log log = new Log("point", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logKnightEvent() {
        Log log = new Log("knight", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logDevelopmentCardEvent() {
        Log log = new Log("development", player);
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    // Stores log in logs
    private void storeLog(Log log) {
        logs.addLog(log);
    }

    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }

    public static void setPlayer(){
        player = Player.getActivePlayer().getName();
    }

    public static void setOpponent(Player opponentPlayer){
        opponent = opponentPlayer.getName();
        //TODO Opponent moet bij alle acties waar de opponent in voorkomt aangegeven worden met behulp van deze methode
    }

    @Override
    public void update(Game game) {

    }
}
