package org.catan.Helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.catan.App;

import java.io.IOException;

public class fxmlHelper {
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
