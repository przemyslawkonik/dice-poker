package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.combination.Arrangement;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class ArrangementController implements Initializable {

    @FXML
    private Arrangement arrangement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void calculate() {
        arrangement.calculate();
    }

    public Arrangement getArrangement() {
        return arrangement;
    }


}
