package model.combination;

import javafx.beans.property.ObjectProperty;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public interface Arrangement {

    public ObjectProperty<Combination> combinationProperty();
    public Combination calculate();
}
