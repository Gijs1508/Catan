package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.MenuMusicHandler;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Used to handle button clicks on the rules screen.
 *
 * @Author Gijs van der Weijden
 */
public class RulesController implements Observable, Initializable {
    @FXML private ImageView musicBtn;

    // Routes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    @FXML
    public void backToMenu() throws IOException {
        App.setRoot("./Views/mainView");
    }

    @FXML
    private void toggleMusic() {
        Sound.playClick();
        MenuMusicHandler.toggleMusic(musicBtn);
    }

    @Override
    public void update(Game game) {

    }
}
