package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class RulesPopUpController implements Initializable {

    @FXML private Pane background;

    private static RulesPopUpController rulesPopUpController;

    public RulesPopUpController() {
        rulesPopUpController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rulesPopUpController = this;
    }

    public static RulesPopUpController getInstance() {
        return rulesPopUpController;
    }

    public void closeBtnClicked() {
        ScreenController.getInstance().hideRulesPopUp();
    }

    // Rules fade in when opened
    public void startAnimation() {
        background.setOpacity(0);
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(background.getOpacity() < 1) {
                    background.setOpacity(background.getOpacity() + 0.02);
                }
                else {
                    this.stop();
                }
            }
        };
        animation.start();
    }
}
