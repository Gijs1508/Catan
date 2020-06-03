package org.catan.View.panes;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.catan.Controller.LogController;
import org.catan.Model.Log;

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
    private ScrollPane scrollPane = new ScrollPane();

    private LogController logController;

    public LogsPane(){
        logController = LogController.getInstance();

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

        root.getChildren().add(scrollPane);
    }
}
