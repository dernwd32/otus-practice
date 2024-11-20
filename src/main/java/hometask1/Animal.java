package hometask1;

import java.util.ArrayList;

public class Animal {
    private String name;
    private Integer age;
    private Float weight;
    private String color;

    private static ArrayList<Animal> listAnimals = new ArrayList<>();

    public static ArrayList<Animal> getListAnimals() {
        return listAnimals;
    }

    public static void setListAnimals(Animal animal) {
        listAnimals.add(animal);
    }


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
        assert age > 0 : "Возраст не может быть меньше нуля";
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        assert weight >= 0 : "Вес должен быть больше нуля";
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
        String newStr = "Привет! меня зовут " + this.name + ", мне " + this.age + " " +
                MiscFuncs.agePostfix(this.age) + ", я вешу - " + MiscFuncs.cutZeroFloat(this.weight) + " кг, мой цвет - " + this.color;
        return newStr;
    }
}
