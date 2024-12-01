package factory;

import animals.*;
import exceptions.AnimalTypesNotSupportedException;
import data.AnimalTypesData;

public class FactoryAnimal {
    public static AbsAnimal create(AnimalTypesData animalTypesData, String name, int age, float weight, String color)
            throws AnimalTypesNotSupportedException { //AbsAnimal тут - определение возвращаемого методом типа
        //
        return switch (animalTypesData) {
            case CAT -> new animals.pets.Cat(name, age, weight, color);
            case DOG -> new animals.pets.Dog(name, age, weight, color);
            case DUCK -> new animals.birds.Duck(name, age, weight, color);
            default -> throw new AnimalTypesNotSupportedException(animalTypesData);
        };
    }
}