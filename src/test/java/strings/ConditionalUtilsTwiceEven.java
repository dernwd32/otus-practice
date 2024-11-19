package strings;

public class ConditionalUtilsTwiceEven {
    public static boolean isDoublePrime(int inputNumber) {
        // TODO
        inputNumber = Math.abs(inputNumber);
        int summa = 0;
        int proizv = 1;
        int countN = 3;

        //в строке должно быть countN знаков, иначе возвращаем ложь
        //if (inputNumber < (int) Math.pow(10,countN-1) || inputNumber > (int) Math.pow(10,countN) ) return false;
        Integer checkIt = inputNumber;
        if (checkIt.toString().length() != countN) return false;

        for (int i=0; i < countN; i++) {
                 summa = summa + Character.getNumericValue(checkIt.toString().charAt(i));
                 proizv = proizv * Character.getNumericValue(checkIt.toString().charAt(i));
        }

        System.out.println(summa +" " + proizv);
        if (summa%2==0 && proizv%2==0) return true;
        else return false;
    }


    public static void main(String[] args) {
        System.out.println("При вводе числа 222 метод должен вернуть true - "
                + ConditionalUtilsTwiceEven.isDoublePrime(-224));
    }
}