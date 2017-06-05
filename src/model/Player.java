package model;

import model.dice.DiceBox;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Player {

    private Value money;
    private DiceBox diceBox;

    public Player() {
        this.money = new Value(0);
        diceBox = new DiceBox(6);
    }

    public Player(Value money) {
        this.money = new Value(money);
        diceBox = new DiceBox(6);
    }

    public void bet(Value money, Pot pot) {
        if(this.money.get() > money.get()) {
            pot.getValue().increase(money);
            this.money.decrease(money);
        }
    }

    public Value getMoney() {
        return money;
    }

    public void setMoney(Value money) {
        this.money = money;
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public void setDiceBox(DiceBox diceBox) {
        this.diceBox = diceBox;
    }
}
