package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.combination.Arrangement;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class CombinationController implements Initializable {

    @FXML
    private Label combination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bindCombination(Arrangement arrangement) {
        combination.textProperty().bind(arrangement.combinationProperty().asString());
    }
}