package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/** Popup appears as an alert to notify the player of an issue that occurred.
 * @author Jeroen */

public class AlertPopUpController {
    @FXML private Label alertDescription;
    @FXML private Text popupTitle;

    private ScreenController screenController = ScreenController.getInstance();
    private LobbySchermController lobbySchermController = LobbySchermController.getInstance();
    private static AlertPopUpController alertPopUpController;
    private Object controller = screenController;

    public AlertPopUpController() {
        alertPopUpController = this;
    }

    public void closePopup() {
//        switch(controller.getClass()){
//            case ScreenController.getInstance().getClass().toString(): screenController.hideAlertPopup(); break;
//            case LobbySchermController.getInstance().getClass().toString(): lobbySchermController.hideAlertPopup(); break;
//        }

        System.out.println(controller.getClass().toString());

        if(controller.getClass().toString().equals(lobbySchermController.getClass().toString())){
            lobbySchermController.hideAlertPopup();
        } else {
            screenController.hideAlertPopup();
        }
    }

    public void setAlertTitle(String title){
        popupTitle.setText(title);
    }

    public void setAlertDescription(String description) {
        alertDescription.setText(description);
    }

    public void setAlertPlacedController(Class controllerClass){
        System.out.println(controllerClass.getName());
        controller = controllerClass;
    }



    public static AlertPopUpController getInstance() {
        return alertPopUpController;
    }
}
