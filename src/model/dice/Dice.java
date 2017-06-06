package model.dice;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public abstract class Dice {

    protected IntegerProperty value;
    protected final int mesh;
    protected BooleanProperty mark;

    public Dice(int mesh) {
        value = new SimpleIntegerProperty();
        this.mesh = mesh;
        mark = new SimpleBooleanProperty(false);
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

    public boolean isMark() {
        return mark.get();
    }

    public BooleanProperty markProperty() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark.set(mark);
    }
}
