package org.catan.View.panes;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.catan.Controller.LogController;
import org.catan.Model.Log;

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
    private ScrollPane scrollPane = new ScrollPane();
    private VBox logs;


    public LogsPane(){
        logs = new VBox();
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

//        logs.getChildren().add(new Text("d")); //AAAAAAAAAAAAAA
//        addChild();
    }

    public LogPane createLog(Log log) {
        LogPane logPane = new LogPane(log);
        logPane.setEventText(log.getEventString());
//        logs.getChildren().add(logPane.getLogGrid());
        logs.getChildren().add(new Text("b"));
        addChild();

        return logPane;
    }

    private void addChild() {
        this.logs.getChildren().add(new Text("HALLO??"));
    }

    public VBox getLogsContainer(){
        return logs;
    }

    public AnchorPane getRoot(){
        return root;
    }
}
