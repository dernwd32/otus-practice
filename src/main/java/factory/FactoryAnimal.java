package factory;

import animals.*;
import data.AnimalTypesData;

public class FactoryAnimal {
    public AbsAnimal create(AnimalTypesData animalTypesData, String name, int age, float weight, String color) {
        // AbsAnimal тут - определение возвращаемого методом типа объекта
        return switch (animalTypesData) {
            case CAT -> new animals.pets.Cat(name, age, weight, color);
            case DOG -> new animals.pets.Dog(name, age, weight, color);
            case DUCK -> new animals.birds.Duck(name, age, weight, color);
            default -> null; //если в енаме добавили новую дочку, а на фабрике забыли, вернём нулл
        };
    }
}