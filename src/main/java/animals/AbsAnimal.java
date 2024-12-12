package animals;

import app.Funcs;

public abstract class AbsAnimal {
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
    protected AbsAnimal(String name, int age, float weight, String color, int dbId) {
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


}
