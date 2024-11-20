package maths;

public class FunctionUtilsHexToBin {
    public static String hexToBin(String hexBase) {
        //TODO
        int dec = Integer.parseInt(hexBase, 16);
        String bin = Integer.toBinaryString(dec);
        return bin;
    }
    public static void main(String[] args) {
        System.out.println(
                "При вводе числа 17, метод должен вернуть 10111 - '"
                        + FunctionUtilsHexToBin.hexToBin("17")
                        + "'"
        );
    }
}