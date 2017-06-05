package model.dice;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class DiceBox {

    private List<Dice> dices;

    public DiceBox(int numberOfDices) {
        dices = new LinkedList<>();
        initDices(numberOfDices);
    }

    private void initDices(int numberOfDices) {
        for(int i=0; i<numberOfDices; i++) {
            dices.add(new StandardDice());
        }
    }

    public void rollAll() {
        for(Dice d : dices) {
            d.roll();
        }
    }

    public int roll(int numberOfDice) {
        if(numberOfDice < 0 && numberOfDice > dices.size()) {
            //rzuc wyjatek
        }
        return dices.get(numberOfDice).roll();
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }
}
