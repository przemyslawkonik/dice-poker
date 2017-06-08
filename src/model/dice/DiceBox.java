package model.dice;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class DiceBox {

    private List<Dice> dices;

    public DiceBox(List<Dice> dices) {
        this.dices = dices;
    }

    public DiceBox(int dices) {
        this.dices = createDiceList(dices);
    }

    public DiceBox() {
        dices = new LinkedList<>();
    }

    private List<Dice> createDiceList(int dices) {
        List<Dice> list = new LinkedList<>();
        for(int i=0; i<dices; i++) {
            list.add(new Dice());
        }
        return list;
    }

    public int[] rollAll() {
        int[] values = new int[dices.size()];
        for(int i=0; i<dices.size(); i++) {
            values[i] = dices.get(i).roll();
        }
        return values;
    }

    public int roll(int dice) {
        if(dice <0 || dice > dices.size()) {
            throw new IndexOutOfBoundsException();
        }
        return dices.get(dice).roll();
    }

    public void setStateAll(State state) {
        for(Dice d : dices) {
            d.setState(state);
        }
    }

    public void setState(int dice, State state) {
        if(dice < 0 || dice > dices.size()) {
            throw new IndexOutOfBoundsException();
        }
        dices.get(dice).setState(state);
    }

    public List<Dice> getDices() {
        return dices;
    }

    public Dice getDice(int dice) {
        if(dice < 0 || dice > dices.size()) {
            throw new IndexOutOfBoundsException();
        }
        return dices.get(dice);
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }
}
