package model.dice;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ToggleButton;

import java.util.Random;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Dice extends ToggleButton {

    private IntegerProperty value;
    private ObjectProperty<State> state;
    private final int maxValue;

    public Dice() {
        maxValue = 6;
        initVariables();
        initBindings();
    }

    public int roll() {
        value.set(new Random().nextInt(maxValue)+1);
        return value.getValue();
    }

    private void initVariables() {
        value = new SimpleIntegerProperty();
        state = new SimpleObjectProperty<>(State.UNMARKED);

    }

    private void initBindings() {
        this.textProperty().bind(this.value.asString());
        this.idProperty().bind(this.state.asString());
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public State getState() {
        return state.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public void setState(State state) {
        this.state.set(state);
    }
}
