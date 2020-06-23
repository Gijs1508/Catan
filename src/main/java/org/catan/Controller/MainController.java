package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
 * @Author Gijs van der Weijden
 */
public class MainController implements Observable, Initializable {
    @FXML private ImageView bgShadow;
    @FXML private ImageView title;
    @FXML private ImageView musicBtn;
    @FXML private TextField player_name_input;
    @FXML private VBox buttons;
    @FXML private Pane alertPopup;

    private static MainController mainController;

    private static String playerName = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(playerName.length() > 0) {
            player_name_input.setText(playerName);
        }

        MenuMusicHandler.initializeMusic(musicBtn);
        mainController = this;
        try {
            alertPopup.getChildren().add(App.loadFXML("Views/alertPopUpView"));
            alertPopup.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_name_input.textProperty().addListener((observable, oldValue, newValue) -> {
            playerName = newValue;
        });
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
        if(nameIsSet()){
            App.setClientPlayer(new Player(player_name_input.getText()));
            App.getClientPlayer().setHost(true);
            App.setRoot("./Views/lobbyView");
            App.setStageHeight(837);
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
                    Sound.setIntroMusicIsPlaying(true);

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
        AlertPopUpController.getInstance().setAlertPlacedController(this.getClass());
        showAlertPopup();
        AlertPopUpController.getInstance().setAlertDescription("You didn't fill in a name!");
    }

    public void showAlertPopup() {
        alertPopup.setVisible(true);
    }
    public void hideAlertPopup() {
        alertPopup.setVisible(false);
    }

    private boolean nameIsSet(){
        return !player_name_input.getText().equals("");
    }

    public static MainController getInstance() {
        return mainController;
    }
}
