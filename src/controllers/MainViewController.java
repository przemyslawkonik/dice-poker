package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.bet.Bet;
import model.combination.Arrangement;
import model.dice.DiceBox;
import model.game.Game;
import model.money.Money;
import model.player.Player;
import model.pot.Pot;

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
    private Pot pot;
    private Game game;
    private boolean firstTurn = true;
    private boolean secondTurn = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        human = new Player(new DiceBox(5), new Arrangement(), new Money(1000));
        humanDicesController.bind(human.getDiceBox());
        humanCombinationController.bind(human.getArrangement());
        humanMoneyController.bind(human.getMoney());

        computer = new Player(new DiceBox(5), new Arrangement(), new Money(1000));
        computerDicesController.bind(computer.getDiceBox());
        computerCombinationController.bind(computer.getArrangement());

        pot = new Pot();
        potController.bind(pot);

        game = new Game(/*human, computer, pot*/);

        prepareGameView();
    }

    @FXML
    public void handleAction() throws Exception{
        /*
        if(firstTurn) {
            game.prepareView();
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
        */
    }

    private void prepareGameView() {
        humanCombinationController.setVisible(false);
        humanDicesController.setVisibleAll(false);

        computerCombinationController.setVisible(false);
        computerDicesController.setVisibleAll(false);
    }
}
