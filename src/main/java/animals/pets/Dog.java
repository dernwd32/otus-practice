package animals.pets;

import animals.AbsAnimal;

public class Dog extends AbsAnimal {


    public Dog (String name, int age, float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.print("Гав");
    }

}