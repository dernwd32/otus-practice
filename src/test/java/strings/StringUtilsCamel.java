package strings;

public class StringUtilsCamel {
    public static String toCamelCase(String input) {

        StringBuilder newCamelString = new StringBuilder();
        boolean toUpperNext = false;

        String[] arNewStr = input.split("");

        for (String thisChar : arNewStr) {

            if (Character.isLetter(thisChar.charAt(0)) || thisChar.equals(" ")) {

                if (toUpperNext == true) thisChar = thisChar.toUpperCase();

                if (thisChar.equals(" ") ) {
                    toUpperNext = true;
                    thisChar = "";
                }
                else toUpperNext = false;

                /*
                обработка неучтённого выше случая, когда до первой буквы в строке стоит пробел
                и случая когда в исходной строке первая буква уже заглавная
                */
                if (newCamelString.isEmpty()) thisChar = thisChar.toLowerCase();

                newCamelString.append(thisChar);
            }
        }

        return newCamelString.toString();

//        String newStr = "";
//        String[] arNewStr = input.split(" ");
//        for (int k=0; k < arNewStr.length; k++) {
//
//            if (k!=0) arNewStr[k] = arNewStr[k].substring(0, 1).toUpperCase() + arNewStr[k].substring(1);
//            for (int i = 0; i < arNewStr[k].length(); i++)
//                if (Character.isLetter(arNewStr[k].charAt(i)))
//                    newStr = newStr + arNewStr[k].charAt(i);
//        }
//        return newStr;
    }



    public static void main(String[] args) {
        System.out.println("Строка \"my camel * case string 1\" должна преобразоваться в myCamelCaseString - "
                + StringUtilsCamel.toCamelCase(" 2 My c$amel  * &case str.^2ing 1"));
    }
}