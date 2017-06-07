package model.dice;

import javafx.beans.property.*;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public abstract class Dice {

    protected IntegerProperty value;
    protected final int mesh;
    protected BooleanProperty mark;
    protected ObjectProperty<Style> style;

    public Dice(int mesh) {
        value = new SimpleIntegerProperty();
        this.mesh = mesh;
        mark = new SimpleBooleanProperty(false);
        style = new SimpleObjectProperty<>(Style.UNMARKED);
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

    public Style getStyle() {
        return style.get();
    }

    public ObjectProperty<Style> styleProperty() {
        return style;
    }

    public void setStyle(Style style) {
        this.style.set(style);
    }
}
