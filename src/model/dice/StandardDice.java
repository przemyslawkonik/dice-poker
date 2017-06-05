package model.dice;

import java.util.Random;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class StandardDice extends Dice {

    public StandardDice() {
        super(6);
    }

    @Override
    public int roll() {
        return result = new Random().nextInt(mesh)+1;
    }
}
