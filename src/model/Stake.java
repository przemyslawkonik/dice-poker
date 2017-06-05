package model;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Stake {

    private int value;

    public Stake() {
        value = 0;
    }

    public Stake(int value) {
        this.value = value;
    }

    public void increase(int value) {
        this.value += value;
    }

    public void decrease(int value) {
        this.value -= value;
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
