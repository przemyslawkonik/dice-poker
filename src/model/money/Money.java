package model.money;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public Money() {
        this.value =0;
    }

    public void increase(int value) {
        this.value += value;
    }

    public void decrease(int value) {
        this.value -= value;
        if(value < 0) {
            this.value = 0;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(this.value < 0) {
            this.value = 0;
        }
    }
}
