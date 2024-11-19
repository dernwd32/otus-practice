package arrays;

import java.util.*;

public class CollectionUtilsCount {
    public static <K> Map<K, Integer> countElements(K[] inputArray) {
        //TODO
        return Map.of();
    }

    private CollectionUtilsCount() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе массива [1, 2, 4, 1, 1, 1, 2, 3, 2], метод должен вернуть [\"1\", 4, \"2\", 3, \"4\", 1, \"3\", 1] - "
                + CollectionUtilsCount.countElements(new String[]{"1", "2", "4", "1", "1", "1", "2", "3", "2"}));
    }
}