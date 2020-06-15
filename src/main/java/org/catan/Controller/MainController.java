package org.catan.Controller;

import com.sun.tools.javac.Main;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.MenuMusicHandler;
import org.catan.Model.Player;
import org.catan.Model.Sound;
import org.catan.interfaces.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Used to handle button clicks on the main menu screen.
 *
 * @Author Gijs van der Weijden
 */
public class MainController implements Observable, Initializable {
    @FXML private ImageView bgShadow;
    @FXML private ImageView bg;
    @FXML private ImageView title;
    @FXML private ImageView musicBtn;

    @FXML private TextField player_name_input;
    @FXML private VBox buttons;

    private LobbySchermController lobbySchermController = LobbySchermController.getInstance();
    private static MainController mainController;

    // Routes
    public MainController() {
        mainController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuMusicHandler.initializeMusic(musicBtn);
    }

    @FXML
    private void joinGame() throws IOException {
        Sound.playClick();
        if(nameIsSet()){
            App.setClientPlayer(new Player(player_name_input.getText()));
            App.setRoot("./Views/joinView");
        } else {
            createAlert();
        }
    }

    @FXML
    private void startGame() throws IOException {
        Sound.playClick();
        App.setStageSize(1200, 810);
        if(nameIsSet()){
            App.setClientPlayer(new Player(player_name_input.getText()));
            App.getClientPlayer().setHost(true);
            App.setRoot("./Views/lobbyView");
        } else {
            createAlert();
        }
    }

    @FXML
    private void viewRules() throws IOException {
        Sound.playClick();
        App.setRoot("./Views/rulesView");
    }

    @FXML
    private void toggleMusic() {
        Sound.playClick();
        MenuMusicHandler.toggleMusic(musicBtn);
    }

    @Override
    public void update(Game game) {

    }

    /** Starts the animation that plays at launch.
     * @author Jeroen */
    public void playAnimation() {
        // Sets starting positions/opacity for the animation
        bgShadow.setOpacity(0);
        buttons.setOpacity(0);
        player_name_input.setOpacity(0);
        musicBtn.setOpacity(0);
        double defaultTitleY = title.getLayoutY();
        title.setLayoutY(-260);

        AnimationTimer animation = new AnimationTimer() {
            int tick;
            @Override
            public void handle(long l) {
                tick++;
                if(bgShadow.getOpacity() <= 1) {
                    bgShadow.setOpacity(bgShadow.getOpacity() + 0.007); }
                else {
                    Sound.playMenuMusic();
                    Sound.introMusicIsPlaying(true);

                    if(title.getLayoutY() <= defaultTitleY) {
                        title.setLayoutY(title.getLayoutY() + 1.4); }
                    else {
                        if(buttons.getOpacity() <= 1) {
                            buttons.setOpacity(buttons.getOpacity() + 0.1);
                            player_name_input.setOpacity(buttons.getOpacity() + 0.1);
                            musicBtn.setOpacity(buttons.getOpacity() + 0.1);}
                        else {
                            this.stop(); }
                    }
                }
            }
        };
        animation.start();
    }

    private void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("You didn't fill in a name!");
        alert.show();
    }

    private boolean nameIsSet(){
        return !player_name_input.getText().equals("");
    }

    public static MainController getInstance() {
        return mainController;
    }
}
