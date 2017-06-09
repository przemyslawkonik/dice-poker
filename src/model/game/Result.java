package model.game;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public enum Result {

    WIN("win"),
    LOST("lost"),
    DRAW("draw");

    private String result;

    private Result(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}
