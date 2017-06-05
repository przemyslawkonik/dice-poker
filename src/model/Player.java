package model;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Player {

    private Value money;

    public Player() {
        this.money = new Value(0);
    }

    public Player(Value money) {
        this.money = new Value(money);
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
}
