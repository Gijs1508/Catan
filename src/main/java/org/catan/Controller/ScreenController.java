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

    private AnchorPane boardView;
    private AnchorPane stockView;
    private AnchorPane logView;
    private AnchorPane chatView;
    private AnchorPane tradeView;
    private AnchorPane diceView;
    private AnchorPane scoreView;

    @FXML Pane boardPane;
    @FXML Pane stockPane;
    @FXML Pane scorePane;
    @FXML Pane chatPane;
    @FXML Pane logPane;
    @FXML Pane tradePane;
    @FXML Pane dicePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            boardView = (AnchorPane) App.loadFXML("Views/boardView");
            stockView = (AnchorPane) App.loadFXML("Views/stockView");
            logView = (AnchorPane) App.loadFXML("Views/logView");
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
            diceView = (AnchorPane) App.loadFXML("Views/diceView");
            scoreView = (AnchorPane) App.loadFXML("Views/scoreView");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boardPane.getChildren().setAll(boardView);
        stockPane.getChildren().setAll(stockView);
        logPane.getChildren().setAll(logView);
        chatPane.getChildren().setAll(chatView);
        tradePane.getChildren().setAll(tradeView);
        dicePane.getChildren().setAll(diceView);
        scorePane.getChildren().setAll(scoreView);
    }


}
