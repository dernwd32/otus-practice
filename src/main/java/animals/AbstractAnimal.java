package animals;

import app.Funcs;

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
        //return "%-10s | %-25s | %-10s | %-10s | %-10s |" .formatted(dbId, name, age, miscFuncs.floatTemplate(weight), color);

        String hName = name;
        String hAge = String.valueOf(age);
        String hWeight = miscFuncs.floatTemplate(weight);
        String hColor = color;
        String hType = type;
        String hId = String.valueOf(dbId);
        String highlightFormat = "\u001B[36m%s\u001B[0m";

        switch (highlightColumn) {
            case "name" -> hName = highlightFormat.formatted(name);
            case "age" -> hAge = highlightFormat.formatted(age);
            case "weight" -> hWeight = highlightFormat.formatted(miscFuncs.floatTemplate(weight));
            case "color" -> hColor = highlightFormat.formatted(color);
            case "type" -> hType = highlightFormat.formatted(type);
            case "id" -> hId = highlightFormat.formatted(dbId);
        }

        return String.format("%-25s | %-10s | %-10s | %-10s | %s#%-8s ",
               hName, hAge, hWeight, hColor, hType, hId);

    }


}
