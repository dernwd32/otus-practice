package animals.pets;

import animals.AbsAnimal;

public class Dog extends AbsAnimal {


    public Dog (String name, Integer age, Float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.print("Гав");
    }

}