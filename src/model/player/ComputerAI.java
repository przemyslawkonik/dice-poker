package model.player;

import javafx.scene.control.ToggleButton;
import model.dice.State;

import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class ComputerAI {

    public void run(Player computer, Player human, List<ToggleButton> computerDices) {
        if(computer.getArrangement().getCombination().getWorth() > human.getArrangement().getCombination().getWorth()) {
            for(int i=0; i<computerDices.size(); i++) {
                if(computer.getDiceBox().getDice(i).getState().equals(State.UNMARKED)) {
                    computerDices.get(i).setSelected(true);
                }
            }
        } else if(computer.getArrangement().getCombination().getWorth() < human.getArrangement().getCombination().getWorth()) {
            for(ToggleButton t : computerDices) {
                t.setSelected(true);
            }
        } else if(computer.getArrangement().getTotalCombinationWorth() > human.getArrangement().getTotalCombinationWorth()){
            for(int i=0; i<computerDices.size(); i++) {
                if(computer.getDiceBox().getDice(i).getState().equals(State.UNMARKED)) {
                    computerDices.get(i).setSelected(true);
                }
            }
        } else if(computer.getArrangement().getTotalCombinationWorth() < human.getArrangement().getTotalCombinationWorth()) {
            for(int i=0; i<computerDices.size(); i++) {
                if(computer.getDiceBox().getDice(i).getState().equals(State.UNMARKED)) {
                    computerDices.get(i).setSelected(true);
                }
            }
        } else {
            for(ToggleButton t : computerDices) {
                t.setSelected(true);
            }
        }
    }
}
