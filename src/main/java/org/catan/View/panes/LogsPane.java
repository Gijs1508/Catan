package org.catan.View.panes;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.catan.Controller.LogController;

/**
 * Class that creates the view/pane for event logging.
 * LogPanes can be added to the LogsPane.
 *
 * @author Jeroen
 * @version 0.4
 */

public class LogsPane {

    private AnchorPane root = new AnchorPane();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox logs = new VBox();

    private LogController logController;

    public LogsPane(){
        root.setPrefWidth(430);
        root.setPrefHeight(144);
        root.setStyle("-fx-background-color: #a1aaa0;");

        scrollPane.setPrefHeight(144);
        scrollPane.setPrefWidth(430);

        logs.prefWidth(430);
        logs.prefHeight(144);
        logs.setPadding(new Insets(10));

        scrollPane.setContent(logs);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        createLog("img");

        root.getChildren().add(scrollPane);
    }

    public LogPane createLog(String type) {
        LogPane log = new LogPane(type);
        logs.getChildren().add(log.getLogGrid());
        return log;
    }

    public VBox getlogsContainer(){
        return logs;
    }

    public AnchorPane getRoot(){
        return root;
    }

}
