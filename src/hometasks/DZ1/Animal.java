import java.util.ArrayList;

public class Animal {
    private String name;
    private Integer age;
    private Float weight;
    private String color;

    private static ArrayList<Animal> listAnimals = new ArrayList<Animal>();



    public static ArrayList<Animal> getListAnimals() {
        return listAnimals;
    }

    public static void setListAnimals(Animal animal) {
        listAnimals.add(animal);
    }

    //    //создаём геттеры и сеттеры для полей
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
        assert age >= 0 : "Вес должен быть больше нуля";
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
        String newStr = "";

        //преобразование постфикса возраста
        int lastDigit = this.age % 10;
        int lastTwoDigits = this.age % 100;
        String agePostfix = "";
        if ( lastDigit == 0 || lastDigit >= 5 || (lastTwoDigits >= 11 && lastTwoDigits <= 14))
            agePostfix = "лет";
        else if (lastDigit == 1) agePostfix = "год";
        else if (lastDigit > 1) agePostfix = "года";

        //убираем ноль после точки в весе
        String sWeight = "";
        if (this.weight == this.weight.intValue())
            sWeight = String.valueOf(this.weight.intValue());
        else
            sWeight = this.weight.toString();


        newStr = "Привет! меня зовут " + this.name + ", мне " + this.age + " " +
                agePostfix + ", я вешу - " + sWeight + " кг, мой цвет - " + this.color;
//        newStr.append("Привет! меня зовут ").append(this.name).append(", мне ").append(this.age).append(" ")
//                .append(agePostfix).append(", я вешу - ").append(sWeight)
//                .append(" кг, мой цвет - ").append(this.color);
        return newStr;


    }
}
