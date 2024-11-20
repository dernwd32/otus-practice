package strings;

public class ConditionalUtilsFlatNumber {
    public static boolean isDescendingNumber(int inputNumber) {
        Integer tmpCheckIt = Math.abs(inputNumber);
        String checkIt = tmpCheckIt.toString();
        // if (checkIt.charAt(0)=='-') checkIt = checkIt.substring(1); //по модулю
        boolean status = false;
        int countN = 3;

        //если число не трёхзначное, возвращаем ложь
        if (checkIt.length() != countN) return false;

        for (int i=1; i < countN; i++) {

            if ( checkIt.charAt(i) == checkIt.charAt(i-1) )
                  status = true;
            else
                  return false;


        }

        return status;
    }

    private ConditionalUtilsFlatNumber() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе числа 777 метод должен вернуть true - "
                + ConditionalUtilsFlatNumber.isDescendingNumber(777));
    }
}