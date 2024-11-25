package animals.pets;

import animals.AbsAnimal;

public class Cat extends AbsAnimal {

    public Cat(String name, Integer age, Float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.print("Мяу");
    }

}
