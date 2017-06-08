package model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class Statistics {

    private IntegerProperty won;
    private IntegerProperty lost;
    private IntegerProperty draw;

    public Statistics() {
        this.won = new SimpleIntegerProperty(0);
        this.lost = new SimpleIntegerProperty(0);
        this.draw = new SimpleIntegerProperty(0);
    }

    public void increaseWon() {
        int tmp = won.get();
        tmp++;
        won.set(tmp);
    }

    public void increaseLost() {
        int tmp = lost.get();
        tmp++;
        lost.set(tmp);
    }

    public void increaseDraw() {
        int tmp = draw.get();
        tmp++;
        draw.set(tmp);
    }

    public void reset() {
        won.set(0);
        lost.set(0);
        draw.set(0);
    }

    public int getWon() {
        return won.get();
    }

    public IntegerProperty wonProperty() {
        return won;
    }

    public void setWon(int won) {
        this.won.set(won);
    }

    public int getLost() {
        return lost.get();
    }

    public IntegerProperty lostProperty() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost.set(lost);
    }

    public int getDraw() {
        return draw.get();
    }

    public IntegerProperty drawProperty() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw.set(draw);
    }

    public void add(Result result) {
        switch (result) {
            case WIN: {
                increaseWon();
                break;
            }
            case LOST: {
                increaseLost();
                break;
            }
            case DRAW: {
                increaseDraw();
                break;
            }
        }
    }
}
