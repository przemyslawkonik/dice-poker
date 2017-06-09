package model.player;

import model.combination.Arrangement;
import model.dice.DiceBox;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Player {

    private final DiceBox diceBox;
    private final Arrangement arrangement;
    private final Money money;

    public Player(DiceBox diceBox, Arrangement arrangement, Money money) {
        this.diceBox = diceBox;
        this.arrangement = arrangement;
        this.money = money;

        this.arrangement.setDiceBox(this.diceBox);
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public Money getMoney() {
        return money;
    }
}
