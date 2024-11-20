package strings;

public class BracketUtil {
    public static boolean isCorrectBrackets(String input,
                                            char bracketOpenSymbol,
                                            char bracketCloseSymbol) {

        int openers = 0;
        int closers = 0;
        for (int i = 0; i < input.length(); i++) {
            if ( input.charAt(i) == bracketOpenSymbol) {
                openers++;
            }
            if ( input.charAt(i) == bracketCloseSymbol) {
                closers++;
                if (openers < closers) return false;
            }

        }

       // System.out.println("Итого: " + openers + " " + closers);
        if (openers == closers) return true;
        else return false;


        //return true;
    }

    public static void main(String[] args) {
        System.out.println("Ввод равного поличества открывающих и закрывающих скобок {{}} - "
                + BracketUtil.isCorrectBrackets("{d{f}d}", '{', '}'));
        System.out.println("Ввод неравного поличества открывающих и закрывающих скобок {{{}} - "
                + BracketUtil.isCorrectBrackets("{{{sdf}}", '{', '}'));
        System.out.println("Ввод неравного поличества открывающих и закрывающих скобок {{}}} - "
                + BracketUtil.isCorrectBrackets("{{sdf}}sfd}", '{', '}'));
        System.out.println("Ввод равного поличества открывающих и закрывающих скобок в неправильном порядке {}}}{{ - "
                + BracketUtil.isCorrectBrackets("{}}sdf}{{", '{', '}'));
        System.out.println("Ввод равного поличества открывающих и закрывающих скобок в правильном порядке с вложенностью {{}{}} - "
                + BracketUtil.isCorrectBrackets("{{}{sdf}}", '{', '}'));
    }
}