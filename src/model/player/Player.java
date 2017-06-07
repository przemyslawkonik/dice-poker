package model.player;

import model.combination.Arrangement;
import model.dice.DiceBox;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Player {

    private final DiceBox diceBox;
    private final Arrangement arrangement;

    public Player(DiceBox diceBox, Arrangement arrangement) {
        this.diceBox = diceBox;
        this.arrangement = arrangement;

        this.arrangement.setDices(this.diceBox.getDices());
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

}
