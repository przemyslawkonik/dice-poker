package model.value;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public interface Value {
    void increase(int value);
    void increase(Value value);
    void decrease(int value);
    void decrease(Value value);
    void set(int value);
    void set(Value value);
    int get();
}
