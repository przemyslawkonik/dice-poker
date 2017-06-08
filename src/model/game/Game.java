package model.game;

import javafx.scene.control.ToggleButton;
import model.player.Player;
import model.pot.Pot;
import tools.AlertBox;

import java.io.IOException;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Game {

    /*
    private Pot pot;
    private Player human;
    private Player computer;
    */

    public Game(/*Player human, Player computer, Pot pot*/) {
        /*
        this.human = human;
        this.computer = computer;
        this.pot = pot;
        */
    }

    public void firstTurn(Player player) {
        player.getDiceBox().rollAll();
        player.getArrangement().calculate();
    }

    public void secondTurn(Player player, List<ToggleButton> dices) {
        for(int i=0; i<dices.size(); i++) {
            if(dices.get(i).isSelected()) {
                player.getDiceBox().getDice(i).roll();
            }
        }
        player.getArrangement().calculate();
    }

    public Result calculateResult(Player human, Player computer) {
        if (human.getArrangement().getCombination().getWorth() > computer.getArrangement().getCombination().getWorth()) {
            return Result.WIN;
        } else if (human.getArrangement().getCombination().getWorth() < computer.getArrangement().getCombination().getWorth()) {
            return Result.LOST;
        } else {
            if(human.getArrangement().getCombinationValue() > computer.getArrangement().getCombinationValue()) {
                return Result.WIN;
            } else if(human.getArrangement().getCombinationValue() < computer.getArrangement().getCombinationValue()) {
                return Result.LOST;
            } else {
                return Result.DRAW;
            }
        }
    }

    public void displayResult(Result result) {
        AlertBox alertBox = new AlertBox();
        try {
            switch (result) {
                case WIN: {
                    alertBox.displayInfo("You won!");
                    break;
                }
                case LOST: {
                    alertBox.displayInfo("You lost!");
                    break;
                }
                case DRAW: {
                    alertBox.displayInfo("Draw!");
                    break;
                }
            }
        }catch (IOException e) {}
    }

    public void moneyResult(Player player, Pot pot, Result result) {
        switch (result) {
            case WIN: {
                player.getMoney().increase(pot.getValue());
                break;
            }
            case LOST: {
                if(player.getMoney().getValue() <= 0) {
                    boolean choice = false;
                    try {
                        choice = new AlertBox().displayChoice("You have lost all your money. Do you want to start a new game?");
                    }catch (IOException e) {}
                    if(choice) {
                        player.getMoney().setValue(1000);
                    }
                }
                break;
            }
            case DRAW: {
                player.getMoney().increase(pot.getValue()/2);
            }
        }
    }
}

/*

    private Pot pot;

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

    public Pot getPot() {
        return pot;
    }

    public void setPot(Pot pot) {
        this.pot = pot;
    }

    public void prepareView() {
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
                Result result = getResult();
                displayResult(result);
                System.out.println(human.getMoney().getValue());
                moneyResult(result);
                pot.setValue(0);
                System.out.println(human.getMoney().getValue());
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

    private void displayResult(Result result) {
        AlertBox alertBox = new AlertBox();
        try {
            switch (result) {
                case WIN: {
                    alertBox.displayInfo("You won!");
                    break;
                }
                case LOST: {
                    alertBox.displayInfo("You lost!");
                    break;
                }
                case DRAW: {
                    alertBox.displayInfo("Draw!");
                    break;
                }
            }
        }catch (IOException e) {}
    }

    private Result getResult() {
        if (human.getArrangement().getCombination().getWorth() > computer.getArrangement().getCombination().getWorth()) {
            return Result.WIN;
        } else if (human.getArrangement().getCombination().getWorth() < computer.getArrangement().getCombination().getWorth()) {
            return Result.LOST;
        } else {
            if(human.getArrangement().getCombinationValue() > computer.getArrangement().getCombinationValue()) {
                return Result.WIN;
            } else if(human.getArrangement().getCombinationValue() < computer.getArrangement().getCombinationValue()) {
                return Result.LOST;
            } else {
                return Result.DRAW;
            }
        }
    }

    private void moneyResult(Result result) {
        switch (result) {
            case WIN: {
                human.getMoney().increase(pot.getValue());
                break;
            }
            case LOST: {
                if(human.getMoney().getValue() <= 0) {
                    boolean choice = false;
                    try {
                        choice = new AlertBox().displayChoice("You have lost all your money. Do you want to start a new game?");
                    }catch (IOException e) {}
                    if(choice) {
                        human.getMoney().setValue(1000);
                    }
                }
                break;
            }
            case DRAW: {
                human.getMoney().increase(pot.getValue()/2);
            }
        }
    }

 */
