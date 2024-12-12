package animals;

import app.Funcs;
import data.AnimalTypesData;
import db.tables.AnimalTable;
import factory.FactoryAnimal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnimalTools {


    public Map<String, Object> inputAnimal() {

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
            if (chosenAnimal.equalsIgnoreCase("cancel")) return null; //выход через 'cancel'
            if (!listChildren.contains(chosenAnimal) || chosenAnimal.isEmpty())
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
                        "алфавитов, цифры, пробел и дефис. R2-D2 - можно. BigЗлыдень - нет! От 2 до 30 символов.",
                false
        );

        int intAge = Integer.parseInt(miscFuncs.inputWithRegexValidate(
                "^[0-9]\\d{0,2}$",
                "Введите возраст животного",
                "Неправильно введен возраст животного. Возраст должен быть целым числом от 0 до 999",
                false
        ));

        float floatWeight = Float.parseFloat(miscFuncs.inputWithRegexValidate(
                "^[1-9]\\d{0,2}(\\.\\d+)?$",
                "Введите вес животного",
                "Неправильно введен вес животного. Вес должен быть числом (с плавающей точкой) от 1 до 999.999",
                false
        ));

        String color = miscFuncs.inputWithRegexValidate(
                "^#[a-fA-F0-9]{6}$",
                "Введите цвет животного строго в формате hex-RGB: #XXXXXX (например, для красного #FF0000)",
                "",
                false
        );
        int dbId = 0;

        Map<String, Object> inputValues = new HashMap<>();
        inputValues.put("type", chosenAnimal);
        inputValues.put("name", name);
        inputValues.put("age", intAge);
        inputValues.put("weight", floatWeight);
        inputValues.put("color", color);

        return inputValues;

    }

    public void addAnimal() {

        //создаём экземпляр фабрики
        FactoryAnimal factoryAnimal = new FactoryAnimal();

        //Создаём объект таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();

        //вызываем метод, возвращающий HashMap с введёнными параметрами животного
        Map<String,Object> animalValues = inputAnimal();
        if (animalValues==null) return; //выход через 'cancel'
        String type = animalValues.get("type").toString();

        //создаём экземпляр дочернего класса через фабрику
        AbsAnimal createdAnimal = factoryAnimal.create(
                AnimalTypesData.valueOf(type.toUpperCase()),
                animalValues.get("name").toString(),
                Integer.parseInt(animalValues.get("age").toString()),
                Float.parseFloat(animalValues.get("weight").toString()),
                animalValues.get("color").toString(),
                0
        );
        //String type = createdAnimal.getClass().getSimpleName();

        if (createdAnimal != null) {
            //пишем в базу
            animalTable.insert(createdAnimal, type);
            System.out.print("Животное типа " + type
                    + " успешно порождено. И сказало оно: \n -- ");
            createdAnimal.say();
        }
        //если в енаме добавили новую дочку, а на фабрике забыли, то фабрика вернула null =>
        else System.out.println("Выбранное вами животное не поддерживается нашей фабрикой! " +
                "Пните разработчика за невнимательность!");
    }


    public void editAnimal() {
        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();


       // boolean existsId = false;
        while (true) {
            Funcs miscFuncs = new Funcs();


            String input = miscFuncs.inputWithRegexValidate(
                    "^[0-9]{1,10}$",
                    "\nВведите id записи, которую хотите изменить",
                    "Некорректное значение id",
                    true
            );

            if (input == null) break; //выход в главное меню через 'cancel'

            try (ResultSet animalById = animalTable.selectWhereId(Integer.parseInt(input))) {
                //проверяем наличие такого id в базе
                if (animalById.next()) {
                    //System.out.println("Есть " + input);

                    //вызываем метод, возвращающий HashMap с введёнными параметрами животного
                    Map<String,Object> animalValues = inputAnimal();

                    //пишем в базу
                    animalTable.update(
                            animalValues.get("color").toString(),
                            animalValues.get("name").toString(),
                            Float.parseFloat(animalValues.get("weight").toString()),
                            animalValues.get("type").toString().toUpperCase(),
                            Integer.parseInt(animalValues.get("age").toString()),
                            Integer.parseInt(input)
                    );
                    System.out.print("Животное с id #" + Integer.parseInt(input) + " успешно обновлено. \n ");

                   break;
                } else {
                    System.out.println("Несуществующий id.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void deleteAnimal() {
        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        Funcs miscFuncs = new Funcs();

        String input = miscFuncs.inputWithRegexValidate(
                "^[0-9 ,]+$",
                "\nВведите id записи, которую хотите удалить. Либо перечислите id для удаления через запятую.",
                "Некорректные значения id",
                true
        );


        if (input == null) return;  //выход в главное меню через 'cancel'

        String[] ids = input.split(",");

        for (String key : ids) {
            key = key.trim();
            if (!key.isEmpty()) {
                int thisId = Integer.parseInt(key);
                try (ResultSet animalById = animalTable.selectWhereId(thisId)) {
                    //проверяем наличие такого id в базе
                    if (animalById.next()) {

                        //пишем в базу
                        animalTable.delete(thisId);
                        System.out.print("Животное с id #" + thisId + " успешно удалено. \n ");

                    } else {
                        System.out.println("Несуществующий id #" + thisId + ". Удаление невозможно.");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
