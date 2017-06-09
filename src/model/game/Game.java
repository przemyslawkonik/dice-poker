package model.game;

import javafx.application.Platform;
import javafx.scene.control.ToggleButton;
import model.dice.State;
import model.player.Player;

import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Game {

    public void firstTurn(Player player) {
        Platform.runLater( () -> {
            player.getDiceBox().rollAll();
            player.getDiceBox().setStateAll(State.UNMARKED);
            player.getArrangement().calculate();
            player.getArrangement().markDicesInCombination();
        });
    }

    public void secondTurn(Player player, List<ToggleButton> dices) {
        Platform.runLater( () -> {
            for(int i=0; i<dices.size(); i++) {
                if(dices.get(i).isSelected()) {
                    player.getDiceBox().getDice(i).roll();
                }
            }
            player.getDiceBox().setStateAll(State.UNMARKED);
            player.getArrangement().calculate();
            player.getArrangement().markDicesInCombination();
        });
    }

    public Result calculateResult(Player human, Player computer) {
        if (human.getArrangement().getCombinationWorth() > computer.getArrangement().getCombinationWorth()) {
            return Result.WIN;
        } else if (human.getArrangement().getCombinationWorth() < computer.getArrangement().getCombinationWorth()) {
            return Result.LOST;
        } else {
            if (human.getArrangement().getTotalCombinationWorth() > computer.getArrangement().getTotalCombinationWorth()) {
                return Result.WIN;
            } else if (human.getArrangement().getTotalCombinationWorth() < computer.getArrangement().getTotalCombinationWorth()) {
                return Result.LOST;
            }
        }
        return Result.DRAW;
    }

    public void calculateMoneyResult(Player player, Pot pot, Result result) {
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
        pot.setValue(0);
    }
}
