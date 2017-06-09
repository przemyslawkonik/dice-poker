package model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Pot {

    private IntegerProperty value;

    public Pot(int value) {
        this.value = new SimpleIntegerProperty(value);
    }

    public Pot() {
        this.value = new SimpleIntegerProperty(0);
    }

    public void increase(int value) {
        int tmp = this.getValue() + value;
        this.value.setValue(tmp);
    }

    public void decrease(int value) {
        int tmp = this.getValue() + value;
        if(tmp < 0) {
            this.setValue(0);
        } else {
            this.setValue(tmp);
        }
    }

    public int getValue() {
        return value.getValue();
    }

    public void setValue(int value) {
        if(value < 0) {
            this.value.setValue(0);
        } else {
            this.value.setValue(value);
        }
    }

    public IntegerProperty valueProperty() {
        return value;
    }
}
