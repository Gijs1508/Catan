package org.catan.Controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.catan.App;
import org.catan.View.popups.KnightDetails;

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
    private AnchorPane costView;
    private AnchorPane knightDetails;

    @FXML private Pane boardPane;
    @FXML private Pane stockPane;
    @FXML private Pane scorePane;
    @FXML private Pane chatPane;
    @FXML private Pane logPane;
    @FXML private Pane tradePane;
    @FXML private Pane dicePane;
    @FXML private Pane costPane;
    @FXML private Pane knightPopup;

    private static ScreenController screenController;

    public ScreenController() {
        screenController = this;
    }

    // The order in which the views are loaded is important if you need their controllers to communicate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            logView = (AnchorPane) App.loadFXML("Views/logsView");
            boardView = (AnchorPane) App.loadFXML("Views/boardView");
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
            diceView = (AnchorPane) App.loadFXML("Views/diceView");
            scoreView = (AnchorPane) App.loadFXML("Views/scoreView");
            costView = (AnchorPane) App.loadFXML("Views/costView");
            stockView = (AnchorPane) App.loadFXML("Views/stockView");
            knightDetails = new KnightDetails().getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logPane.getChildren().setAll(logView);
        boardPane.getChildren().setAll(boardView);
        chatPane.getChildren().setAll(chatView);
        tradePane.getChildren().setAll(tradeView);
        dicePane.getChildren().setAll(diceView);
        scorePane.getChildren().setAll(scoreView);
        costPane.getChildren().setAll(costView);
        stockPane.getChildren().setAll(stockView);
        knightPopup.getChildren().setAll(knightDetails);
        knightPopup.setVisible(false);
    }

    public void hideKnightPopup() {
        KnightDetails.getFadeOut().playFromStart();
//        knightPopup.setVisible(false);
    }

    public void showKnightPopup() {
        KnightDetails.getFadeIn().playFromStart();
        knightPopup.setVisible(true);
    }

    public static ScreenController getInstance() {
        return screenController;
    }
}
