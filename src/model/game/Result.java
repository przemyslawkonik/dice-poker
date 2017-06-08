package model.game;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public enum Result {

    NO_RESULT("Empty"),
    WIN("Win"),
    LOST("Lost"),
    DRAW("Draw");

    private String result;

    private Result(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}
