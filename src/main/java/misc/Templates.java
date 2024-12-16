package tools;

import java.util.LinkedHashMap;

public class Templates {

    public LinkedHashMap<String,String> tableTemplate() {
        //LinkedHashMap потому что сохраняет порядок, в котором добавлялись элементы в отличие от HashMap
        LinkedHashMap<String,String> columns = new LinkedHashMap<>();
        columns.put("id", "%-5s");
        columns.put("type", "%-8s");
        columns.put("name", "%-25s");
        columns.put("age", "%-10s");
        columns.put("weight", "%-10s");
        columns.put("color", "%-10s");

        return columns;
    }
}
