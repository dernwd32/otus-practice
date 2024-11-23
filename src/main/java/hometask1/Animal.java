package hometask1;


import java.util.Arrays;

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

    /*Если перетащить дочерние классы животных внутрь родительского Animal,
    то вот этот код позволит налету их имена в массив. Как следствие -
    налету генерировать меню выбора животных и ...возможно, даже создание и
    добавление их объектов в AnimalList

                  System.out.println("???");
                  Class<?>[] classes = Animal.class.getClasses();
                  Arrays.stream(classes)
                       .forEach(x -> System.out.println(x.getSimpleName()));
     */


//    public static String getAnimalClass(Animal animal){
//        String[] elems = animal.getClass().toString().split("\\.");
//        return elems[elems.length-1];
//          return animal.getClass().getSimpleName();
//    }
}
