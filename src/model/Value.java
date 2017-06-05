package model;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Value {

    private int value;

    public Value() {
        this.value = 0;
    }

    public Value(Value value) {
        this.value = value.get();
    }

    public Value(int value) {
        this.value = value;
    }

    public void increase(int value) {
        this.value += value;
    }

    public void increase(Value value) {
        this.value += value.get();
    }

    public void decrease(int value) {
        this.value -= value;
    }

    public void decrease(Value value) {
        this.value -= value.get();
    }

    public void set(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
