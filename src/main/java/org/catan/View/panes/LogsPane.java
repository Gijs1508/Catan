package org.catan.View.panes;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.catan.Controller.LogController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that creates the view/pane for event logging.
 * LogPanes can be added to the LogsPane.
 *
 * @author Jeroen
 * @version 0.4
 */

public class LogsPane {
    private AnchorPane root = new AnchorPane();
    private VBox logs = new VBox();

    private LogController logController;

    public LogsPane(){
        root.setPrefWidth(200);
        root.setPrefHeight(190);
        root.setStyle("-fx-background-color: #a1aaa0;");

        logs.prefWidth(200);
        logs.prefHeight(190);
        logs.setPadding(new Insets(10));

        addLog("txt");
        addLog("img");

        root.getChildren().add(logs);
    }

    public void addLog(String type) {
        LogPane log = new LogPane(type);
        logs.getChildren().add(log.getLog());
    }

    public VBox getlogsContainer(){
        return logs;
    }

    public AnchorPane getRoot(){
        return root;
    }
}
