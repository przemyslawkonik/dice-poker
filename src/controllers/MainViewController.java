package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import model.bet.Bet;
import model.combination.Arrangement;
import model.dice.DiceBox;
import model.dice.State;
import model.game.Game;
import model.game.Result;
import model.game.Statistics;
import model.money.Money;
import model.player.Player;
import model.pot.Pot;
import tools.AlertBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    @FXML
    private ProgressBarController progressBarController;

    @FXML
    private StatisticsController statisticsController;

    private Player human;
    private Player computer;
    private Pot pot;
    private Game game;
    private Statistics statistics;
    private boolean firstTurn = true;

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

        statistics = new Statistics();
        statisticsController.bind(statistics);

        game = new Game(/*human, computer, pot*/);

        prepareView();
    }

    @FXML
    public void handleAction() throws Exception{

        if(firstTurn) {
            prepareView();
            if (new Bet().set(human, pot, "Set bet")) {
                new Thread(() -> {
                    rollButton.setDisable(true);

                    progressBarController.run(0.1, 150);

                    Platform.runLater(() -> {
                        game.firstTurn(human);
                    });

                    humanDicesController.setDisableAll(true);
                    humanDicesController.setVisibleAll(true);
                    humanCombinationController.setVisible(true);

                    progressBarController.run(0.1, 150);

                    Platform.runLater(() -> {
                        game.firstTurn(computer);
                        rollButton.setText("Reroll");
                    });

                    computerDicesController.setVisibleAll(true);
                    computerCombinationController.setVisible(true);

                    humanDicesController.setDisableAll(false);
                    rollButton.setDisable(false);
                }).start();

                firstTurn = false;
            }
        } else {
            if(new Bet().set(human, pot, "Increase bet")) {
                new Thread( () -> {
                    rollButton.setDisable(true);

                    humanCombinationController.setVisible(false);
                    humanDicesController.setVisibleSelected(false);
                    humanDicesController.setDisableAll(true);
                    human.getDiceBox().setStateAll(State.UNMARKED);

                    progressBarController.run(0.1, 150);

                    Platform.runLater( () -> {
                        game.secondTurn(human, humanDicesController.getDices());
                    });

                    humanDicesController.setVisibleAll(true);
                    humanCombinationController.setVisible(true);
                    humanDicesController.setSelectedAll(false);

                    computerCombinationController.setVisible(false);
                    computerDicesController.setVisibleSelected(false);
                    computer.getDiceBox().setStateAll(State.UNMARKED);

                    progressBarController.run(0.1, 150);

                    Platform.runLater(() -> {
                        game.secondTurn(computer, computerDicesController.getDices());
                    });

                    computerDicesController.setVisibleAll(true);
                    computerCombinationController.setVisible(true);
                    computerDicesController.setSelectedAll(false);

                    rollButton.setDisable(false);

                    //rezultat
                    Result result = game.calculateResult(human, computer);
                    Platform.runLater(() -> {
                        game.displayResult(result);
                        game.moneyResult(human, pot, result);
                        pot.setValue(0);
                        statistics.add(result);
                        checkIfEnd();
                    });

                }).start();

                firstTurn = true;
            }
        }
    }

    private void prepareView() {
        humanCombinationController.setVisible(false);
        humanDicesController.setVisibleAll(false);

        computerCombinationController.setVisible(false);
        computerDicesController.setVisibleAll(false);
        computerDicesController.setDisableAll(true);

        progressBarController.setVisible(false);

        rollButton.setText("Roll");
    }

    private void checkIfEnd() {
        if (human.getMoney().getValue() <= 0) {
            boolean choice = false;
            try {
                choice = new AlertBox().displayChoice("You have lost all your money! Do you want to start a new game?");
            } catch (IOException e) {
            }
            if(choice) {
                prepareView();
                human.getMoney().setValue(1000);
            } else {
                System.exit(0);
            }
        }
    }
}
