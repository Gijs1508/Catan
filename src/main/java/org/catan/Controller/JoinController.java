package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.MenuMusicHandler;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Observable, Initializable {
    @FXML private TextField code_input;
    @FXML private Text error_text;
    @FXML private ImageView musicBtn;

    // Routes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    @FXML
    private void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }

    @FXML
    private void createGame() throws IOException{
        App.setRoot("./views/createView");
    }

    @FXML
    private void handleButtonJoinAction(ActionEvent actionEvent) throws IOException{
        var code_text = code_input.getText();
        // TODO Werner: check if game code exists + is open!
        if(code_text.equals("testcode")){
            //TODO Jeroen: Create route to gameview here :)
            //App.setRoot("./Views/gameView");
            System.out.println("haha leuk je code klopt");
        } else {
            error_text.setText("Code is onjuist!");
        }
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
