package model.combination;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.dice.Dice;
import model.dice.DiceBox;
import model.dice.State;
import tools.Finder;

import java.util.*;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Arrangement {

    private ObjectProperty<Combination> combination;
    private DiceBox diceBox;

    public Arrangement() {
        combination = new SimpleObjectProperty<>(Combination.NOTHING);
        diceBox = new DiceBox();
    }

    public Arrangement(DiceBox diceBox) {
        combination = new SimpleObjectProperty<>(Combination.NOTHING);
        this.diceBox = diceBox;
    }

    public void setDiceBox(DiceBox diceBox) {
        this.diceBox = diceBox;
    }

    public void setCombination(Combination combination) {
        this.combination.set(combination);
    }

    public Combination getCombination() {
        return combination.get();
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public int getTotalCombinationWorth() {
        return calculateTotalCombinationWorth();
    }

    public int getCombinationWorth() { return combination.getValue().getWorth();
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
            combination.setValue(Combination.FOUR_OF_A_KIND);
        else if (isStraightFlush(map))
            combination.setValue(Combination.STRAIGHT_FLUSH);
        else
            combination.setValue(Combination.NOTHING);

        return combination.getValue();
    }

    //kluczem mapy jest liczba oczek, a wartoscia ilosc wystapien
    private Map<Integer, Integer> sort() {
        Map<Integer, Integer> map = new HashMap<>();
        List<Dice> dices = diceBox.getDices();

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


    public void markDicesInCombination() {
        Map<Integer, Integer> map = sort();
        List<Dice> dices = diceBox.getDices();

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
            case FOUR_OF_A_KIND: {
                int key = Finder.findKey(4, map);
                for (Dice d : dices) {
                    if (d.getValue() == key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            case THREE_OF_A_KIND: {
                int key = Finder.findKey(3, map);
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
                int key = Finder.findKey(1, map);
                for(Dice d : dices) {
                    if(d.getValue() != key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
            case ONE_PAIR: {
                int key = Finder.findKey(2, map);
                for (Dice d : dices) {
                    if (d.getValue() == key) {
                        d.setState(State.MARKED);
                    }
                }
                break;
            }
        }
    }

    private int calculateTotalCombinationWorth() {
        Map<Integer, Integer> map = sort();
        switch (combination.getValue()) {
            case STRAIGHT_FLUSH: {
                int key = Finder.findKey(5, map);
                return key*combination.getValue().getWorth();
            }
            case FULL_HOUSE: {
                int key1 = Finder.findKey(3, map);
                int key2 = Finder.findKey(2, map);
                if(key1 > key2) {
                    return key1*combination.getValue().getWorth() + key2;
                } else {
                    return key2*combination.getValue().getWorth() + key1;
                }
            }
            case BIG_STRAIGHT: {
                return combination.getValue().getWorth();
            }
            case SMALL_STRAIGHT: {
                return combination.getValue().getWorth();
            }
            case FOUR_OF_A_KIND: {
                int key = Finder.findKey(4, map);
                return key*combination.getValue().getWorth();
            }
            case THREE_OF_A_KIND: {
                int key = Finder.findKey(3, map);
                return key*combination.getValue().getWorth();
            }
            case TWO_PAIR: {
                List<Integer> keys = Finder.findKeys(2, map);
                if(keys.get(0) > keys.get(1)) {
                    return keys.get(0)*combination.getValue().getWorth() + keys.get(1);
                } else {
                    return keys.get(1)*combination.getValue().getWorth() + keys.get(0);
                }
            }
            case ONE_PAIR: {
                int key = Finder.findKey(2, map);
                return key*combination.getValue().getWorth();
            }
        }
        return 0;
    }
}
