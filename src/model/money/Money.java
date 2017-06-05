package model.money;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Money {

    private int value;

    public Money() {
        this.value = 0;
    }

    public Money(Money money) {
        this.value = money.getValue();
    }

    public Money(int value) {
        this.value = value;
    }

    public void increase(int value) {
        this.value += value;
    }

    public void increase(Money money) {
        this.value += money.getValue();
    }

    public void decrease(int value) {
        this.value -= value;
    }

    public void decrease(Money money) {
        this.value -= money.getValue();
    }

    public void set(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
