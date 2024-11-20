package strings;

public class StringUtilsPalindrome {
    public static boolean isPalindrome(String input) {
        // TODO
        String cleanedString = "";
        String reversedString = "";
        for (int i=0; i < input.length(); i++) {
            // если нужны пробелы, то еще || input.charAt(i) == ' '
            if (Character.isLetter(input.charAt(i)) ) {
                cleanedString = cleanedString + input.toLowerCase().charAt(i);
                reversedString = input.toLowerCase().charAt(i) + reversedString;
            }
        }
        // System.out.println(cleanedString);
        //System.out.println(reversedString);

        //второе условие - проверка, что исходная строка содержит хоть одну букву
        if (reversedString.equals(cleanedString) && reversedString.length()>0) return true;
        else return false;
    }

    private StringUtilsPalindrome() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе строки \"шалаш\" метод должен вернуть true - "
                + StringUtilsPalindrome.isPalindrome("12354 Лёша на по2314лке клопа на;%№№шёл"));
    }
}