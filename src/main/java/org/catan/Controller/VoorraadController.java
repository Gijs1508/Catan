package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class VoorraadController implements Initializable {

    @FXML private ImageView knightCard;
    @FXML private Pane animationPane;
    @FXML private ImageView animationKnightCard;

    LogController logController = LogController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animationPane.setVisible(true);
    }

    @FXML
    public void activateKnight() {
        logController.logKnightEvent();


    }

    @FXML
    public void showKnightDetails() {
        ScreenController.getInstance().showKnightPopup();
    }

    @FXML
    public void hideKnightDetails() {
        ScreenController.getInstance().hideKnightPopup();
    }
}
