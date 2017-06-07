package model.dice;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class DiceBox {

    private List<Dice> dices;

    public DiceBox(List<Dice> dices) {
        this.dices = dices;
    }

    public DiceBox() {
        dices = new LinkedList<>();
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void roll(int dice) {
        if(dice < 0 || dice > dices.size()) {
            throw new IndexOutOfBoundsException();
        }
        dices.get(dice).roll();
    }

    public void rollAll() {
        for(Dice d : dices) {
            d.roll();
        }
    }

    public void rollSelected() {
        for(Dice d : dices) {
            if(d.isSelected()) {
                d.roll();
            }
        }
    }

    public void setSelectedAll(boolean selected) {
        for(Dice d : dices) {
            d.setSelected(selected);
        }
    }

    public void setVisibleAll(boolean visibility) {
        for(Dice d : dices) {
            d.setVisible(visibility);
        }
    }

    public void setDisableAll(boolean disability) {
        for(Dice d : dices) {
            d.setDisable(disability);
        }
    }

    public void setOpacityAll(double opacity) {
        for(Dice d : dices) {
            d.setOpacity(opacity);
        }
    }

}
