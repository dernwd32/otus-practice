package strings;

public class WordUtils {
    public static String reverseWords(String inputText) {
       String[] arWords = inputText.split(" ");
       StringBuilder revWords = new StringBuilder();

        for (int i = arWords.length; i > 0; i--)
            revWords.append(" ").append(arWords[i - 1]);

       return revWords.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("Ввод пустой строки - " + WordUtils.reverseWords(""));
        System.out.println("Ввод одного слова - " + WordUtils.reverseWords("word"));
        System.out.println("Ввод двух слов - " + WordUtils.reverseWords("два слова"));
        System.out.println("Ввод кучи слов - " + WordUtils.reverseWords("сто тыщ и ещё два слова"));

       // System.out.println(arWords);
    }
}