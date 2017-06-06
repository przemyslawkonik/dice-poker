package model.combination;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dice.Dice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class DicePokerCombination {

    private StringProperty combination;
    private List<IntegerProperty> dicesValues;

    public DicePokerCombination(List<Dice> dices) {
        combination = new SimpleStringProperty();
        dicesValues = new LinkedList<>();
        bindValues(dices);
    }

    private void bindValues(List<Dice> dices) {
        for(int i=0; i<dices.size(); i++) {
            dicesValues.add(new SimpleIntegerProperty());
            dicesValues.get(i).bind(dices.get(i).valueProperty());
        }
    }

    public void calculate() {
        Map<Integer, Integer> map = sort();

        if (isStraightFlush(map))
            combination.setValue(Combination.STRAIGHT_FLUSH.toString());
        else if (isQuads(map))
            combination.setValue(Combination.QUADS.toString());
        else if (isFullHouse(map))
            combination.setValue(Combination.FULL_HOUSE.toString());
        else if (isBigStraight(map))
            combination.setValue(Combination.BIG_STRAIGHT.toString());
        else if (isSmallStraight(map))
            combination.setValue(Combination.SMALL_STRAIGHT.toString());
        else if (isThreeOfAKind(map))
            combination.setValue(Combination.THREE_OF_A_KIND.toString());
        else if (isTwoPair(map))
            combination.setValue(Combination.TWO_PAIR.toString());
        else if (isPair(map))
            combination.setValue(Combination.ONE_PAIR.toString());
        else
            combination.setValue(Combination.NOTHING.toString());
    }

    //kluczem mapy jest liczba oczek, a wartoscia ilosc wystapien
    private Map<Integer, Integer> sort() {
        Map<Integer, Integer> map = new HashMap<>();
        for(IntegerProperty ip : dicesValues) {
            int key = ip.getValue();
            if(map.containsKey(key)) {
                int value = map.get(key);
                value++;
                map.replace(key, value);
            } else {
                map.put(key, 1);
            }
        }
        return map;
    }

    private boolean isStraightFlush(Map<Integer, Integer> map) {
        return (map.containsValue(5));
    }

    private boolean isQuads(Map<Integer, Integer> map) {
        return (map.containsValue(4));
    }

    private boolean isFullHouse(Map<Integer, Integer> map) {
        return (map.containsValue(3) && map.containsValue(2));
    }

    private boolean isBigStraight(Map<Integer, Integer> map) { return (map.size() == 5 && map.containsKey(6) && !map.containsKey(1)); }

    private boolean isSmallStraight(Map<Integer, Integer> map) { return (map.size() == 5 && map.containsKey(1) && !map.containsKey(6)); }

    private boolean isThreeOfAKind(Map<Integer, Integer> map) {
        return (map.containsValue(3) && map.size() == 3);
    }

    private boolean isTwoPair(Map<Integer, Integer> map) {
        return (map.containsValue(2) && map.size() == 3);
    }

    private boolean isPair(Map<Integer, Integer> map) {
        return (map.containsValue(2) && map.size() == 4);
    }

    public StringProperty combinationProperty() {
        return combination;
    }

}
