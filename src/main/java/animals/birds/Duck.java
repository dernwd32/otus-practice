package animals.birds;

import animals.AbsAnimal;

public class Duck extends AbsAnimal implements IFlying{

    public Duck(String name, int age, float weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.print("Кря");
    }

}
