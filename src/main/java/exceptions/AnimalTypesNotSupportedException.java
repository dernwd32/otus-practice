package exceptions;

import data.AnimalTypesData;

public class AnimalTypesNotSupportedException extends Throwable {
    public AnimalTypesNotSupportedException(AnimalTypesData animalTypesData) {
        super(String.valueOf(animalTypesData));
    }
}

