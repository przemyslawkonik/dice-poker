package model.dice;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public enum Style {

    MARKED("marked"),
    UNMARKED("unmarked");

    private String style;

    private Style(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return style;
    }
}
