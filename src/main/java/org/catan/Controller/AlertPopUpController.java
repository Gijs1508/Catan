package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/** Popup appears as an alert to notify the player of an issue that occurred.
 * @author Jeroen */

public class AlertPopUpController {
    @FXML private Label alertDescription;
    @FXML private Text popupTitle;

    private ScreenController screenController = ScreenController.getInstance();
    private static AlertPopUpController alertPopUpController;
    private Object controller = screenController;

    public AlertPopUpController() {
        alertPopUpController = this;
    }

    // Checks which hideAlertPopup should be called
    public void closePopup() {
        if(controller.toString().equals(MainController.getInstance().getClass().toString())){
            MainController.getInstance().hideAlertPopup();
        }
        else if(controller.toString().equals(LobbySchermController.getInstance().getClass().toString())){
            LobbySchermController.getInstance().hideAlertPopup();
        } else {
            screenController.hideAlertPopup();
        }
    }

    public void setAlertTitle(String title){
        popupTitle.setText(title);
    }

    public void setAlertDescription(String description) {
        alertDescription.setText(description);
        if(description.length() < 30) { // If description's length is smaller than 30, make font size bigger
            alertDescription.setFont(Font.font(20)); }
        else {
            alertDescription.setFont(Font.font(15)); }
    }

    public void setAlertPlacedController(Object controllerClass){
        this.controller = controllerClass;
    }

    public static AlertPopUpController getInstance() {
        return alertPopUpController;
    }
}
