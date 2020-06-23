package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
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

/**
 * Used to create a new game. *
 * @Author Gijs van der Weijden
 */
public class CreateController implements Observable, Initializable {
    @FXML private Text game_code_text;
    @FXML private ImageView musicBtn;

    public CreateController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    /**
     * This method changes the view to
     * the main menu screen
     * @author Gijs
     */
    public void backToMenu() throws IOException {
        Sound.playClick();
        App.setRoot("./views/mainView");
    }

    /**
     * This method changes the view to
     * the join game screen
     * @author Gijs
     */
    public void joinGame() throws IOException{
        Sound.playClick();
        App.setRoot("./views/joinView");
    }

    /**
     * This method creates a new game when
     * the player clicks the button.
     * Sets the text of the game code to the generated seed.
     * @author Gijs
     */
    public void createGame() {
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
