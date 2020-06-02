package org.catan.Controller;

//import model.Dobbelsteen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.catan.App;
import org.catan.Model.Log;

import java.io.IOException;

public class DobbelsteenController {

    LogController logController = LogController.getInstance();

//    private Dobbelsteen dobbelsteen_1;
//    private Dobbelsteen dobbelsteen_2;

    public void randomGetal() {

    }

//    public int[] getDobbelsteenGetallen() {
//        return int[]; // Dit moet worden gewijzigd
//    }

    public void startAnimatie() {

    }

    public void stopAnimatie() {

    }

    public void showDobbelstenen() {

    }

    @FXML
    public void throwDie() {
        logController.logRollEvent("2", "4"); // Replace parameters with the results of the throw
    }
}
