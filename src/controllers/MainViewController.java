package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.combination.DicePokerCombination;

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

    private DicePokerCombination dicePokerCombination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dicePokerCombination = new DicePokerCombination(playerDicesController.getDices().getDices());
        combinationLabel.textProperty().bind(dicePokerCombination.combinationProperty());
    }

    @FXML
    public void handleAction() {
        playerDicesController.getDices().rollAll();
        dicePokerCombination.calculate();
        System.out.println(dicePokerCombination.combinationProperty().getValue());
    }
}
