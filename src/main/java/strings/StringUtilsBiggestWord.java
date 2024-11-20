package strings;

public class StringUtilsBiggestWord {
    public static String getLargest(String input) {
        // TODO
        String[] arWords = input.split(" ");
        String biggest = "";
        for (String word : arWords)
            if (word.length() >= biggest.length())
                biggest = word;

        return biggest;
    }

    private StringUtilsBiggestWord() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе строки \"самое большое слово\" метод должен вернуть \"большое\" - "
                + StringUtilsBiggestWord.getLargest("самое большое слово"));
    }
}