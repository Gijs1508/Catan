package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.catan.Model.Sound;

/**
 * Popup appears as an alert to notify the player of an issue that occurred.
 *
 * @author Jeroen
 */

public class AlertPopUpController {
    @FXML private Label alertDescription;
    @FXML private Text popupTitle;

    private ScreenController screenController = ScreenController.getInstance();
    private static AlertPopUpController alertPopUpController;
    private Object controller = screenController;

    public AlertPopUpController() {
        alertPopUpController = this;
    }

    /** Checks on which controller the popup is active and closes it */
    public void closePopup() {
        Sound.playClick();
        if(controller.toString().equals(MainController.getInstance().getClass().toString())){
            MainController.getInstance().hideAlertPopup(); // Popup is active in the main (main menu) controller
        }
        else if(controller.toString().equals(LobbyController.getInstance().getClass().toString())){
            LobbyController.getInstance().hideAlertPopup(); // Popup is active in the lobby controller
        } else {
            screenController.hideAlertPopup(); // Popup is active in the screen (in-game) controller
        }
    }

    public void setAlertTitle(String title){
        popupTitle.setText(title);
    }

    /** Updates the alert pop-up's description text to describe the current issue.
     * @param description description to update the alert pop-up with */
    public void setAlertDescription(String description) {
        alertDescription.setText(description);
        if(description.length() < 30) { // If description's length is smaller than 30, make font size bigger
            alertDescription.setFont(Font.font(20)); }
        else {
            alertDescription.setFont(Font.font(15)); }
    }

    /** Assigns another controller to this controller, so alerts can be interacted with over different controllers.
     * @param controllerClass the controller the alert is in
     * @author Gijs */
    public void setAlertPlacedController(Object controllerClass){
        this.controller = controllerClass;
    }

    public static AlertPopUpController getInstance() {
        return alertPopUpController;
    }
}
