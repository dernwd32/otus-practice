package strings;

public class ConditionalUtilsCountInner {

    public static int countInnerNumber(int base, int checkNumber) {
        // TODO
        String text = Integer.toString(base);
        String match = Integer.toString(checkNumber);
       // System.out.println(text + " " + match);
        int i = 0;
        while (text.length()>0 && text.contains(match)) {
            text = text.replaceFirst(match, "");
            i++;
        //    System.out.println(text);
        }
        //System.out.println(text + " " + match);
        return i;
    }


    public static void main(String[] args) {
        System.out.println("При вводе чисел 6522 и 22 метод должен вернуть 1 - "
                + ConditionalUtilsCountInner.countInnerNumber(2222, 22));
    }
}