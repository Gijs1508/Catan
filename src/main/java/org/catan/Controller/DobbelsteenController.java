package org.catan.Controller;

//import model.Dobbelsteen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DobbelsteenController {

//    private Dobbelsteen dobbelsteen_1;
//    private Dobbelsteen dobbelsteen_2;
    private LogController logController = new LogController();

    public DobbelsteenController() {

    }

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
        logController.logRollEvent("2", "4");
    }
}
