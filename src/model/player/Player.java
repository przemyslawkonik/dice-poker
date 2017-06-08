package model.player;

import model.combination.Arrangement;
import model.dice.DiceBox;
import model.money.Money;
import model.pot.Pot;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Player {

    private DiceBox diceBox;
    private Arrangement arrangement;
    private Money money;

    public Player(DiceBox diceBox, Arrangement arrangement, Money money) {
        this.diceBox = diceBox;
        this.arrangement = arrangement;
        this.money = money;
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public void setDiceBox(DiceBox diceBox) {
        this.diceBox = diceBox;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
