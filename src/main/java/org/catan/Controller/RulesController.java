package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class RulesController implements Observable {
    @FXML private ImageView musicBtn;

    private boolean musicOn = true;
    private final Image musicOnImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOnWhite.png")));
    private final Image musicOffImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOffWhite.png")));

    // Routes
    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./Views/mainView");
    }

    @FXML
    private void toggleMusic() {
        Sound.playClick();
        if (musicOn) {
            Sound.pauseMenuMusic();
            musicBtn.setImage(musicOffImg);
            musicOn = false;
        }
        else {
            Sound.playMenuMusic();
            musicBtn.setImage(musicOnImg);
            musicOn = true;
        }
    }

    @Override
    public void update(Game game) {

    }
}
