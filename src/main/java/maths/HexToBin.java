package maths;

public class HexToBin{

    public static void main(String args[]){
        String tmpHex = "17";
        int tmpDec = Integer.parseInt(tmpHex,16);
        String bin = Integer.toBinaryString(tmpDec);
        //int tmpBin = Integer.parseInt(tmpDec,2);

      // int x = Integer.parseInt("9");
      //  double c = Double.parseDouble("5");
       // int to = Integer.parseInt("17",16);

       // System.out.println(x);
       // System.out.println(c);
       // System.out.println("Вот F = " + tmpDec + " = " + tmpBin);
        System.out.println("Вот F = " + bin );
    }
}