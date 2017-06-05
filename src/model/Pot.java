package model;

import model.value.Value;
import model.value.ValueImpl;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Pot {

    private Value value;

    public Pot() {
        this.value = new ValueImpl(0);
    }

    public Pot(Value value) {
        this.value = new ValueImpl(value);
    }

    public Value value() {
        return value;
    }
}
