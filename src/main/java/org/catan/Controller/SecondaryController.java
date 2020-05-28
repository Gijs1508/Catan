package org.catan.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.catan.App;

public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}