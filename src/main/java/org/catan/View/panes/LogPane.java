package org.catan.View.panes;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

    private GridPane log = new GridPane();
    private Text event = new Text();
    private HBox imgBox = new HBox();

    public LogPane(String type){
        if(type == "img"){
            initImgEvent();
            return; }
        initTxtEvent();
    }

    public void initLog(){
        log.prefHeight(25);
        log.prefWidth(200);

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

        log.getRowConstraints().add(row);
        log.getColumnConstraints().addAll(column1, column2);

    }

    public void initImgEvent(){
        initLog();

        event.setWrappingWidth(115);
        event.setFont(Font.font(13));

        imgBox.prefHeight(100);
        imgBox.prefWidth(200);
        imgBox.setSpacing(5);

        event.setText("This is an image event:");
        log.getChildren().addAll(event, imgBox);
    }

    public void initTxtEvent(){
        initLog();

        event.setWrappingWidth(190);
        event.setFont(Font.font(13));

        event.setText("This is a text event.");
        log.getChildren().addAll(event);
    }

    public GridPane getLog() {
        return log;
    }
}
