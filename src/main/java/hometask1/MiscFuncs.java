package hometask1;

public class MiscFuncs {

    public static String cutZeroFloat(Float weight) {
        //убираем ноль после точки в целочисленном весе

       // String sWeight = "";
        if (weight == weight.intValue())
            return String.valueOf(weight.intValue());
        else
            return weight.toString();

//        if (weight % 1.0 != 0)
//            sWeight = String.format("%s", weight);
//        else
//            sWeight = String.format("%.0f", weight);

      //  return sWeight;
    }

    public static String agePostfix(Integer age) {
        int lastDigit = age % 10;
        int lastTwoDigits = age % 100;
        if ( lastDigit == 0 || lastDigit >= 5 || (lastTwoDigits >= 11 && lastTwoDigits <= 14))  return "лет";
        else if (lastDigit == 1) return "год";
        else return "года";
        // else if (lastDigit > 1) return "года";
    }

    public static String firstCapitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
