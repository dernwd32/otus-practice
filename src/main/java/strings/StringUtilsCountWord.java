package strings;

public class StringUtilsCountWord {
    public static int countWord(String input, String word) {
        // TODO
        int i = 0;
        input = input.toLowerCase();
        word = word.toLowerCase();
        while (input.length()>0 && input.contains(word)) {
            input = input.replaceFirst(word, "");
            i++;
        }
        return i;
    }

    private StringUtilsCountWord() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе строки \"Test string for test\" и искомом слове \"test\" метод должен вернуть 2 - "
                + StringUtilsCountWord.countWord("Test string for test", "Test"));
    }
}