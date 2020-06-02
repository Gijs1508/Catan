package org.catan.Controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Speler;
import org.catan.View.panes.LogPane;
import org.catan.View.panes.LogsPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LogController{

    private LogsPane logsView = new LogsPane();
    private AnchorPane logsRoot;
    private Logs logsModel = new Logs();

    private ArrayList<GridPane> logGrids = new ArrayList<>();

    private Speler player = new Speler("Jeroen");   // Placeholder


    public void logRollEvent(String dice1, String dice2) {


        Log log = new Log("img", "roll");
//        log.createImage(dice1);
//        log.createImage(dice2);
//        logsModel.addLog(log);

//        LogPane logPane = new LogPane(log);
//        logsView.getLogsContainer().getChildren().add(logPane.getLogGrid());
//        logsView.getLogsContainer().getChildren().add(new Text("eedfsfsdf"));
        LogPane logPane = logsView.createLog(log);
        logGrids.add(logPane.getLogGrid());
        logsView.updateLogs(logGrids);




//        LogPane logPane = logsView.createLog(log);

//        logPane.addImage(log.getImage(0));
//        logPane.addImage(log.getImage(1));

        // Should work, but pane isn't added to logspane.


//        logsRoot = logsView.getRoot();
    }

    public void logReceiveEvent() {

    }

    public void logStealEvent() {

    }

    public void logStartTurn() {

    }

    public void logEndTurn() {
        Log log = new Log("txt", "endturn");

        LogPane logPane = logsView.createLog(log);
    }

    public void logUpgradeEvent() {

    }

    public void logRoadEvent() {

    }

    public void logTradeEvent() {

    }

    public void logWinEvent() {

    }

    public void logRobberEvent() {

    }

    public void logSettlementEvent() {

    }

    public void logPointEvent() {

    }

    public void logKnightEvent() {

    }


    public LogController() {

    }

    public void update() {

    }

}
