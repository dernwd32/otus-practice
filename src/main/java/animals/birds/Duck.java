package animals.birds;

import animals.AbsAnimal;

public class Duck extends AbsAnimal implements IFlying{

    public Duck(String name, Integer age, Float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.print("Кря");
    }

}
