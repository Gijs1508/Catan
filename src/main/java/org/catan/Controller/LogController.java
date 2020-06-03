package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Speler;
import org.catan.View.panes.LogPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LogController implements Initializable{

    @FXML private VBox logsBox;
    @FXML private ScrollPane scrollPane;

    private Logs logs = new Logs();
    private Speler player = new Speler("Jeroen");    // placeholders
    private Speler opponent = new Speler("Jan");

    private static LogController logController;


    public LogController() {
        logController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logsBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));   // Scroll to bottom with each update
    }

    private void addToLogsBox(LogPane logPane) {
        logsBox.getChildren().add(logPane.getLogGrid());
    }

    private void addImagesToLogPane(LogPane logPane, ArrayList<Image> images) {
        for (int i = 0; i < images.size(); i++) {
            logPane.addImage(images.get(i));
        }
    }

    private void addTxtLogToLogsPane(Log log) {
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
    }

    private void addImgLogToLogsPane(Log log) {
        LogPane logPane = new LogPane(log);
        addToLogsBox(logPane);
        addImagesToLogPane(logPane, log.getImages());
    }

    public void update() {
    }


    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player.getNaam());
        log.createImage(dice1);
        log.createImage(dice2);
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    public void logReceiveEvent(ArrayList<String> receivedCards) {
        Log log = new Log("receive", player.getNaam());
        for (int i = 0; i < receivedCards.size(); i++) {
            log.createImage(receivedCards.get(i));
        }
        addImgLogToLogsPane(log);
        storeLog(log);
    }

    public void logStealEvent(Speler opponent) {
        Log log = new Log("steal", player.getNaam(), opponent.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logTradeEvent(Speler opponent) {
        Log log = new Log("trade", player.getNaam(), opponent.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logEndTurnEvent() {
        Log log = new Log("endturn", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logUpgradeEvent() {
        Log log = new Log("upgrade", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logRoadEvent() {
        Log log = new Log("road", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logSettlementEvent() {
        Log log = new Log("settlement", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logRobberEvent() {
        Log log = new Log("robber", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logWinEvent() {
        Log log = new Log("win", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logPointEvent() {
        Log log = new Log("point", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }

    public void logKnightEvent() {
        Log log = new Log("knight", player.getNaam());
        addTxtLogToLogsPane(log);
        storeLog(log);
    }


    private void storeLog(Log log) {
        logs.addLog(log);
    }

    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }
}
