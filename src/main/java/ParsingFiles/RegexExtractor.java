package ParsingFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtractor {


    public static List<Integer> extractIds(String str) {
        Pattern pattern = Pattern.compile("\\[(\\d+)]");
        Matcher matcher = pattern.matcher(str);

        List<Integer> extractedIntegers = new ArrayList<>();

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            extractedIntegers.add(number);
        }
        return extractedIntegers;
    }

    public static void main(String[] args) {
        //String input = "Советник по СПиИ [2507840] / Советник по ОП/Директор ДПУЭРиРГБ/Руководитель ФЭП [2507841] / На усмотрение Инициатора [2475471] / Директор ДСПиО [2475486] / Профильные топ-менеджеры [2475493] / Топ-менеджер, ответственный за вопросы стратегического планирования [2475503]";

       // List<Integer> extractedIntegers = extractIds(str);

        //extractedIntegers.forEach(System.out::println);
    }

}