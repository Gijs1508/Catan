package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.catan.App;
import org.catan.Model.Sound;

public class SettingsController {
    @FXML private Pane background;
    @FXML private ImageView musicImg;
    @FXML private ImageView soundsImg;

    private Image soundsOnImg = new Image(String.valueOf(App.class.getResource("assets/img/audioOn.png")));
    private Image soundsOffImg = new Image(String.valueOf(App.class.getResource("assets/img/audioOff.png")));
    private Image musicOnImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOn.png")));
    private Image musicOffImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOff.png")));

    private boolean musicOn = true;
    private boolean soundsOn = true;

    private static SettingsController settingsController;

    public SettingsController() {
        settingsController = this;
    }

    public void soundsBtnClicked() {
        if (soundsOn) {
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

    public void musicBtnClicked() {
        if (musicOn) {
//            Sound.muteMusic();
            Sound.playClick();
            musicImg.setImage(musicOffImg);
            musicOn = false;
        }
        else {
//            Sound.unmuteMusic();
            Sound.playClick();
            musicImg.setImage(musicOnImg);
            musicOn = true;
        }
    }

    public void applyBtnClicked() {
        Sound.playClick();
        ScreenController.getInstance().hideSettings();
    }

    public void startAnimation() {
        background.setOpacity(0);
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(background.getOpacity() < 0.50) {
                    background.setOpacity(background.getOpacity() + 0.01);
                }
                else {

                }
            }
        };
        animation.start();
    }

    public static SettingsController getInstance() {
        return settingsController;
    }
}
