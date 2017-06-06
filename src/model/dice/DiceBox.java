package model.dice;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class DiceBox {

    private final List<Dice> dices;

    public DiceBox(int diceAmount) {
        dices = new LinkedList<>();
        initList(diceAmount);
    }

    public void rollAll() {
        for(Dice d : dices) {
            d.roll();
        }
    }

    public int getDiceAmount() {
        return dices.size();
    }

    private void initList(int numberOfDices) {
        for(int i=0; i<numberOfDices; i++) {
            dices.add(new StandardDice());
        }
    }

    public List<Dice> getDices() {
        return dices;
    }
}
