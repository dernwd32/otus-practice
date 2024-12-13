package animals.pets;

import animals.AbsAnimal;

public class Cat extends AbsAnimal {

    public Cat(String name, int age, float weight, String color, int dbId) {
        super(name, age, weight, color, dbId);
    }

    @Override
    public void say() {
        System.out.println("-- Мяу");
    }

}
