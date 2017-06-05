package model;

import model.money.Money;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Pot {

    private Money money;

    public Pot() {
        this.money = new Money(0);
    }

    public Pot(Money money) {
        this.money = new Money(money);
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
