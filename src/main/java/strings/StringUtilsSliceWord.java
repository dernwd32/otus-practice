package strings;

public class StringUtilsSliceWord {
    public static String getSlice(String input, int leftEdge, int rightEdge) {
        // TODO
        if (rightEdge>input.length()) rightEdge = input.length();
        if (leftEdge>input.length()) return "";
        if (leftEdge>rightEdge) return "";
       // System.out.println(leftEdge+" "+rightEdge);
        return input.substring(leftEdge,rightEdge);
    }

    private StringUtilsSliceWord() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе строки \"Test\" с индексами 1 и 4 метод должен вернуть \"est\" - "
                + "\"" + StringUtilsSliceWord.getSlice("Test", 1,4) +"\"");

    }
}