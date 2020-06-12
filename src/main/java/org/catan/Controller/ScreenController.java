package org.catan.Controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.View.popups.KnightDetails;
import org.catan.interfaces.Observable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ScreenController implements Initializable, Observable {

    private AnchorPane boardView;
    private AnchorPane stockView;
    private AnchorPane logView;
    private AnchorPane chatView;
    private AnchorPane tradeView;
    private AnchorPane diceView;
    private AnchorPane scoreView;
    private AnchorPane costView;
    private AnchorPane settingsView;
    private AnchorPane knightDetails;
    private AnchorPane tradePopupView;
    private AnchorPane handInPopupView;
    private AnchorPane devCardPopupView;
    private AnchorPane alertPopupView;

    @FXML private AnchorPane root;
    @FXML private Pane boardPane;
    @FXML private Pane stockPane;
    @FXML private Pane scorePane;
    @FXML private Pane chatPane;
    @FXML private Pane logPane;
    @FXML private Pane tradePane;
    @FXML private Pane dicePane;
    @FXML private Pane costPane;
    @FXML private Pane settingsPane;
    @FXML private Pane knightPopup;
    @FXML private Pane tradePopup;
    @FXML private Pane handInPopup;
    @FXML private Pane devCardPopup;
    @FXML private Pane alertPopup;


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
            tradeView = (AnchorPane) App.loadFXML("Views/tradeView");
            chatView = (AnchorPane) App.loadFXML("Views/chatView");
            diceView = (AnchorPane) App.loadFXML("Views/diceView");
            scoreView = (AnchorPane) App.loadFXML("Views/scoreView");
            costView = (AnchorPane) App.loadFXML("Views/costView");
            settingsView = (AnchorPane) App.loadFXML("Views/settingsView");
            devCardPopupView = (AnchorPane) App.loadFXML("Views/devCardPopUpView");
            alertPopupView = (AnchorPane) App.loadFXML("Views/alertPopUpView");
            knightDetails = new KnightDetails().getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logPane.getChildren().setAll(logView);
        boardPane.getChildren().setAll(boardView);
        tradePane.getChildren().setAll(tradeView);
        stockPane.getChildren().setAll(stockView);
        chatPane.getChildren().setAll(chatView);
        dicePane.getChildren().setAll(diceView);
        scorePane.getChildren().setAll(scoreView);
        costPane.getChildren().setAll(costView);
        settingsPane.getChildren().setAll(settingsView);
        knightPopup.getChildren().setAll(knightDetails);
        devCardPopup.getChildren().setAll(devCardPopupView);
        alertPopup.getChildren().setAll(alertPopupView);

        initializePopup(settingsPane);
        initializePopup(knightPopup);
        initializePopup(tradePopup);
        initializePopup(handInPopup);
        initializePopup(devCardPopup);
        initializePopup(alertPopup);
    }

    public void showSettings() {
        settingsPane.setVisible(true);
        SettingsController.getInstance().startAnimation();
    }
    public void hideSettings() {
        settingsPane.setVisible(false);
    }

    public void showKnightPopup() {
        KnightDetails.getFadeIn().playFromStart();
        knightPopup.setVisible(true);
    }
    public void hideKnightPopup() {
        KnightDetails.getFadeOut().playFromStart();
        KnightDetails.getFadeOut().setOnFinished(actionEvent -> knightPopup.setVisible(false));
    }

    public void showHandInPopUp() throws IOException {
        handInPopupView = (AnchorPane) App.loadFXML("Views/handInPopUpView");
        handInPopup.getChildren().setAll(handInPopupView);
        handInPopup.setVisible(true);
    }
    public void hideHandInPopUp() {
        handInPopup.setVisible(false);
    }

    public void showTradePopup() throws IOException {
        tradePopupView = (AnchorPane) App.loadFXML("Views/tradePopUpView");
        tradePopup.getChildren().setAll(tradePopupView);
        tradePopup.setVisible(true);
    }
    public void hideTradePopup() {
        tradePopup.setVisible(false);
    }

    public void showDevCardPopup() {
        devCardPopup.setVisible(true);
    }
    public void hideDevCardPopup() {
        devCardPopup.setVisible(false);
    }

    public void showAlertPopup() {
        alertPopup.setVisible(true);
    }
    public void hideAlertPopup() {
        alertPopup.setVisible(false);
    }

    private void initializePopup(Pane popupPane) {
        popupPane.setVisible(false);
        popupPane.setOpacity(1);
        popupPane.setStyle("-fx-background-color: none;");
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

    @Override
    public void update(Game game) {

    }
}
