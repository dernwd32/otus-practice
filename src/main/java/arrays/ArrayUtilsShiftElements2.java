package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtilsShiftElements2 {
    public static Integer[] shiftElements(Integer[] inputArray) {
        // TODO
        //var shiftLeftStepOne = Arrays.stream(inputArray).skip(1);
        //var arResult = Arrays.asList(inputArray).add(1);

        // var result = Stream.concat(shiftLeftStepOne, Stream.of(inputArray[0])).toArray();
        //Arrays.stream(inputArray).toList();
        int rotation = -1;
        List<Integer> list = Arrays.asList(inputArray);
        Collections.rotate(list, rotation);
        Integer[] rotatedArray = list.toArray(new Integer[0]);
       // Arrays.stream(rotatedArray).toArray();

        return rotatedArray;
    }
    private ArrayUtilsShiftElements2() {

    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе массива [1, 2, 3, 4, 5], метод должен вернуть массив [2, 3, 4, 5, 1] - '"
                        + Arrays.toString(ArrayUtilsShiftElements2.shiftElements(new Integer[]{1, 2, 3, 4, 5}))
                        + "'"
        );
    }
}