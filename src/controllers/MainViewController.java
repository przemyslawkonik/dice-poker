package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import model.combination.Arrangement;
import model.dice.DiceBox;
import model.game.Game;
import model.game.Pot;
import model.game.Result;
import model.game.Statistics;
import model.player.ComputerAI;
import model.player.Money;
import model.player.Player;
import tools.AlertBox;
import tools.Pause;
import tools.ResourceReader;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController implements Initializable {

    @FXML
    private TextArea manualText;

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
    private boolean firstTurn;

    public MainViewController() {
        human = new Player(new DiceBox(5), new Arrangement(), new Money(500));
        computer = new Player(new DiceBox(5), new Arrangement(), new Money(0));

        game = new Game();
        pot = new Pot();
        statistics = new Statistics();
        firstTurn = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manualText.setText(new ResourceReader().readText("manual"));
        initBindings();
        prepareView();
    }

    @FXML
    public void handleAction() throws Exception{

        AlertBox alertBox = new AlertBox();

        if (firstTurn) {
            prepareView();

            if (alertBox.setBet(human, pot, "Set bet")) {
                new Thread(() -> {
                    rollButton.setDisable(true);
                    humanDicesController.setDisableAll(true);
                    Pause.run(progressBar);

                    game.firstTurn(human);

                    humanDicesController.setVisibleAll(true);
                    humanCombination.setVisible(true);

                    Pause.run(progressBar);

                    game.firstTurn(computer);

                    computerDicesController.setVisibleAll(true);
                    computerCombination.setVisible(true);

                    humanDicesController.setDisableAll(false);
                    rollButton.setDisable(false);
                }).start();

                firstTurn = false;
            }
        } else {

            if(alertBox.increaseBet(human, pot, "Increase or accept stake")) {
                new Thread( () -> {
                    rollButton.setDisable(true);
                    humanDicesController.setDisableAll(true);
                    humanCombination.setVisible(false);
                    humanDicesController.setVisibleSelected(false);

                    Pause.run(progressBar);

                    game.secondTurn(human, humanDicesController.getDices());

                    humanDicesController.setVisibleAll(true);
                    humanCombination.setVisible(true);

                    new ComputerAI().run(computer, human, computerDicesController.getDices());

                    computerCombination.setVisible(false);
                    computerDicesController.setVisibleSelected(false);

                    Pause.run(progressBar);

                    game.secondTurn(computer, computerDicesController.getDices());

                    computerDicesController.setVisibleAll(true);
                    computerCombination.setVisible(true);

                    rollButton.setDisable(false);
                    gameSummary();
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
    }

    private void gameSummary() {
        Platform.runLater(() -> {
            Result result = game.calculateResult(human, computer);
            game.calculateMoneyResult(human, pot, result);
            statistics.add(result);
            displayResult(result);
            checkIfEnd();
        });
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
                human.getMoney().setValue(500);
            } else {
                System.exit(0);
            }
        }
    }

    private void initBindings() {
        potLabel.textProperty().bind(pot.valueProperty().asString());
        moneyLabel.textProperty().bind(human.getMoney().valueProperty().asString());
        statisticsController.bind(statistics);

        computerCombination.textProperty().bind(computer.getArrangement().combinationProperty().asString());
        computerDicesController.bind(computer.getDiceBox());

        humanCombination.textProperty().bind(human.getArrangement().combinationProperty().asString());
        humanDicesController.bind(human.getDiceBox());
    }

    private void displayResult(Result result) {
        switch (result) {
            case WIN: {
                resultLabel.setId("win");
                resultLabel.setText("You have won!");
                break;
            }
            case LOST: {
                resultLabel.setId("lost");
                resultLabel.setText("You have lost!");
                break;
            }
            case DRAW: {
                resultLabel.setId("draw");
                resultLabel.setText("The match result in a draw!");
                break;
            }
        }
        resultLabel.setVisible(true);
    }
}
