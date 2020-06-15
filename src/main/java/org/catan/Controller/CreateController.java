package org.catan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.catan.App;
import org.catan.Model.CreateGameCode;
import org.catan.Model.Game;
import org.catan.interfaces.Observable;

import java.io.IOException;

public class CreateController implements Observable {
    @FXML private Text game_code_text;

    public CreateController(){
    }

    /*
    This method changes the view to
    the main menu screen
     */
    public void backToMenu() throws IOException {
        App.setRoot("./views/mainView");
    }

    /*
    This method changes the view to
    the join game screen
     */
    public void joinGame() throws IOException{
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
    }

    @Override
    public void update(Game game) {

    }
}
