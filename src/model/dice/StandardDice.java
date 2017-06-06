package model.dice;

import java.util.Random;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class StandardDice extends Dice {

    public StandardDice() {
        super(6);
    }

    @Override
    protected void roll() {
        int rnd = new Random().nextInt(mesh)+1;
        value.setValue(""+rnd);
    }
}
