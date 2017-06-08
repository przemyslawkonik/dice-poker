package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.combination.Arrangement;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class CombinationController implements Initializable {


    @FXML
    private Label combination;

    private Arrangement arrangement;

    public CombinationController() {
        arrangement = new Arrangement();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBindings();
    }

    private void initBindings() {
        combination.textProperty().bind(arrangement.combinationProperty().asString());
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public Label getCombination() {
        return combination;
    }

    public void setCombination(Label combination) {
        this.combination = combination;
    }

}
