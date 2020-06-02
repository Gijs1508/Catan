package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.catan.App;
import org.catan.View.panes.LogsPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {

    private static ScreenController screenController;

    private AnchorPane boardView;
    private AnchorPane stockView;
    private AnchorPane logView;
    private AnchorPane chatView;
    private AnchorPane tradeView;
    private AnchorPane diceView;
    private AnchorPane scoreView;
    private AnchorPane costView;

    @FXML private Pane boardPane;
    @FXML private Pane stockPane;
    @FXML private Pane scorePane;
    @FXML private Pane chatPane;
    @FXML private Pane logPane;
    @FXML private Pane tradePane;
    @FXML private Pane dicePane;
    @FXML private Pane costPane;

    public ScreenController() {
        screenController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            boardView = (AnchorPane) App.loadFXML("Views/boardView");
            stockView = (AnchorPane) App.loadFXML("Views/stockView");
            logView = new LogsPane().getRoot();
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
            diceView = (AnchorPane) App.loadFXML("Views/diceView");
            scoreView = (AnchorPane) App.loadFXML("Views/scoreView");
            costView = (AnchorPane) App.loadFXML("Views/costView");
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
        costPane.getChildren().setAll(costView);
    }

    public AnchorPane getLogView() {
        return logView;
    }

    public static ScreenController getInstance() {
        return screenController;
    }
}
