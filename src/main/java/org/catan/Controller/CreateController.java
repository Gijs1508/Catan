package org.catan.Controller;

import com.sun.javafx.scene.control.skin.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.CreateGameCode;
import org.catan.Model.Game;
import org.catan.Model.MenuMusicHandler;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Observable, Initializable {
    @FXML private Text game_code_text;
    @FXML private ImageView musicBtn;

    public CreateController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    /*
    This method changes the view to
    the main menu screen
     */
    public void backToMenu() throws IOException {
        Sound.playClick();
        App.setRoot("./views/mainView");
    }

    /*
    This method changes the view to
    the join game screen
     */
    public void joinGame() throws IOException{
        Sound.playClick();
        App.setRoot("./views/joinView");
    }


    /*
    This method creates a new game when
    the player clicks the button.
    Sets the text of the game code to the generated seed.
     */
    public void createGame() {
        // TODO Werner: add code to db and check if it does not exist
        long gameCode = CreateGameCode.randomCodeGen();
        game_code_text.setText(String.valueOf(gameCode));
        Sound.playClick();
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
