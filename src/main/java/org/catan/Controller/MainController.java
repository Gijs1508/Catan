package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Observable, Initializable {

    @FXML private TextField player_name_input;
    @FXML private ImageView musicBtn;

    private boolean musicOn = true;
    private final Image musicOnImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOnWhite.png")));
    private final Image musicOffImg = new Image(String.valueOf(App.class.getResource("assets/img/musicOffWhite.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sound.playMenuMusic();
    }

    // Routes
    @FXML
    private void joinGame() throws IOException {
        Sound.playClick();
        if(nameIsSet()){
            Player player = new Player(player_name_input.getText());
            App.setRoot("./Views/joinView");
        } else {
            createAlert();

        }
    }

    @FXML
    private void startGame() throws IOException {
        Sound.playClick();
        if(nameIsSet()){
            Player player = new Player(player_name_input.getText());
            App.setRoot("./Views/createView");
        } else {
            createAlert();
        }
    }

    @FXML
    private void viewRules() throws IOException {
        Sound.playClick();
        App.setRoot("./Views/regelsView");
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

    private void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("You didn't fill in a name!");
        alert.show();
    }

    private boolean nameIsSet(){
        return !player_name_input.getText().equals("");
    }
}
