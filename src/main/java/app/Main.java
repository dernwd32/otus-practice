package app;

import animals.AbsAnimal;
import animals.AnimalList;
import animals.InputAnimal;
import data.MainMenuData;
import db.tables.AnimalTable;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        //создаем список животных
        AnimalList listAnimals = new AnimalList();
        //Создаём экземпляр класса InputAnimal для вызова метода inputAnimals(), создающего новое животное
        InputAnimal inputAnimal = new InputAnimal();
        //Создаём объект таблицы для выполнения запросов
        AnimalTable animalTable = new AnimalTable();


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
                        AbsAnimal createdAnimal = inputAnimal.inputAnimal();
                        String type = createdAnimal.getClass().getSimpleName();
                        if (createdAnimal != null) {
                            //добавляем созданное животное в список
                         //   listAnimals.setListAnimals(createdAnimal);
                            //пишем в базу
                            animalTable.write(createdAnimal, type);
                            System.out.print("Животное типа " + type
                                    + " успешно порождено. И сказало оно: \n -- ");
                            createdAnimal.say();
                        }
                        //если в енаме добавили новую дочку, а на фабрике забыли, то фабрика вернула null =>
                        else System.out.println("Выбранное вами животное не поддерживается нашей фабрикой! " +
                                "Пните разработчика за невнимательность!");
                    }
                    case LIST -> {

                        try {
                           listAnimals = animalTable.read();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        listAnimals.printListAnimals();
                    }
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


}


