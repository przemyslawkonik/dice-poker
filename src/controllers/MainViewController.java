package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.bet.Bet;
import model.game.Game;
import model.money.Money;
import model.player.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController implements Initializable {

    @FXML
    private Button rollButton;

    @FXML
    private DicesController computerDicesController;

    @FXML
    private CombinationController computerCombinationController;

    @FXML
    private DicesController humanDicesController;

    @FXML
    private CombinationController humanCombinationController;

    @FXML
    private MoneyController humanMoneyController;

    @FXML
    private PotController potController;

    private Player human;
    private Player computer;
    private Game game;
    private boolean firstTurn = true;
    private boolean secondTurn = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        human = new Player(humanDicesController.getDiceBox(), humanCombinationController.getArrangement(), humanMoneyController.getMoney());
        computer = new Player(computerDicesController.getDiceBox(), computerCombinationController.getArrangement(), new Money(1000));

        humanCombinationController.getArrangement().setDiceBox(human.getDiceBox());
        computerCombinationController.getArrangement().setDiceBox(computer.getDiceBox());

        game = new Game(human, computer);
        game.setHumanController(humanDicesController, humanCombinationController, humanMoneyController);
        game.setComputerControllers(computerDicesController, computerCombinationController);
        prepareGameView();
    }

    @FXML
    public void handleAction() throws Exception{
        if(firstTurn) {
            prepareGameView();
            if(new Bet().set(human, potController.getPot(), "Set bet")) {
                game.playFirstRound();
                firstTurn = false;
                secondTurn = true;
            }
        } else if(secondTurn){
            if(new Bet().set(human, potController.getPot(), "Increase bet")) {
                game.playSecondRound();
                secondTurn = false;
                firstTurn = true;
            }
        }
    }

    private void prepareGameView() {
        humanDicesController.setVisibleAll(false);
        humanDicesController.setSelectedAll(false);
        humanCombinationController.getCombination().setVisible(false);

        computerDicesController.setVisibleAll(false);
        computerDicesController.setSelectedAll(false);
        computerDicesController.setDisableAll(true);
        computerCombinationController.getCombination().setVisible(false);
    }
}
