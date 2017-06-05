package model;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Pot {

    private Value value;

    public Pot() {
        this.value = new Value(0);
    }

    public Pot(Value value) {
        this.value = new Value(value);
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
