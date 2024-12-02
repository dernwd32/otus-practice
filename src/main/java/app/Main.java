package app;

import animals.AbsAnimal;
import animals.AnimalList;
import data.AnimalTypesData;
import data.MainMenuData;
import factory.FactoryAnimal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        //создаем список животных
        AnimalList listAnimals = new AnimalList();

        labelExit:
        for(;;) {
            try {
                System.out.println(listAnimals.countAnimals() + "\n"
                        + "Выберите дальнейшее действие, напечатав одну из команд: ");
                Arrays.stream(MainMenuData.values()).forEach(
                        menuChoice -> System.out.println("\t" + (menuChoice.ordinal() + 1)
                                + " - " + menuChoice.toString().toLowerCase()));
                Scanner console = new Scanner(System.in);
                String command = console.nextLine().toLowerCase().trim();


                var action = MainMenuData.allOptions(command);
                switch (action) {
                    case ADD -> {
                        //вызываем метод, возвращающий созданное по введённым параметрам животное
                        AbsAnimal createdAnimal = inputAnimal();
                        if (createdAnimal != null) {
                            //добавляем созданное животное в список
                            listAnimals.setListAnimals(createdAnimal);
                            System.out.print("Животное типа " + createdAnimal.getClass().getSimpleName()
                                    + " успешно порождено. И сказало оно: \n -- ");
                            createdAnimal.say();
                        }
                        //если в енаме добавили новую дочку, а на фабрике забыли, то фабрика вернула null =>
                        else System.out.print("Выбранное вам животное не поддерживается нашей фабрикой! " +
                                "Пните разработчика за невнимательность!  \n");
                    }
                    case LIST -> listAnimals.printListAnimals();
                    case EXIT -> {
                        System.out.println("Покедова!");
                        break labelExit; //просто так оригинальнее, чем System.exit(0); хочу так! %)
                    }
                }

            } catch (IllegalStateException e) {
                System.out.println("Некорректный выбор.");
            }

        }
    }


    //метод обрабатывает пользовательский ввод параметров животного и возвращает созданное животное в main()
    static AbsAnimal inputAnimal() {

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

        String name;
        for (;;) {
            System.out.println("Введите имя животного");
            name = console.nextLine().trim();
            if (name.matches("^[A-z \\-\\d]{2,30}|[А-яЁё \\-\\d]{2,30}$")) break;
            else System.out.println("Имя может содержать только буквы русского или(!) английского " +
                    "алфавитов, цифры, пробел и дефис. R2-D2 - можно. BigЗлыдень - нет! От 2 до 30 символов.");
        }

        int intAge;
        for (;;) {
            System.out.println("Введите возраст животного");
            String inputAge = console.nextLine().trim();
            if (inputAge.matches("^[0-9]\\d{0,2}$")) {
                intAge = Integer.parseInt(inputAge);
                break;
            } else System.out.println("Неправильно введен возраст животного." +
                    " Возраст должен быть целым числом от 0 до 999. ");
        }

        float floatWeight;
        for (;;) {
            System.out.println("Введите вес животного");
            String inputWeight = console.nextLine().trim();
            if (inputWeight.matches("^[1-9]\\d{0,2}(\\.\\d+)?$")) {
                floatWeight = Float.parseFloat(inputWeight);
                break;
            } else
                System.out.println("Неправильно введен вес животного." +
                        " Вес должен быть числом (с плавающей точкой) от 1 до 999.999... ");
        }

        String color;
        for (;;) {
            System.out.println("Введите цвет животного строго в формате hex-RGB: " +
                    "#XXXXXX (например, для красного #FF0000)");
            color = console.nextLine().trim();
            if (color.matches("^#[a-fA-F0-9]{6}$")) break;
        }

        //создаём экземпляр фабрики, чтоб избавиться от объявления статиком всего класса
        FactoryAnimal factoryAnimal = new FactoryAnimal();
        //создаём экземпляр дочернего класса через фабрику и отправляем его обратно в main() в listAnimals.
        return factoryAnimal.create(
                AnimalTypesData.valueOf(chosenAnimal.toUpperCase()), name, intAge, floatWeight, color);

    }




}


