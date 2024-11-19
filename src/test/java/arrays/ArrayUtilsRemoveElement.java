package arrays;//import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtilsRemoveElement {

    public static int[] removeElement(int[] nums, int val) {
        // TODO
        int[] tmp = nums;
        return Arrays.stream(nums).filter(item->item!=val).toArray();

/*
        ArrayList<Integer> list = new ArrayList<>();
        for (int elem : nums)
            if (elem != val)
                list.add(elem);

        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);

        return array;
 */

    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе массива [1, 1, 2, 3, 1] и числа 1, метод должен вернуть [2, 3] - '"
                        + Arrays.toString(ArrayUtilsRemoveElement.removeElement(
                                new int[]{1, 1, 2, 3, 1}, 1))
                        + "'"
        );
    }
}