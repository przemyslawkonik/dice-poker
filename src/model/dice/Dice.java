package model.dice;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public abstract class Dice {

    protected IntegerProperty value;
    protected final int mesh;

    public Dice(int mesh) {
        value = new SimpleIntegerProperty();
        this.mesh = mesh;
    }

    protected abstract void roll();

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public int getMesh() {
        return mesh;
    }
}
