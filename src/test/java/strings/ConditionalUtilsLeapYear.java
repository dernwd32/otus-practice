package strings;

public class ConditionalUtilsLeapYear {
    public static boolean isLeapYear(int inputYear) {
        //TODO


//       if  (inputYear%400==0) return true;
//       else if (inputYear%100==0) return false;
//       else if (inputYear%4==0) return true;
//       else return false;

        if  (inputYear%400==0 || (inputYear%4==0 && inputYear%100!=0)) return true;
        else return false;

    }


    public static void main(String[] args) {
        System.out.println("При вводе числа 2004, метод должен вернуть true - "
                + ConditionalUtilsLeapYear.isLeapYear(2100));
    }
}