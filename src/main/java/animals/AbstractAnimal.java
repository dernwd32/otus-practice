package animals;

import app.Funcs;

import java.util.*;

public abstract class AbstractAnimal {
    private String name;
    private int age;
    private float weight;
    private String color;
    private int dbId;

    //сеттеры и геттеры по факту не используются, сохранены для соответствия условиям задачи
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getDbId() {return dbId;}
    public void setDbId(int dbId) {
        this.dbId = dbId;
    }


    //кучка неиспользуемых методов - тоже по условию задачи
    public void go() { System.out.println("Я иду"); }
    public void drink() { System.out.println("Я пью"); }
    public void eat() { System.out.println("Я ем"); }
    public void say() { System.out.println("Я говорю"); }


    //Конструктор задан здесь для того, чтоб указать наследникам "как надо".
    protected AbstractAnimal(String name, int age, float weight, String color, int dbId) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.dbId = dbId;
    }

    Funcs miscFuncs = new Funcs();
    //переопределяем метод toString
    @Override
    public String toString() {

        return "%s -- Привет! меня зовут %s, мне %d %s, я вешу %s кг, мой цвет - %s. "
                .formatted(dbId, name, age, miscFuncs.agePostfix(age),
                        miscFuncs.floatTemplate(weight), color);
    }

    public String toTableTr(String highlightColumn) {
        String type = this.getClass().getSimpleName();
        String highlightFormat = "\u001B[36m%s\u001B[0m";

        Map<String,String> columns = new LinkedHashMap<>();
        columns.put("id", "%-5s");
        columns.put("type", "%-8s");
        columns.put("name", "%-25s");
        columns.put("age", "%-10s");
        columns.put("weight", "%-10s");
        columns.put("color", "%-10s");
        columns.forEach((key, value) -> {
            if (key.equals(highlightColumn))
                columns.replace(key, highlightFormat.formatted(value));
        });

        String template = String.join(" | ", columns.values());
        return String.format(template,
                dbId, type, name, age, miscFuncs.floatTemplate(weight), color);

    }


}
