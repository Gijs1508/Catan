package org.catan.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.catan.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
