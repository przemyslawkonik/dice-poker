package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import model.dice.Dice;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class DicesController implements Initializable {

    @FXML
    private ToggleButton dice1;

    @FXML
    private ToggleButton dice2;

    @FXML
    private ToggleButton dice3;

    @FXML
    private ToggleButton dice4;

    @FXML
    private ToggleButton dice5;

    private List<ToggleButton> dices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = new LinkedList<>(Arrays.asList(
                dice1, dice2, dice3, dice4, dice5
        ));
    }

    public void bindDices(List<Dice> dices) {
        bindDicesValues(dices);
    }

    private void bindDicesValues(List<Dice> dices) {
        for(int i=0; i<this.dices.size(); i++ ) {
            this.dices.get(i).textProperty().bind(dices.get(i).valueProperty().asString());
        }
    }
}
