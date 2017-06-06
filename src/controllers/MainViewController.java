package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.combination.DicePokerCombination;
import model.dice.DiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController implements Initializable {

    @FXML
    private Button rollButton;

    @FXML
    private Label combinationLabel;

    @FXML
    private DicesController enemyDicesController;

    @FXML
    private DicesController playerDicesController;

    private DicePokerCombination playerDicePokerCombination;
    private DiceBox playerDiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerDiceBox = new DiceBox(5);
        playerDicePokerCombination = new DicePokerCombination(playerDiceBox.getDices());
        playerDicesController.bindDices(playerDiceBox.getDices());

        combinationLabel.textProperty().bind(playerDicePokerCombination.combinationProperty());
    }

    @FXML
    public void handleAction() {
        playerDiceBox.rollAll();
        playerDicePokerCombination.calculate();
    }
}
