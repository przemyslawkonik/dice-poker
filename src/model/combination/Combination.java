package model.combination;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public enum Combination {

    STRAIGHT_FLUSH("Straight flush", 8),
    QUADS("Quads", 7),
    FULL_HOUSE("Full house", 6),
    BIG_STRAIGHT("Big straight", 5),
    SMALL_STRAIGHT("Small straight", 4),
    THREE_OF_A_KIND("Three of a kind", 3),
    TWO_PAIR("Two pairs", 2),
    ONE_PAIR("One pair", 1),
    NOTHING("Nothing", 0);

    private String combination;
    private int worth;

    private Combination(String combination, int worth) {
        this.combination = combination;
        this.worth = worth;
    }

    @Override
    public String toString() {
        return combination;
    }

    public int getWorth() {
        return worth;
    }
}
