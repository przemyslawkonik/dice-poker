package model;

import model.value.Value;
import model.value.ValueImpl;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Player {

    private Money money;

    public Player() {
        this.money = new Money(new ValueImpl(0));
    }

    public Player(Value money) {
        this.money = new Money(money);
    }

    public void bet(Value money, Pot pot) {
        if(this.money.value().get() > money.get()) {
            pot.value().increase(money);
            this.money.value().decrease(money);
        }
    }

    public void bet(int money, Pot pot) {
        if(this.money.value().get() > money) {
            pot.value().increase(money);
            this.money.value().decrease(money);
        }
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
