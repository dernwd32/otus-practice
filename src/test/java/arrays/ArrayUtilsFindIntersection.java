package arrays;

import java.util.Arrays;

public class ArrayUtilsFindIntersection {


    public static int[] findIntersection(int[] rightArray, int[] leftArray) {
        //TODO
        int[] arIS = Arrays.stream(rightArray)
                .filter(
                        item -> Arrays.stream(leftArray).anyMatch(item2 -> item2 == item)
                ).toArray();

         return arIS;
    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе массивов [1,4,7,3,8] и [9,12,7,4] метод должен вернуть массив [4,7] - '"
                        + Arrays.toString(ArrayUtilsFindIntersection.findIntersection(
                                new int[]{1, 4, 7, 3, 8}, new int[]{9, 12, 7, 4}))
                        + "'"
        );
    }
}