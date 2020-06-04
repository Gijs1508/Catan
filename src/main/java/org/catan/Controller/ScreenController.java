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
import java.util.HashMap;
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
    private AnchorPane tradePopupView;

    @FXML private AnchorPane root;
    @FXML private Pane boardPane;
    @FXML private Pane stockPane;
    @FXML private Pane scorePane;
    @FXML private Pane chatPane;
    @FXML private Pane logPane;
    @FXML private Pane tradePane;
    @FXML private Pane dicePane;
    @FXML private Pane costPane;
    @FXML private Pane knightPopup;
    @FXML private Pane tradePopup;

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
            stockView = (AnchorPane) App.loadFXML("Views/stockView");
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
            diceView = (AnchorPane) App.loadFXML("Views/diceView");
            scoreView = (AnchorPane) App.loadFXML("Views/scoreView");
            costView = (AnchorPane) App.loadFXML("Views/costView");
            knightDetails = new KnightDetails().getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logPane.getChildren().setAll(logView);
        boardPane.getChildren().setAll(boardView);
        stockPane.getChildren().setAll(stockView);
        chatPane.getChildren().setAll(chatView);
        tradePane.getChildren().setAll(tradeView);
        dicePane.getChildren().setAll(diceView);
        scorePane.getChildren().setAll(scoreView);
        costPane.getChildren().setAll(costView);

        knightPopup.getChildren().setAll(knightDetails);
        knightPopup.setVisible(false);

        tradePopup.setVisible(false);
    }

    public void hideKnightPopup() {
        KnightDetails.getFadeOut().playFromStart();
//        knightPopup.setVisible(false);
    }

    public void showKnightPopup() {
        KnightDetails.getFadeIn().playFromStart();
        knightPopup.setVisible(true);
    }

    public void showTradePopup() throws IOException {
        tradePopupView = (AnchorPane) App.loadFXML("Views/tradePopUpView");
        tradePopup.getChildren().setAll(tradePopupView);
        tradePopup.setVisible(true);
    }

    public void hideTradePopup() {
        tradePopup.setVisible(false);
    }

    public HashMap<String, Double> getTradePopupLayout() {
        HashMap<String, Double> popupInfo = new HashMap<>() {{
            this.put("layoutX", tradePopup.getLayoutX());
            this.put("layoutY", tradePopup.getLayoutY());
        }};
        return popupInfo;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public static ScreenController getInstance() {
        return screenController;
    }
}
