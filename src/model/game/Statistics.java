package model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextArea;
import org.omg.CORBA.portable.*;
import org.omg.CORBA.portable.OutputStream;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class Statistics {

    private IntegerProperty won;
    private IntegerProperty lost;
    private IntegerProperty draw;

    public Statistics() {
        this.won = new SimpleIntegerProperty();
        this.lost = new SimpleIntegerProperty();
        this.draw = new SimpleIntegerProperty();
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
