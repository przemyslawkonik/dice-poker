package model.value;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class ValueImpl implements Value {

    private int value;

    public ValueImpl() {

    }

    public ValueImpl(int value) {
        this.value = value;
    }

    public ValueImpl(Value value) {
        this.value = value.get();
    }

    @Override
    public void increase(int value) {
        this.value += value;
    }

    @Override
    public void increase(Value value) {
        this.value += value.get();
    }

    @Override
    public void decrease(int value) {
        this.value -= value;
    }

    @Override
    public void decrease(Value value) {
        this.value -= value.get();
    }

    @Override
    public void set(int value) {
        this.value = value;
    }

    @Override
    public void set(Value value) {
        this.value = value.get();
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
