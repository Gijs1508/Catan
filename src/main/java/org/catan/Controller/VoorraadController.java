package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class VoorraadController {

    @FXML private ImageView knightCard;

    LogController logController = LogController.getInstance();

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
