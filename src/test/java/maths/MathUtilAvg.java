package maths;

public class MathUtilAvg {
    public static int getPrimeNumbersSum(byte lowEdge, byte highEdge) {

        if (lowEdge>highEdge || lowEdge<0 || highEdge<0 ) return 0;

        int evenCount = 0;
        int evenSum = 0;

        for (int i = lowEdge; i <= highEdge; i++){
            if (i%2==0) {
                evenSum = evenSum + i;
                evenCount++;

                System.out.println(i + " => " + evenSum +" / "+ evenCount + " = " + (evenSum/evenCount));
            }
        }

        return (evenSum/evenCount);
    }


    public static void main(String[] args) {
        System.out.println("avg четных чисел у ряда с 1 до 10 должен равняться 6 - "
                + MathUtilAvg.getPrimeNumbersSum((byte) 1, (byte) 10));
    }

}