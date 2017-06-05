package model.dice;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public abstract class Dice {

    protected final int mesh;
    protected int result;

    public Dice(int mesh) {
        this.mesh = mesh;
        result = 0;
    }

    public abstract int roll();

    public int getMesh() {
        return mesh;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
