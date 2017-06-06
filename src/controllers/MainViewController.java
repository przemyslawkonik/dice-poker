package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.combination.Arrangement;
import model.combination.DicePokerArrangement;
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
    private DicesController enemyDicesController;

    @FXML
    private CombinationController enemyCombinationController;

    @FXML
    private DicesController playerDicesController;

    @FXML
    private CombinationController playerCombinationController;

    private Arrangement enemyDicePokerCombination;
    private DiceBox enemyDiceBox;

    private Arrangement playerDicePokerCombination;
    private DiceBox playerDiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerDiceBox = new DiceBox(5);
        playerDicePokerCombination = new DicePokerArrangement(playerDiceBox);

        playerDicesController.bindDices(playerDiceBox);
        playerCombinationController.bindCombination(playerDicePokerCombination);


        enemyDiceBox = new DiceBox(5);
        enemyDicePokerCombination = new DicePokerArrangement(enemyDiceBox);

        enemyDicesController.bindDices(enemyDiceBox);
        enemyCombinationController.bindCombination(enemyDicePokerCombination);

    }

    @FXML
    public void handleAction() {
        playerDiceBox.rollAll();
        enemyDiceBox.rollAll();

        playerDicePokerCombination.calculate();
        enemyDicePokerCombination.calculate();
    }
}
