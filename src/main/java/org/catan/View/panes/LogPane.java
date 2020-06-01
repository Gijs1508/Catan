package org.catan.View.panes;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.catan.App;


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

    private GridPane logGrid = new GridPane();
    private Text eventText = new Text();
    private HBox imgBox = new HBox();


    public LogPane(String type){
        if(type == "img"){
            initImgEvent();
            return; }
        initTxtEvent();
    }

    public void initLog(){
        logGrid.prefHeight(25);
        logGrid.prefWidth(430);

        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setMinHeight(10);

        logGrid.getRowConstraints().add(row);
    }

    public void initImgEvent(){
        initLog();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(10);
        column1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMinWidth(10);
        column2.setPrefWidth(84);
        logGrid.getColumnConstraints().addAll(column1, column2);

//        logGrid.setGridLinesVisible(true); //later weg

        eventText.setWrappingWidth(0);
        eventText.setFont(Font.font(13));
        logGrid.setMargin(eventText, new Insets(0, 15, 0, 0));

        eventText.setText("eee");

        imgBox.prefHeight(100);
//        imgBox.prefWidth(200);
        imgBox.setSpacing(2);


//        logGrid.getChildren().addAll(eventText, imgBox);
        logGrid.add(eventText, 0, 0);
        logGrid.add(imgBox, 1, 0);
    }

    public void initTxtEvent(){
        initLog();

        eventText.setWrappingWidth(430);
        eventText.setFont(Font.font(13));

        eventText.setText("Jeroen traded a penis pango bangle for a bingle bangle with mariano.");
        logGrid.getChildren().addAll(eventText);
    }

    public GridPane getLogGrid() {
        return logGrid;
    }

    public void setEventText(String text) {
        eventText.setText(text);
    }

    public void addImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(27);
        imgBox.getChildren().addAll(imageView);
    }
}
