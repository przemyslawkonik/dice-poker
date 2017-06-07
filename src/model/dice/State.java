package model.dice;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public enum State {

    MARKED("marked"),
    UNMARKED("unmarked");

    private String state;

    private State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
