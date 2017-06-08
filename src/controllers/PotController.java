package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.pot.Pot;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class PotController implements Initializable{

    @FXML
    private Label pot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void bind(Pot pot) {
        this.pot.textProperty().bind(pot.valueProperty().asString());
    }
}
