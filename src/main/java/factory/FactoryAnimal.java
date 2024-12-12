package factory;

import animals.*;
import data.AnimalTypesData;

public class FactoryAnimal {
    public AbsAnimal create(AnimalTypesData animalTypesData, String name, int age, float weight, String color, int dbId) {
        // AbsAnimal тут - определение возвращаемого методом типа объекта
        return switch (animalTypesData) {
            case CAT -> new animals.pets.Cat(name, age, weight, color, dbId);
            case DOG -> new animals.pets.Dog(name, age, weight, color, dbId);
            case DUCK -> new animals.birds.Duck(name, age, weight, color, dbId);
            default -> null; //если в енаме добавили новую дочку, а на фабрике забыли, вернём нулл
        };
    }
}