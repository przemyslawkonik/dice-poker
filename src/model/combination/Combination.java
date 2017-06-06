package model.combination;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public enum Combination {

    STRAIGHT_FLUSH("Straight flush"),
    QUADS("Quads"),
    FULL_HOUSE("Full house"),
    BIG_STRAIGHT("Big straight"),
    SMALL_STRAIGHT("Small straight"),
    THREE_OF_A_KIND("Three of a kind"),
    TWO_PAIR("Two pairs"),
    ONE_PAIR("One pair"),
    NOTHING("Nothing");

    private String combination;

    private Combination(String combination) {
        this.combination = combination;
    }

    @Override
    public String toString() {
        return combination;
    }
}
