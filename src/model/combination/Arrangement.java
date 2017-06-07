package model.combination;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import model.dice.Dice;
import model.dice.State;

import java.util.*;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Arrangement extends Label {

    private ObjectProperty<Combination> combination;
    private List<Dice> dices;
    private int combinationValue;

    public Arrangement() {
        initVariables();
        initBindings();
    }

    private void initVariables() {
        combination = new SimpleObjectProperty<>(Combination.NOTHING);
        dices = new LinkedList<>();
    }

    private void initBindings() {
        this.textProperty().bind(this.combinationProperty().asString());
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public ObjectProperty<Combination> combinationProperty() {
        return combination;
    }

    public Combination calculate() {
        Map<Integer, Integer> map = sort();

        if (isPair(map))
            combination.setValue(Combination.ONE_PAIR);
        else if (isTwoPair(map))
            combination.setValue(Combination.TWO_PAIR);
        else if (isThreeOfAKind(map))
            combination.setValue(Combination.THREE_OF_A_KIND);
        else if (isSmallStraight(map))
            combination.setValue(Combination.SMALL_STRAIGHT);
        else if (isBigStraight(map))
            combination.setValue(Combination.BIG_STRAIGHT);
        else if (isFullHouse(map))
            combination.setValue(Combination.FULL_HOUSE);
        else if (isQuads(map))
            combination.setValue(Combination.QUADS);
        else if (isStraightFlush(map))
            combination.setValue(Combination.STRAIGHT_FLUSH);
        else
            combination.setValue(Combination.NOTHING);

        markDices(map);
        setCombinationValue(map);
        return combination.getValue();
    }

    //kluczem mapy jest liczba oczek, a wartoscia ilosc wystapien
    private Map<Integer, Integer> sort() {
        Map<Integer, Integer> map = new HashMap<>();

        for(Dice d : dices) {
            int key = d.getValue();
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


    private void markDices(Map<Integer, Integer> map) {
        unMarkDices();

        switch (combination.getValue()) {
            case STRAIGHT_FLUSH:
            case FULL_HOUSE:
            case BIG_STRAIGHT:
            case SMALL_STRAIGHT: {
                for (Dice d : dices) {
                    d.setState(State.MARKED);
                }
                break;
            }
            case QUADS: {
                int key = findKey(4, map);
                for (Dice d : dices) {
                    if (d.getValue() == key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            case THREE_OF_A_KIND: {
                int key = findKey(3, map);
                for (Dice d : dices) {
                    if (d.getValue() == key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            case TWO_PAIR: {
                /*
                    gdybysmy wyszukali po wartosci 2 to wtedy udaloby sie nam zaznaczyc tylko jedna pare, a potrzebujemy zaznaczyc obie
                    dlatego latwiej wyszukac nie pasujacy klucz i zaznaczyc wszystkie kosci nie pasujace do niego
                */
                int key = findKey(1, map);
                for(Dice d : dices) {
                    if(d.getValue() != key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            case ONE_PAIR: {
                int key = findKey(2, map);
                for (Dice d : dices) {
                    if (d.getValue() == key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            //pusty case poniewaz i tak na poczatku ustawiamy zaznaczenie wszystkich kosci na false
            case NOTHING: {
            }
        }
    }

    private void unMarkDices() {
        for(Dice d : dices) {
            d.setState(State.UNMARKED);
        }
    }

    private int findKey(int value, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            if (map.get(key) == value)
                return key;
        }
        return 0;
    }

    private List<Integer> findKeys(int value, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        List<Integer> k = new ArrayList<>();

        for (Integer key : keys) {
            if (map.get(key) == value)
                k.add(key);
        }
        return k;
    }

    private void setCombinationValue(Map<Integer, Integer> map) {
        switch (combination.getValue()) {
            case STRAIGHT_FLUSH: {
                int key = findKey(5, map);
                combinationValue = key*combination.getValue().getWorth();
                break;
            }
            case FULL_HOUSE: {
                int key = findKey(3, map);
                combinationValue = key*combination.getValue().getWorth();
                break;
            }
            case BIG_STRAIGHT: {
                combinationValue = combination.getValue().getWorth();
                break;
            }
            case SMALL_STRAIGHT: {
                combinationValue = combination.getValue().getWorth();
                break;
            }
            case QUADS: {
                int key = findKey(4, map);
                combinationValue = key*combination.getValue().getWorth();
                break;
            }
            case THREE_OF_A_KIND: {
                int key = findKey(3, map);
                combinationValue = key*combination.getValue().getWorth();
                break;
            }
            case TWO_PAIR: {
                List<Integer> keys = findKeys(2, map);
                if(keys.get(0) > keys.get(1)) {
                    combinationValue = keys.get(0)*combination.getValue().getWorth();
                } else {
                    combinationValue = keys.get(1)*combination.getValue().getWorth();
                }
                break;
            }
            case ONE_PAIR: {
                int key = findKey(2, map);
                combinationValue = key*combination.getValue().getWorth();
                break;
            }
            case NOTHING: {
                combinationValue = combination.getValue().getWorth();
                break;
            }
        }
    }

    public int getCombinationValue() {
        return combinationValue;
    }

    public Combination getCombination() {
        return combination.get();
    }

}
