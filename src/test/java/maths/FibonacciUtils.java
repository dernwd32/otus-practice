package maths;//import java.math.BigInteger;

public class FibonacciUtils {
    public static String getFibonacciSum(byte limit) {
                int[] fib = new int[limit];
        fib[0] = 0;
        fib[1] = 1;
        Integer sum = fib[0] + fib[1];
        for (int i = 2; i < limit; i++){
            fib[i] = fib[i-1] + fib [i-2];
            sum = sum + fib[i];
        }

        return sum.toString();
    }


    public static void main(String[] args) {
        System.out.println("Сумма чисел фибоначчи до 5 должна равняться 7 - " + FibonacciUtils.getFibonacciSum((byte) 5));
    }
}