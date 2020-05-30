package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.catan.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {

    private AnchorPane screenView;
    private AnchorPane boardView;
    private AnchorPane stockView;
    private AnchorPane logView;
    private AnchorPane chatView;
    private AnchorPane tradeView;

//    @FXML Pane screenPane;
    @FXML Pane boardPane;
    @FXML Pane stockPane;
    @FXML Pane scorePane;
    @FXML Pane chatPane;
    @FXML Pane logPane;
    @FXML Pane tradePane;
    @FXML Pane dicePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
//            screenView = (AnchorPane) App.loadFXML("Views/screenView");
            boardView = (AnchorPane) App.loadFXML("Views/boardView");
            stockView = (AnchorPane) App.loadFXML("Views/stockView");
            logView = (AnchorPane) App.loadFXML("Views/logView");
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boardPane.getChildren().setAll(boardView);
        stockPane.getChildren().setAll(stockView);
        logPane.getChildren().setAll(logView);
        chatPane.getChildren().setAll(chatView);
        tradePane.getChildren().setAll(tradeView);

//        screenView.getChildren().setAll(boardView, stockView, logView, chatView);
//
//        Scene scene = new Scene(screenView);
//        App.getStage().setScene(scene);
//        System.out.println("dwdwadadwa");

    }


}
