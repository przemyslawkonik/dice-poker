package model.game;

import controllers.CombinationController;
import controllers.DicesController;
import controllers.MoneyController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import model.dice.State;
import model.player.Player;
import tools.AlertBox;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Game {

    private Player human;
    private Player computer;

    private DicesController humanDicesController;
    private DicesController computerDicesController;

    private CombinationController humanCombinationController;
    private CombinationController computerCombinationController;

    private MoneyController humanMoneyController;

    public Game(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
    }

    public void setHumanController(DicesController dicesController, CombinationController combinationController, MoneyController moneyController) {
        this.humanDicesController = dicesController;
        this.humanCombinationController = combinationController;
        this.humanMoneyController = moneyController;
    }

    public void setComputerControllers(DicesController dicesController, CombinationController combinationController) {
        this.computerDicesController = dicesController;
        this.computerCombinationController = combinationController;
    }

    public void prepare() {
        humanDicesController.setVisibleAll(false);
        humanDicesController.setSelectedAll(false);
        humanCombinationController.getCombination().setVisible(false);

        computerDicesController.setVisibleAll(false);
        computerDicesController.setSelectedAll(false);
        computerDicesController.setDisableAll(true);
        computerCombinationController.getCombination().setVisible(false);
    }

    public void playFirstRound() {
        new Thread( () -> {
            handleScreen(2000);
            firstTurn(human, humanDicesController, humanCombinationController);

            humanDicesController.setDisableAll(true);

            handleScreen(2000);
            firstTurn(computer, computerDicesController, computerCombinationController);

            humanDicesController.setDisableAll(false);
        }).start();
    }

    public void playSecondRound() {
        new Thread( () -> {
            humanCombinationController.getCombination().setVisible(false);
            humanDicesController.setVisibleSelected(false);
            humanDicesController.setDisableAll(true);
            human.getDiceBox().setStateAll(State.UNMARKED);
            handleScreen(2000);
            secondTurn(human, humanDicesController, humanCombinationController);

            //human.getDiceBox().setSelectedAll(false);

            enemyAI();
            computerCombinationController.getCombination().setVisible(false);
            computerDicesController.setVisibleSelected(false);
            computer.getDiceBox().setStateAll(State.UNMARKED);
            handleScreen(2000);
            secondTurn(computer, computerDicesController, computerCombinationController);

            //displayResult();
            Platform.runLater( () -> {
                displayResult();
                prepare();
            });

            //enemy.getDiceBox().setSelectedAll(false);
            //human.getDiceBox().setSelectedAll(false);
            //human.getDiceBox().setDisableAll(false);

        }).start();
    }


    private void firstTurn(Player player, DicesController dicesController, CombinationController combinationController) {
        Platform.runLater( () -> {
            player.getDiceBox().rollAll();
            player.getArrangement().calculate();
            dicesController.setVisibleAll(true);
            combinationController.getCombination().setVisible(true);
        });
    }

    private void secondTurn(Player player, DicesController dicesController, CombinationController combinationController) {
        Platform.runLater( () -> {
            dicesController.rollSelected();
            player.getArrangement().calculate();
            dicesController.setVisibleAll(true);
            combinationController.getCombination().setVisible(true);

            dicesController.setSelectedAll(false);
        });
    }

    private void handleScreen(int miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {}
    }

    private void enemyAI() {
        for(ToggleButton d : computerDicesController.getDices()) {
            int x = new Random().nextInt(2);
            if(x==0) {
                d.setSelected(true);
            } else {
                d.setSelected(false);
            }
            }
    }

    private void displayResult() {
        AlertBox alertBox = new AlertBox();
        try {
            if (human.getArrangement().getCombinationWorth() > computer.getArrangement().getCombinationWorth()) {
                alertBox.displayInfo("You won!");
            } else if (human.getArrangement().getCombinationWorth() < computer.getArrangement().getCombinationWorth()) {
                alertBox.displayInfo("You lost!");
            } else {
                alertBox.displayInfo("Draw!");
            }
        }catch (IOException e) {}
    }

}
