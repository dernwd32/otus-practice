package app;

import animals.AbsAnimal;
import animals.AnimalList;
import animals.AnimalTools;
import data.AnimalTypesData;
import data.MainMenuData;
import db.tables.AnimalTable;
import factory.FactoryAnimal;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //булева переменная, отвечающая далее на вопрос - были ли обновления в таблице, надо ли заново считывать
    public static boolean ifUpdated = true;

    public static void main(String[] args) {

        //создаем список животных
        AnimalList listAnimals = new AnimalList();
        //Создаём объект таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        //создаем объект EditAnimal для вызова методов изменения объекта
        //EditAnimal editAnimal = new EditAnimal();
        AnimalTools animalTools = new AnimalTools();
        //создаём экземпляр фабрики, чтоб избавиться от объявления статиком всего класса
        FactoryAnimal factoryAnimal = new FactoryAnimal();

        labelExit:
        for(;;) {
            // запрашиваем список из базы на старте и каждом возвращении в главное меню список из таблицы для счётчика
            // если были изменения записи
            try { if (ifUpdated) listAnimals = animalTable.read(); }
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
                    case ADD -> {
                        animalTools.addAnimal();
                    }
                    case EDIT -> {
                        listAnimals.printTableListAnimals();
                        animalTools.editAnimal();
                    }
                    case LIST -> listAnimals.printListAnimals();
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


