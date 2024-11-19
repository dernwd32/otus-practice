public class Cat extends Animal {

    public Cat(String name, Integer age, Float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }

}
