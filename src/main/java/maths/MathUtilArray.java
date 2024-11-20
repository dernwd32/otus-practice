package maths;

public class MathUtilArray {

    public static String getEvenNumbersUpTo(byte number) {

        if (number==0) return "1";
        if (number<0) return "";

        String str = "1";

        for (int i = 1; i < number; i++)
            if (i%2==0) str = str + i;

        str = str + number;

        return str;
    }


    public static void main(String[] args) {
        System.out.println("Если передать число 5, то метод должен вернуть строку 1245 - "
                + MathUtilArray.getEvenNumbersUpTo((byte) 5));
    }
}