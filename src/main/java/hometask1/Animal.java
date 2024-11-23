package hometask1;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Animal {
    private String name;
    private Integer age;
    private Float weight;
    private String color;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //создаём пользовательские методы класса
    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    //конструкторы
    public Animal(String name, Integer age, Float weight, String color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public Animal() {
    }

    //переопределяем метод toString
    @Override
    public String toString() {
        //  StringBuilder newStr = new StringBuilder();
        return "Привет! меня зовут %s, мне %d %s, я вешу - %s кг, мой цвет - %s"
                .formatted(name, age, MiscFuncs.agePostfix(age),
                        MiscFuncs.cutZeroFloat(weight), color);
    }

    public static class Cat extends Animal {

        public Cat(String name, Integer age, Float weight, String color) {
            super(name, age, weight, color);
        }

        @Override
        public void say() {
            System.out.println("Мяу");
        }

    }

    public static class Dog extends Animal {

        public Dog (String name, Integer age, Float weight, String color) {
            super(name, age, weight, color);
        }

        @Override
        public void say() {
            System.out.println("Гав");
        }

    }
    public static class Duck extends Animal implements Flying{

        public Duck(String name, Integer age, Float weight, String color) {
            super(name, age, weight, color);
        }

        @Override
        public void say() {
            System.out.println("Кря");
        };

    }
    /*Если перетащить дочерние классы животных внутрь родительского Animal,
    то вот этот код позволит налету их имена собирать. Как следствие -
    налету генерировать меню выбора животных и даже создание и
    добавление их объектов в AnimalList.
    * изучить вопрос - как сделать такое же с внешними дочерними.java
     */
    public static Set getChildClasses() {
       // System.out.println("???");
        Class<?>[] classes = Animal.class.getClasses();
        Set childs = new HashSet();
        Arrays.stream(classes).forEach(x -> childs.add(x.getSimpleName()));
        return childs;
    }



}
