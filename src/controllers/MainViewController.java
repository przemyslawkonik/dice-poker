package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.combination.Arrangement;
import model.dice.DiceBox;
import model.dice.State;
import model.game.Game;
import model.game.Result;
import model.game.Statistics;
import model.money.Money;
import model.player.ComputerAI;
import model.player.Player;
import model.pot.Pot;
import tools.AlertBox;
import tools.Pause;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button rollButton;

    @FXML
    private Label resultLabel;

    @FXML
    private Label potLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label computerCombination;

    @FXML
    private Label humanCombination;

    @FXML
    private DicesController computerDicesController;

    @FXML
    private DicesController humanDicesController;

    @FXML
    private StatisticsController statisticsController;

    private Player human;
    private Player computer;
    private Pot pot;
    private Game game;
    private Statistics statistics;
    private boolean firstTurn = true;
    private boolean isBet = false;

    public MainViewController() {
        pot = new Pot();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        human = new Player(new DiceBox(5), new Arrangement(), new Money(1000));
        humanDicesController.bind(human.getDiceBox());

        computer = new Player(new DiceBox(5), new Arrangement(), new Money(1000));
        computerDicesController.bind(computer.getDiceBox());

        statistics = new Statistics();
        statisticsController.bind(statistics);

        game = new Game(/*human, computer, pot*/);

        initBindings();

        prepareView();
    }

    @FXML
    public void handleAction() throws Exception{

        if(firstTurn) {
            prepareView();
            isBet = new AlertBox().bet(human, pot, "Set bet");
            if(isBet && pot.getValue() == 0) {
                new AlertBox().displayInfo("You have to bet some money!");
            }
            if (isBet && pot.getValue() > 0) {
                new Thread(() -> {
                    rollButton.setDisable(true);

                    Pause.run(progressBar);

                    Platform.runLater(() -> {
                        game.firstTurn(human);
                    });

                    humanDicesController.setDisableAll(true);
                    humanDicesController.setVisibleAll(true);
                    humanCombination.setVisible(true);

                    Pause.run(progressBar);

                    Platform.runLater(() -> {
                        game.firstTurn(computer);
                        rollButton.setText("Reroll");
                    });

                    computerDicesController.setVisibleAll(true);
                    computerCombination.setVisible(true);

                    humanDicesController.setDisableAll(false);
                    rollButton.setDisable(false);
                }).start();

                firstTurn = false;
            }
        } else {
            isBet = (human.getMoney().getValue() <= 0 || new AlertBox().bet(human, pot, "Increase or accept bet"));
            if(isBet) {
                new Thread( () -> {
                    rollButton.setDisable(true);

                    humanCombination.setVisible(false);
                    humanDicesController.setVisibleSelected(false);
                    humanDicesController.setDisableAll(true);
                    human.getDiceBox().setStateAll(State.UNMARKED);

                    Pause.run(progressBar);

                    Platform.runLater(() -> {
                        game.secondTurn(human, humanDicesController.getDices());

                        humanDicesController.setVisibleAll(true);
                        humanCombination.setVisible(true);
                        humanDicesController.setSelectedAll(false);
                    });

                    new ComputerAI().run(computer, human, computerDicesController.getDices());

                    computerCombination.setVisible(false);
                    computerDicesController.setVisibleSelected(false);
                    computer.getDiceBox().setStateAll(State.UNMARKED);

                    Pause.run(progressBar);

                    Platform.runLater(() -> {
                        game.secondTurn(computer, computerDicesController.getDices());

                        computerDicesController.setVisibleAll(true);
                        computerCombination.setVisible(true);
                        computerDicesController.setSelectedAll(false);

                        rollButton.setText("Roll");
                    });

                    rollButton.setDisable(false);

                    //rezultat
                    Platform.runLater(() -> {
                        Result result = game.calculateResult(human, computer);
                        //game.displayResult(result);
                        game.moneyResult(human, pot, result);
                        pot.setValue(0);
                        statistics.add(result);
                        resultLabel.setVisible(true);
                        checkIfEnd();
                    });

                }).start();

                firstTurn = true;
            }
        }
    }

    private void prepareView() {
        humanCombination.setVisible(false);
        humanDicesController.setVisibleAll(false);

        computerCombination.setVisible(false);
        computerDicesController.setVisibleAll(false);
        computerDicesController.setDisableAll(true);

        progressBar.setVisible(false);

        resultLabel.setVisible(false);

        isBet = false;
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

    private void initBindings() {
        potLabel.textProperty().bind(pot.valueProperty().asString());
        moneyLabel.textProperty().bind(human.getMoney().valueProperty().asString());

        computerCombination.textProperty().bind(computer.getArrangement().combinationProperty().asString());
        humanCombination.textProperty().bind(human.getArrangement().combinationProperty().asString());

        resultLabel.textProperty().bind(game.resultProperty().asString());
        resultLabel.idProperty().bind(game.resultProperty().asString());
    }
}
