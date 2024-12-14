package app;

import animals.AnimalList;
import animals.AnimalTools;
import data.MainMenuData;
import db.tables.AnimalTable;
import factory.AnimalFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    //булева переменная, отвечающая далее на вопрос - были ли обновления в таблице, надо ли заново считывать
    public static boolean ifUpdated = true;

    public static void main(String[] args) {

        //создаем список животных
        AnimalList listAnimals = new AnimalList();
        //Создаём объект таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        //создаем объект animalTools для вызова методов изменения объекта
        AnimalTools animalTools = new AnimalTools();

        Funcs miscFuncs = new Funcs();


        labelExit:
        for(;;) {
            // запрашиваем список из базы на старте и каждом возвращении в главное меню список из таблицы для счётчика
            // если были изменения записи
            try {
                if (ifUpdated) {
                    miscFuncs.loader(0);
                    listAnimals = animalTable.read();
                    miscFuncs.loader(3);
                }
            }
            catch (SQLException e) { throw new RuntimeException(e); }

            try {
                System.out.println(listAnimals.countAnimals() + "\n"
                        + "\u001b[36;1mВыберите дальнейшее действие, напечатав одну из команд: \u001B[0m");
                Arrays.stream(MainMenuData.values()).forEach(
                        menuChoice -> System.out.println("\t" + (menuChoice.ordinal() + 1)
                                + " - " + menuChoice.toString().toLowerCase()));
                Scanner console = new Scanner(System.in);
                String command = console.nextLine().toLowerCase().trim();


                var action = MainMenuData.allOptions(command);
                switch (action) {
                    case ADD -> animalTools.addAnimal();
                    case EDIT -> {
                        listAnimals.printTableListAnimals();
                        animalTools.editAnimal();
                    }
                    case LIST -> listAnimals.printListAnimals();
                    case SEARCH -> {
                        try {
                            animalTools.searchAnimal();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case DELETE -> {
                        listAnimals.printTableListAnimals();
                        animalTools.deleteAnimal();
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


