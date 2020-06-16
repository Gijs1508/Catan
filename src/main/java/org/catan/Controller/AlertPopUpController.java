package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/** Popup appears as an alert to notify the player of an issue that occurred.
 * @author Jeroen */

public class AlertPopUpController {
    @FXML private Label alertDescription;
    private ScreenController screenController = ScreenController.getInstance();
    private static AlertPopUpController alertPopUpController;

    public AlertPopUpController() {
        alertPopUpController = this;
    }

    public void closePopup() {
        screenController.hideAlertPopup();
    }

    public void setAlertDescription(String description) {
        alertDescription.setText(description);
    }

    public static AlertPopUpController getInstance() {
        return alertPopUpController;
    }
}
