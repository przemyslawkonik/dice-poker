package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import model.dice.Dice;
import model.dice.DiceBox;

import java.net.URL;
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

    private final DiceBox dices;

    public DicesController() {
        dices = new DiceBox(5);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        List<Dice> diceList = dices.getDices();
        dice1.textProperty().bind(diceList.get(0).valueProperty().asString());
        dice2.textProperty().bind(diceList.get(1).valueProperty().asString());
        dice3.textProperty().bind(diceList.get(2).valueProperty().asString());
        dice4.textProperty().bind(diceList.get(3).valueProperty().asString());
        dice5.textProperty().bind(diceList.get(4).valueProperty().asString());
    }

    public DiceBox getDices() {
        return dices;
    }
}
