package maths;

public class Factorial {
    public static float getFactorial(byte number) {
        float x = 1;
        if (number>=0) {
            for (float i = 1; i <= number; i++) {
                x = x * i;
                System.out.println(i + " = " + x);
            }
        }
        else x = 0; // для факториала отрицательного числа

        return x;
    }


    public static void main(String[] args) {
        System.out.println("Факториал от 12 должен равняться 479001600 - " + Factorial.getFactorial((byte) 12));
    }
}

