package arrays;

import java.util.*;

public class CollectionUtilsCount {
    public static <K> Map<K, Integer> countElements(K[] inputArray) {

        Map<K, Integer> counterNumbers = new HashMap<K, Integer>();

        Arrays.stream(inputArray).forEach(
                x -> {
                    if(!counterNumbers.containsKey(x))
                        counterNumbers.put(x, 1);
                    else
                        counterNumbers.put(x, counterNumbers.get(x) + 1);
                }
        );

        //Arrays.stream(inputArray).forEach(

        return counterNumbers;
    }


    public static void main(String[] args) {
        System.out.println("При вводе массива [1, 2, 4, 1, 1, 1, 2, 3, 2], " +
                "метод должен вернуть [\"1\", 4, \"2\", 3, \"4\", 1, \"3\", 1] - "
                + CollectionUtilsCount.countElements(new String[]{"1", "2", "4", "1", "1", "1", "2", "3", "2"}));
    }
}