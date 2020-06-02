package org.catan.View.panes;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.catan.Model.Log;

/**
 * Class that creates a pane for a single log.
 * There are two possible panes, decided by the event type.
 *
 * todo
 * find a way to make everything fit
 *
 * @author Jeroen
 * @version 0.1
 */

public class LogPane {

    private GridPane logPane = new GridPane();
    private Text event = new Text();
    private HBox imgBox = new HBox();

    public LogPane(Log log){
        if(log.getLogType() == "img"){
            initImgEvent();
            return; }
        initTxtEvent();
    }

    public void initLog(){
        logPane.prefHeight(25);
        logPane.prefWidth(200);

        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setMinHeight(10);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMaxWidth(164);
        column1.setMinWidth(10);
        column1.setPrefWidth(103);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMaxWidth(102);
        column2.setMinWidth(10);
        column2.setPrefWidth(84);

        logPane.getRowConstraints().add(row);
        logPane.getColumnConstraints().addAll(column1, column2);

    }

    public void initImgEvent(){
        initLog();

        event.setWrappingWidth(115);
        event.setFont(Font.font(13));

        imgBox.prefHeight(100);
        imgBox.prefWidth(200);
        imgBox.setSpacing(5);

        event.setText("This is an image event:");
        logPane.getChildren().addAll(event, imgBox);
    }

    public void initTxtEvent(){
        initLog();

        event.setWrappingWidth(190);
        event.setFont(Font.font(13));

        event.setText("This is a text event.");
        logPane.getChildren().addAll(event);
    }

    public GridPane getLogPane() {
        return logPane;
    }
}
