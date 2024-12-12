package app;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Funcs {

    public String firstLetterCapitalize(String str) {
        if (!str.isEmpty()) return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
        else return "";
    }

    public String agePostfix(int age) {
        //определяем падеж года
        int lastDigit = age % 10;
        int lastTwoDigits = age % 100;
        if (lastDigit == 0 || lastDigit >= 5 || (lastTwoDigits >= 11 && lastTwoDigits <= 14)) return "лет";
        else if (lastDigit == 1) return "год";
        else return "года";
    }

    public String floatTemplate (float number) {
        //используем US локаль, чтоб разделителем десятичным была точка, а не русская запятая
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        // настройка шаблона на 3 знака после запятой
        DecimalFormat df = new DecimalFormat("#.###", otherSymbols);
        // лучше, чем String.format("%.3f", weight), хотя бы потому что отрезает лишние нули
        return df.format(number);
    }

    public String inputWithRegexValidate(String regexRule, String questionText, String errorText, boolean canCancel) {

        for (;;) {
            Scanner console = new Scanner(System.in);
            System.out.println(questionText);
            if (canCancel) System.out.println("Вернуться в главное меню - 'cancel'");
            String input = console.nextLine().trim();
            if (canCancel && input.equals("cancel")) return null;
            if (input.matches(regexRule)) return input;
            else if (!errorText.isEmpty()) System.out.println(errorText);
        }
    }


}

