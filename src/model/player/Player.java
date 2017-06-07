package model.player;

import model.combination.Arrangement;
import model.dice.DiceBox;
import model.money.Money;
import model.pot.Pot;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Player {

    private final DiceBox diceBox;
    private final Arrangement arrangement;
    private Money money;

    public Player(DiceBox diceBox, Arrangement arrangement, Money money) {
        this.diceBox = diceBox;
        this.arrangement = arrangement;

        this.money = new Money(money.getValue());

        this.arrangement.setDices(this.diceBox.getDices());
    }

    public void bet(int value, Pot pot) {
        if(value < money.getValue()) {
            pot.increase(value);
            money.decrease(value);
        } else {
            //gracz nie ma tyle pieniedzy
        }
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

    public void setMoney(Money money) {
        this.money = money;
    }
}
