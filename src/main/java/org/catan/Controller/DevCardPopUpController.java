package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.catan.App;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the popup that shows when a development card is bought.
 *
 * @author Jeroen
 */

public class DevCardPopUpController {
    @FXML private ImageView cardImg;
    @FXML private AnchorPane root;
    private final String KNIGHT_IMG = String.valueOf(App.class.getResource("assets/img/knightTitle.png"));
    private final String POINT_IMG = String.valueOf(App.class.getResource("assets/img/victoryPoint.png"));
    private boolean animationIsPlaying;
    private AnimationTimer animation;
    private static DevCardPopUpController devCardPopUpController;

    public DevCardPopUpController() {
        devCardPopUpController = this;
    }

    /** Plays the animation that makes the popup appear on the screen.
     * It slides down from the top, stops at the center and then fades out. */
    public void playAnimation() {
        // If animation is already playing, overwrite the current animation with a quick animation that simply fades out.
        if(animationIsPlaying) {
            animation.stop();
            resetPopup();
            animation = new AnimationTimer() {
                int tick;
                @Override
                public void handle(long l) {
                    tick++;
                    if(tick > 80) {
                        root.setOpacity(root.getOpacity() - 0.02);
                    }
                    if(root.getOpacity() < 0) {
                        animationIsPlaying = false;
                        resetPopup();
                        ScreenController.getInstance().hideDevCardPopup();
                        this.stop();
                    }
                }
            };
            animation.start();
            return;
        }
        // If animation isn't playing, play the default animation.
        animationIsPlaying = true;
        root.setLayoutY(-400);
        animation = new AnimationTimer() {
            int tick;
            @Override
            public void handle(long l) {
                if(root.getLayoutY() < 0) {
                    root.setLayoutY(root.getLayoutY() + 3);
                }
                else {
                    tick++;
                    if(tick > 80) {
                        root.setOpacity(root.getOpacity() - 0.02);
                        if(root.getOpacity() < 0) {
                            animationIsPlaying = false;
                            resetPopup();
                            ScreenController.getInstance().hideDevCardPopup();
                            this.stop();
                        }
                    }
                }
            }
        };
        animation.start();
    }

    private void resetPopup() {
        root.setOpacity(1);
        root.setLayoutY(0);
    }

    public void setKnightImage() {
        cardImg.setImage(new Image(KNIGHT_IMG));
    }

    public void setPointImage() {
        cardImg.setImage(new Image(POINT_IMG));
    }

    public static DevCardPopUpController getInstance() {
        return devCardPopUpController;
    }
}
