package model.game;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ToggleButton;
import model.player.Player;
import tools.AlertBox;

import java.io.IOException;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Game {

    private ObjectProperty<Result> result;

    public Game() {
        result = new SimpleObjectProperty<>(Result.NO_RESULT);
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
            result.set(Result.WIN);
        } else if (human.getArrangement().getCombination().getWorth() < computer.getArrangement().getCombination().getWorth()) {
            result.set(Result.LOST);
        } else {
            if(human.getArrangement().getTotalCombinationWorth() > computer.getArrangement().getTotalCombinationWorth()) {
                result.set(Result.WIN);
            } else if(human.getArrangement().getTotalCombinationWorth() < computer.getArrangement().getTotalCombinationWorth()) {
                result.set(Result.LOST);
            } else {
                result.set(Result.DRAW);
            }
        }
        return result.get();
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
                break;
            }
            case DRAW: {
                player.getMoney().increase(pot.getValue()/2);
            }
        }
    }

    public Result getResult() {
        return result.get();
    }

    public ObjectProperty<Result> resultProperty() {
        return result;
    }

    public void setResult(Result result) {
        this.result.set(result);
    }
}
