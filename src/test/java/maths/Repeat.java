package maths;

import java.util.Arrays;

public class Repeat {

    public static int[] generateArrWithFibanachi(int length){
        //TODO
        if ( length<=0 )
            return new int[0];

        int[] arFib = new int[length];
        if ( length<=0 ) return arFib;

        arFib[0] = 1;
        if (length == 1) return arFib;
        arFib[1] = 2;
        if (length == 2) return arFib;

        for (int i = 2; i < length; i++){
           arFib[i] = arFib[i-1] + arFib[i-2];
        }

        return arFib;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateArrWithFibanachi(-1)));
        System.out.println(Arrays.toString(generateArrWithFibanachi(0)));
        System.out.println(Arrays.toString(generateArrWithFibanachi(1)));
        System.out.println(Arrays.toString(generateArrWithFibanachi(2)));
        System.out.println(Arrays.toString(generateArrWithFibanachi(3)));
        System.out.println(Arrays.toString(generateArrWithFibanachi(4)));
    }
}
