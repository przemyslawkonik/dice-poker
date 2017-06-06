package model.dice;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public abstract class Dice {

    protected StringProperty value;
    protected final int mesh;

    public Dice(int mesh) {
        value = new SimpleStringProperty();
        this.mesh = mesh;
    }

    protected abstract void roll();

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public int getMesh() {
        return mesh;
    }
}
