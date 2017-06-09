package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Przemysław Konik on 2017-06-09.
 */
public class Finder {

    public static int findKey(int value, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            if (map.get(key) == value)
                return key;
        }
        return 0;
    }

    public static List<Integer> findKeys(int value, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        List<Integer> k = new ArrayList<>();

        for (Integer key : keys) {
            if (map.get(key) == value)
                k.add(key);
        }
        return k;
    }
}
