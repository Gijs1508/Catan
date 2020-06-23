package org.catan.View.panes;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Log;


/**
 * Class that creates a pane for a single log.
 * There are two possible panes, decided by the event type.
 *
 * @author Jeroen
 */

public class LogPane {

    private Log log;
    private GridPane logGrid = new GridPane();
    private Text eventText = new Text();
    private HBox imgBox = new HBox();


    public LogPane(Log log){
        this.log = log;
        if(log.getLogType().equals("img")){
            initImgEvent();
            return; }
        initTxtEvent();
    }

    /** Styling that both type of log events use */
    public void initLog(){
        eventText.setFont(Font.font(13));

        logGrid.prefHeight(25);
        logGrid.prefWidth(430);

        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setMinHeight(30);

        logGrid.setMargin(eventText, new Insets(0, 15, 0, 30));
        logGrid.getRowConstraints().add(row);

        eventText.setText(log.getEventString());
    }

    /** Sets a LogPane up for image events */
    public void initImgEvent(){
        initLog();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(30);
        column1.setPrefWidth(150);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMinWidth(10);
        column2.setPrefWidth(Region.USE_COMPUTED_SIZE);

        logGrid.getColumnConstraints().addAll(column1, column2);

        imgBox.prefHeight(100);
        imgBox.setSpacing(2);

        logGrid.add(eventText, 0, 0);
        logGrid.add(imgBox, 1, 0);
    }

    /** Sets a LogPane up for text events */
    public void initTxtEvent(){
        initLog();

        logGrid.getChildren().addAll(eventText);
    }

    /** Adds an image to the logPane
     * @param image image that's added to the log */
    public void addImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(27);
        imgBox.getChildren().addAll(imageView);
    }

    public GridPane getLogGrid() {
        return logGrid;
    }
}