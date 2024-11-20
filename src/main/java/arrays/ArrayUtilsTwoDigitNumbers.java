package arrays;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtilsTwoDigitNumbers {
    public static int[] fillArrayByTwoDigitNumbers(int[] inputArray) {
        // TODO

//        for (int elem : inputArray){
//
//            elem = new Random().ints(10, 100)
//                    .findFirst()
//                    .getAsInt();
//            System.out.println(elem);
//        }
//        return inputArray;

        return new Random().ints(10, 100)
                .limit(inputArray.length).toArray();



    }
    private ArrayUtilsTwoDigitNumbers() {

    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе массива длиной 5, метод должен вернуть массив случайных двузначных чисел - '"
                        + Arrays.toString(ArrayUtilsTwoDigitNumbers.fillArrayByTwoDigitNumbers(new int[5]))
                        +"'"
        );
    }
}