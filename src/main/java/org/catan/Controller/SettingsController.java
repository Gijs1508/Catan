package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Model.Sound;

/**
 * Manages the settings window that allows the player to change their sound settings.
 *
 * @author Jeroen
 */

public class SettingsController {
    @FXML private Pane background;
    @FXML private ImageView musicImg;
    @FXML private ImageView soundsImg;

    private Image soundsOnImg = new Image(String.valueOf(App.class.getResource("assets/img/audioOn.png")));
    private Image soundsOffImg = new Image(String.valueOf(App.class.getResource("assets/img/audioOff.png")));
    private Image musicOnImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOn.png")));
    private Image musicOffImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOff.png")));

    private boolean musicOn = false;
    private boolean soundsOn = true;

    private static SettingsController settingsController;

    public SettingsController() {
        settingsController = this;
    }

    /** Player mutes/unmutes sound effects */
    public void soundsBtnClicked() {
        if (soundsOn) {
            Sound.playClick();
            Sound.muteSoundEffects();
            soundsImg.setImage(soundsOffImg);
            soundsOn = false;
        }
        else {
            Sound.unmuteSoundEffects();
            soundsImg.setImage(soundsOnImg);
            Sound.playClick();
            soundsOn = true;
        }
    }

    /** Player pressed the rules button */
    public void rulesBtnClicked() {
        ScreenController.getInstance().showRulesPopUp();
    }

    /** Player mutes/unmutes music */
    public void musicBtnClicked() {
        if (musicOn) {
            Sound.muteGameMusic();
            Sound.playClick();
            musicImg.setImage(musicOffImg);
            musicOn = false;
        }
        else {
            Sound.unmuteGameMusic();
            Sound.playClick();
            musicImg.setImage(musicOnImg);
            musicOn = true;
        }
    }

    /** Player leaves settings */
    public void applyBtnClicked() {
        Sound.playClick();
        ScreenController.getInstance().hideSettings();
    }

    /** Starts an animation for the settings.
     *  A dark background fades in. */
    public void startAnimation() {
        background.setOpacity(0);
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(background.getOpacity() < 0.50) {
                    background.setOpacity(background.getOpacity() + 0.01);
                }
                else {
                    this.stop();
                }
            }
        };
        animation.start();
    }

    public static SettingsController getInstance() {
        return settingsController;
    }
}
