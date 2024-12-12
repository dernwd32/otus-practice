package animals;

import db.tables.AnimalTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EditAnimal {
    public void editAnimal() {

        //Создаём объект таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        //Создаём экземпляр класса InputAnimal для вызова метода inputAnimals(), создающего новое животное
        InputAnimal inputAnimal = new InputAnimal();

        boolean existsId = false;

        while  (!existsId) {
            Scanner console = new Scanner(System.in);
            System.out.println("Введите id записи, которую хотите изменить");
            String input = console.nextLine().trim();

            try (ResultSet animalById = animalTable.selectWhereId(Integer.parseInt(input))) {
                //проверяем наличие такого id в базе
                if (animalById.next()) {
                    System.out.println("Есть " + input);

                    //вызываем метод, возвращающий созданное по введённым параметрам животное
                    AbsAnimal createdAnimal = inputAnimal.inputAnimal();
                    String type = createdAnimal.getClass().getSimpleName();
                    if (createdAnimal != null) {
                        //пишем в базу
                        animalTable.update(
                                createdAnimal.getColor(),
                                createdAnimal.getName(),
                                createdAnimal.getWeight(),
                                type,
                                createdAnimal.getAge(),
                                Integer.parseInt(input)
                        );
                        System.out.print("Животное с id  " + Integer.parseInt(input)
                                + " успешно обновлено. \n ");
                    }
                    //если в енаме добавили новую дочку, а на фабрике забыли, то фабрика вернула null =>
                    else System.out.println("Выбранное вами животное не поддерживается нашей фабрикой! " +
                            "Пните разработчика за невнимательность!");
                    existsId = true;
                } else {
                    System.out.println("Несуществующий id.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            // if (input.matches(regexRule)) return input;
            // else if (!errorText.isEmpty()) System.out.println(errorText);

        }
    }
}
