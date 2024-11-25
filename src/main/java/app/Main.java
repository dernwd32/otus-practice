package app;

import animals.AbsAnimal;
import animals.AnimalList;
import data.AnimalTypesData;
import data.MainMenuData;
import exceptions.AnimalTypesNotSupportedException;
import factory.FactoryAnimal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AnimalList newAnimalList = new AnimalList();

        boolean showmenu = true;
        while (showmenu) {
            try {
                System.out.println(newAnimalList.countAnimals()
                        + "Выберите дальнейшее действие, напечатав одну из команд: ");
                Arrays.stream(MainMenuData.values()).forEach(
                        menuChoice -> System.out.println("\t" + (menuChoice.ordinal() + 1)
                                + " - " + menuChoice.toString().toLowerCase()));
                Scanner console = new Scanner(System.in);
                String command = console.nextLine().toLowerCase().trim();


                var action = MainMenuData.allOptions(command);
                switch (action) {
                    case MainMenuData.ADD -> menuAdd();
                    case MainMenuData.LIST -> newAnimalList.printListAnimals();
                    case MainMenuData.EXIT -> {
                        System.out.println("Покедова!");
                        showmenu = false;
                    }
                    // default -> System.out.println(" ");
                }

            } catch (IllegalStateException e) {
                System.out.println("Некорректный выбор.");
            }

        }
    }


    static void menuAdd(){
            Scanner console = new Scanner(System.in);
            String menuAnimal;
            Funcs miscFuncs = new Funcs();

            for(;;){
                System.out.println("Выберите животное: ");

                //можно было бы в одном фориче создать лист и вывести меню, но умные люди говорят - моветон!
                List<String> listChildren = Arrays.stream(AnimalTypesData.values())
                        .map(animalType -> miscFuncs.firstCapitalize(animalType.name()))
                        .toList();
                listChildren.forEach(animalType -> System.out.println("\t - " + animalType));

                menuAnimal = miscFuncs.firstCapitalize(console.nextLine().trim());
                if (!listChildren.contains(menuAnimal) )
                    System.out.println("Неправильно введен тип животного: " + menuAnimal);
                else {
                    System.out.println("Животное типа " + menuAnimal + " успешно... зачато.");
                    break;
                }
            }

            String name;
            for(;;) {
                System.out.println("Введите имя животного");
                name = console.nextLine().trim();
                if (name.matches("^[А-яЁёA-z \\-\\d]+$")) break;
                else System.out.println("Имя может содержать только буквы русского и английского " +
                        "алфавитов, цифры, пробел и дефис.");
            }

            int intAge;
            for(;;){
                System.out.println("Введите возраст животного");
                String input = console.nextLine().trim();
                if (input.matches("^[0-9]\\d{0,2}$")) {
                    intAge = Integer.parseInt(input);
                    break;
                }
                else System.out.println("Неправильно введен возраст животного." +
                          " Возраст должен быть целым числом от 0 до 999. ");
            }

            float floatWeight;
            for(;;){
                System.out.println("Введите вес животного");
                String input = console.nextLine().trim();
                if (input.matches("^[1-9]\\d{0,2}|[1-9]\\d{0,2}\\.\\d+$")) {
                    floatWeight = Float.parseFloat(input);
                    break;
                }
                else
                    System.out.println("Неправильно введен вес животного." +
                            " Вес должен быть числом (с плавающей точкой) от 1 до 999.999... ");
            }

            String color;
            for (;;) {
                System.out.println("Введите цвет животного строго в формате hex-RGB: " +
                        "#XXXXXX (например, для красного #FF0000)");
                color = console.nextLine().trim();
                if (color.matches("^#[a-fA-F0-9]{6}$") ) break;
            }

            //создаём экземпляр и пихаем его в listAnimals. Try-Catch по специально созданному исключению
            try {
                AnimalList newAnimalList = new AnimalList();
                AbsAnimal createdAnimal  = FactoryAnimal
                        .create(AnimalTypesData.valueOf(menuAnimal.toUpperCase()), name, intAge, floatWeight, color);
                newAnimalList.setListAnimals(createdAnimal);
                System.out.print("Животное типа " + menuAnimal + " успешно порождено. И сказало оно: \n -- ");
                createdAnimal.say();

            } catch (AnimalTypesNotSupportedException e) {
                throw new RuntimeException("Попытка создать экземпляр неподдерживаемого класса: " + menuAnimal, e);
            }

        }




}


