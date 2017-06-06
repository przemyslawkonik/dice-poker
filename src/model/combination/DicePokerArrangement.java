package model.combination;

import javafx.beans.property.*;
import model.dice.Dice;
import model.dice.DiceBox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class DicePokerArrangement implements Arrangement {

    private ObjectProperty<Combination> combination;
    private List<IntegerProperty> dicesValues;

    public DicePokerArrangement(DiceBox diceBox) {
        combination = new SimpleObjectProperty<>(Combination.NOTHING);
        dicesValues = new LinkedList<>();
        bindValues(diceBox);
    }

    private void bindValues(DiceBox diceBox) {
        List<Dice> diceList = diceBox.getDices();
        for(int i=0; i<diceList.size(); i++) {
            dicesValues.add(new SimpleIntegerProperty());
            dicesValues.get(i).bind(diceList.get(i).valueProperty());
        }
    }

    @Override
    public ObjectProperty<Combination> combinationProperty() {
        return combination;
    }

    @Override
    public Combination calculate() {
        Map<Integer, Integer> map = sort();

        if (isStraightFlush(map))
            combination.setValue(Combination.STRAIGHT_FLUSH);
        else if (isQuads(map))
            combination.setValue(Combination.QUADS);
        else if (isFullHouse(map))
            combination.setValue(Combination.FULL_HOUSE);
        else if (isBigStraight(map))
            combination.setValue(Combination.BIG_STRAIGHT);
        else if (isSmallStraight(map))
            combination.setValue(Combination.SMALL_STRAIGHT);
        else if (isThreeOfAKind(map))
            combination.setValue(Combination.THREE_OF_A_KIND);
        else if (isTwoPair(map))
            combination.setValue(Combination.TWO_PAIR);
        else if (isPair(map))
            combination.setValue(Combination.ONE_PAIR);
        else
            combination.setValue(Combination.NOTHING);
        return combination.getValue();
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

}
