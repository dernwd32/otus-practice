package arrays;

import java.util.Arrays;

public class ArrayUtilsShiftElements {
    public static int[] shiftElements(int[] inputArray) {
        // TODO
        int len = inputArray.length;
        int shift = -1;
        //Корректировка при сдвиге за границу массива. Правый сдвиг = -len, левый сдвиг = +len
        int shiftCorr = (-1) * (shift / Math.abs(shift) ) * len ;
        int[] arShifted = new int[len];
        int newIndex = 0;

        for (int i = 0; i < len; i++) {
            newIndex = i + shift; //свдиг
            if ( newIndex < 0 || newIndex > len - 1) newIndex += shiftCorr;
            //if (newIndex < 0) newIndex += len; //левый сдвиг за границей массива
            //if (newIndex > len - 1) newIndex -= len; //правый сдвиг за границей массива
            arShifted[newIndex] = inputArray[i];
            System.out.println(i + "=>" + newIndex);
        }

        return arShifted;
    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе массива [1, 2, 3, 4, 5], метод должен вернуть массив [2, 3, 4, 5, 1] - '"
                        + Arrays.toString(ArrayUtilsShiftElements.shiftElements(new int[]{1, 2, 3, 4, 5}))
                        + "'"
        );
    }
}