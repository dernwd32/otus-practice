package animals;

import app.Funcs;
import data.AnimalTypesData;
import factory.FactoryAnimal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputAnimal {

    public AbsAnimal inputAnimal() {

        Scanner console = new Scanner(System.in);
        String chosenAnimal;
        Funcs miscFuncs = new Funcs();
        List<String> listChildren = Arrays.stream(AnimalTypesData.values())
                .map(animalType -> miscFuncs.firstLetterCapitalize(animalType.name()))
                .toList();

        for (;;) {
            System.out.println("Выберите животное: ");

            //можно было бы в одном фориче создать лист и вывести меню, но умные люди говорят - моветон!
            listChildren.forEach(animalType -> System.out.println("\t - " + animalType));

            chosenAnimal = miscFuncs.firstLetterCapitalize(console.nextLine().trim());
            if (!listChildren.contains(chosenAnimal))
                System.out.println("Неправильно введен тип животного: " + chosenAnimal);
            else {
                System.out.println("Животное типа " + chosenAnimal + " успешно... зачато.");
                break;
            }
        }

        String name = miscFuncs.inputWithRegexValidate(
                "^[A-z \\-\\d]{2,30}|[А-яЁё \\-\\d]{2,30}$",
                "Введите имя животного",
                "Имя может содержать только буквы русского или(!) английского " +
                        "алфавитов, цифры, пробел и дефис. R2-D2 - можно. BigЗлыдень - нет! От 2 до 30 символов."
        );

        int intAge = Integer.parseInt(miscFuncs.inputWithRegexValidate(
                "^[0-9]\\d{0,2}$",
                "Введите возраст животного",
                "Неправильно введен возраст животного. Возраст должен быть целым числом от 0 до 999"
        ));

        float floatWeight = Float.parseFloat(miscFuncs.inputWithRegexValidate(
                "^[1-9]\\d{0,2}(\\.\\d+)?$",
                "Введите вес животного",
                "Неправильно введен вес животного. Вес должен быть числом (с плавающей точкой) от 1 до 999.999"
        ));

        String color = miscFuncs.inputWithRegexValidate(
                "^#[a-fA-F0-9]{6}$",
                "Введите цвет животного строго в формате hex-RGB: #XXXXXX (например, для красного #FF0000)",
                ""
        );
        int dbId = 0;
        //создаём экземпляр фабрики, чтоб избавиться от объявления статиком всего класса
        FactoryAnimal factoryAnimal = new FactoryAnimal();
        //создаём экземпляр дочернего класса через фабрику и отправляем его обратно в main() в listAnimals.
        return factoryAnimal.create(
                AnimalTypesData.valueOf(chosenAnimal.toUpperCase()), name, intAge, floatWeight, color, dbId);

    }

}
