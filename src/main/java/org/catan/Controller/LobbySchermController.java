package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LobbySchermController implements Initializable {

    private int aantalSpelers;
    private int minimumAantalSpelers;

    @FXML Pane player1pane;
    @FXML Pane player2pane;
    @FXML Pane player3pane;
    @FXML Pane player4pane;
    @FXML Text player1name;
    @FXML Text player2name;
    @FXML Text player3name;
    @FXML Text player4name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1pane.setVisible(false);
        player2pane.setVisible(false);
        player3pane.setVisible(false);
        player4pane.setVisible(false);
    }

    @FXML
    private void startGame() {
        // Deze functionaliteit zal verplaatst en uitgebreid worden (voor wanneer een speler de sessie betreed).
        player1pane.setVisible(true);
        player1name.setText("Jeroen");
    }

    @FXML
    private void leaveGame() throws IOException {
        App.setRoot("Views/joinView");
    }

    private void genoegAantalSpelers() {

    }

    private void startTimer() {

    }
}
