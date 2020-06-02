package org.catan.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

public class LogController {

    @FXML private VBox logsBox;
    @FXML private ScrollPane scrollPane;

    Speler player = new Speler("Jeroen");    // placeholder

    private static LogController logController;

    public LogController() {
        logController = this;

    }

    public static LogController getInstance() {
        if (logController == null){
            logController = new LogController();
        }
        return logController;
    }

    private void addToLogsBox(LogPane logPane) {
        logsBox.getChildren().add(logPane.getLogGrid());
    }
    public void logRollEvent(String dice1, String dice2) {
        Log log = new Log("roll", player.getNaam());
        log.createImage(dice1);
        log.createImage(dice2);

        LogPane logPane = new LogPane(log);
        addImagesToLogPane(logPane, log.getImages());
        addToLogsBox(logPane);
    }

    private void addImagesToLogPane(LogPane logPane, ArrayList<Image> images) {
        for (int i = 0; i < images.size(); i++) {
            logPane.addImage(images.get(i));
        }
    }

    public void update() {

    }
}
