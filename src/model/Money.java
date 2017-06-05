package model;

import model.value.Value;
import model.value.ValueImpl;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Money {

    private Value value;

    public Money() {
        this.value = new ValueImpl(0);
    }

    public Money(Value value) {
        this.value = new ValueImpl(value);
    }

    public Value value() {
        return value;
    }
}
