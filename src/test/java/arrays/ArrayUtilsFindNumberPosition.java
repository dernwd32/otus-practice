package arrays;

import java.util.*;

public class ArrayUtilsFindNumberPosition {
    public static int[] findNumberPosition(int[][] input, int number) {
        // TODO
        int[] answer = new int[2];

        for (int i = 0; i < input.length; i++) {
            for (int k = 0; k < input[i].length; k++){
                if (input[i][k] == number) {
                   // System.out.println(i + " " + k);
                    answer[0] = i;
                    answer[1] = k;
                }
            }
        }
        return answer;
    }
    private ArrayUtilsFindNumberPosition() {

    }

    public static void main(String[] args) {
        int[][] input = new int[][] {{5,7,3,17}, {7,0,1,12}};
        System.out.println(
                "При вводе массива [[5, 7, 3, 17], [7, 0, 1, 12]] и числа 7, метод должен вернуть массив [1, 0] - '"
                        + Arrays.toString(ArrayUtilsFindNumberPosition.findNumberPosition(input, 7))
                        + "'"
        );
    }
}