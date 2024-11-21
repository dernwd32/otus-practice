package arrays;

import java.util.*;

public class CollectionUtilsUniqueValues {
    public static <T> Collection<T> removeDuplicates(Collection<T> inputCollection) {
        Set<Integer> uniqueColl = new HashSet<>();
        inputCollection.forEach(x -> uniqueColl.add((Integer) x));
        return (Collection<T>) uniqueColl;
       // return List.of();
    }


    public static void main(String[] args) {
        System.out.println("При вводе коллекции (1, 2, 3, 3, 2, 4) метод должен вернуть (1, 2, 3, 4) - "
                + CollectionUtilsUniqueValues.removeDuplicates(List.of(1, 2, 3, 3, 2, 4)));
    }
}