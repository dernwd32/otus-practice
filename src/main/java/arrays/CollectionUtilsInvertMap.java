package arrays;

import java.util.*;

public class CollectionUtilsInvertMap {
    public static <K, V> Map<V, K> invertMap(Map<? extends K, ? extends V> inputMap) {
        Map<V,K> newMap = new HashMap<V,K>();
        inputMap.forEach((key, value) -> newMap.put(value, key));
        return newMap;
    }

    public static void main(String[] args) {
        System.out.println("При вводе Map {1=a, 2=b, 3=c} метод должен вернуть {a=1, b=2, c=3} - '"
                + CollectionUtilsInvertMap.invertMap(Map.of(1, "a", 2, "b", 3, "c")) + "'");
    }
}