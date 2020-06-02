package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Speler;
import org.catan.View.panes.LogPane;
import org.catan.View.panes.LogsPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LogController implements Initializable {

    private Logs logs = new Logs();
    private ScreenController screenController;
    private AnchorPane logView;
    private VBox logsBox;
    private LogsPane logsPane;


    Speler player = new Speler("Jeroen");    // placeholder

    private static LogController logController;

    public LogController() {
        screenController = ScreenController.getInstance();
        logView = screenController.getLogView();
        logsBox = (VBox) logView.lookup("#logsBox");

        logController = this;
    }

    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hoi");
    }

    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player.getNaam());
        LogPane logPane = new LogPane(log);
        logsPane.addLog(logPane);
//        logsPane.addLog(logPane);
//        logsBox.getChildren().add(logPane.getLogPane());
//        System.out.println(this.logsBox.getLayoutX());
//        logsBox.getChildren().add(new Text("eaeaw"));
//        logs.addLog(log);
    }

    public void update() {

    }
}
