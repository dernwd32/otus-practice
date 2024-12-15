package animals;

import tools.Funcs;
import data.AnimalTypesData;
import data.SearchFilterData;
import db.tables.AnimalTable;
import factory.AnimalFactory;
import tools.Loader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnimalTools {

    public Map<String, Object> inputAnimal() {

        Scanner console = new Scanner(System.in);
        String chosenAnimal;
        Funcs miscFuncs = new Funcs();
        List<String> listChildren = Arrays.stream(
                AnimalTypesData.values())
                .map(animalType -> miscFuncs.firstLetterCapitalize(animalType.name()))
                .toList();

        for (;;) {
            System.out.println("\u001b[36;1m> Выберите животное: \u001B[0m");

            //можно было бы в одном фориче создать лист и вывести меню, но умные люди говорят - моветон!
            listChildren.forEach(animalType -> System.out.println("\t - " + animalType));
            System.out.println("\t \u001b[3m(вернуться в главное меню: \u001b[1mcancel\u001B[0m)");

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
                        "алфавитов, цифры, пробел и дефис. R2-D2 - можно. BigЗлыдень - нет! От 2 до 30 символов."
        );
        if (name == null) return null; //выход через 'cancel'

        String age = miscFuncs.inputWithRegexValidate(
                "^[0-9]\\d{0,2}$",
                "Введите возраст животного",
                "Неправильно введен возраст животного. Возраст должен быть целым числом от 0 до 999"
        );
        if (age == null) return null; //выход через 'cancel'
        int intAge = Integer.parseInt(age);

        String weight = miscFuncs.inputWithRegexValidate(
                "^[1-9]\\d{0,2}(\\.\\d+)?$",
                "Введите вес животного",
                "Неправильно введен вес животного. Вес должен быть числом (с плавающей точкой) от 1 до 999.999"
        );
        if (weight == null) return null; //выход через 'cancel'
        float floatWeight = Float.parseFloat(weight);


        String color = miscFuncs.inputWithRegexValidate(
                "^#[a-fA-F0-9]{6}$",
                "Введите цвет животного строго в формате hex-RGB: #XXXXXX (например, для красного #FF0000)",
                ""
        );
        if (color == null) return null; //выход через 'cancel'
        //int dbId = 0;

        Map<String, Object> inputValues = new HashMap<>();
        inputValues.put("type", chosenAnimal);
        inputValues.put("name", name);
        inputValues.put("age", intAge);
        inputValues.put("weight", floatWeight);
        inputValues.put("color", color);

        return inputValues;

    }

    public void addAnimal() {
        Loader loader = new Loader();
        //создаём экземпляр фабрики
        AnimalFactory animalFactory = new AnimalFactory();

        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();


        //вызываем метод, возвращающий HashMap с введёнными параметрами животного
        Map<String,Object> animalValues = inputAnimal();

        if(Objects.isNull(animalValues)) return;  //выход через 'cancel'

        String type = animalValues.get("type").toString();

        //создаём экземпляр дочернего класса через фабрику
        AbstractAnimal createdAnimal = animalFactory.create(
                AnimalTypesData.valueOf(type.toUpperCase()),
                animalValues.get("name").toString(),
                Integer.parseInt(animalValues.get("age").toString()),
                Float.parseFloat(animalValues.get("weight").toString()),
                animalValues.get("color").toString(),
                0
        );

        if (createdAnimal != null) {
            //пишем в базу
            loader.loader(0);
            animalTable.insert(createdAnimal, type);
            loader.loader(1);
            System.out.println("Животное типа " + type
                    + " успешно порождено. И сказало оно: ");
            createdAnimal.say();
        }
        //если в енаме добавили новую дочку, а на фабрике забыли, то фабрика вернула null =>
        else System.out.println("Выбранное вами животное не поддерживается нашей фабрикой! " +
                "Пните разработчика за невнимательность!");
    }


    public void editAnimal() {
        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();

        Funcs miscFuncs = new Funcs();
        Loader loader = new Loader();

       // boolean existsId = false;
        while (true) {

            String input = miscFuncs.inputWithRegexValidate(
                    "^[0-9]{1,10}$",
                    "Введите id записи, которую хотите изменить",
                    "Некорректное значение id"
            );

            if (input == null) break; //выход в главное меню через 'cancel'

            loader.loader(0);

            try (ResultSet animalById = animalTable.selectWhereId(Integer.parseInt(input))) {
                //проверяем наличие такого id в базе
                if (animalById.next()) {
                    loader.loader(3);

                    System.out.println( "\u001B[33mВыбранная для изменения запись: \u001b[3m"
                            + miscFuncs.firstLetterCapitalize(animalById.getString("type")) + "#"
                            + animalById.getString("id") + " "
                            + animalById.getString("name") + "\u001B[0m\n"
                            + "\u001B[33mВвод новых данных...\u001B[0m"
                    );
                    //вызываем метод, возвращающий HashMap с введёнными параметрами животного
                    Map<String,Object> animalValues = inputAnimal();

                    if (Objects.isNull(animalValues)) break; // выход через cancel

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
                    loader.loader(1);
                    System.out.println("Несуществующий id.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void searchAnimal() throws SQLException {
        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        AnimalList animalList = new AnimalList();
        AnimalFactory animalFactory = new AnimalFactory();
        Loader loader = new Loader();

        List<String> listOptions = Arrays.stream(
                        SearchFilterData.values())
                .map(option -> (option.name().toLowerCase()))
                .toList();

        String searchField, searchValue, searchType, searchString, descr;

        //выбор поля
        for(;;) {
            System.out.print("\u001b[36;1mВыберите поле для поиска:\u001B[0m "
                    + String.join(", ", listOptions)
                    + "\n\t \u001b[3m(вернуться в главное меню: \u001b[1mcancel\u001B[0m)\n");

            Scanner console = new Scanner(System.in);
            searchField = console.nextLine().toLowerCase().trim();

            if (searchField.equalsIgnoreCase("cancel")) return; //выход через 'cancel'
            if (!listOptions.contains(searchField))
                System.out.println("Некорректный выбор поля: " + searchField);
            else break;
        }

        //выбор значения
        System.out.println("\u001b[36;1mВведите значение для поиска:\u001B[0m \n"
                + "\t \u001b[3m(вернуться в главное меню: \u001b[1mcancel\u001B[0m)");
        Scanner consoleValue = new Scanner(System.in);
        searchValue = consoleValue.nextLine().trim();

        //выбор типа поиска
        for (;;) {
            System.out.println("\u001b[36;1mВведите тип поиска (= или %):\u001B[0m \n"
                    + "\t \u001b[3m(вернуться в главное меню: \u001b[1mcancel\u001B[0m)");
            Scanner console = new Scanner(System.in);
            searchType = console.nextLine().trim();
            if (searchType.equals("=")) {
                searchString = " = '" + searchValue + "'";
                descr = "= " + searchValue;
                break;
            }
            if (searchType.equals("%")) {
                searchString = " LIKE '%%" + searchValue + "%%'";
                descr = "LIKE " + searchValue;
                break;
            }
        }

        System.out.println("\u001B[33mПоиск по полю " + searchField + " со значением " + descr + "\u001B[0m");
        loader.loader(0);
        try (ResultSet foundResultSet = animalTable.selectQ(
                "SELECT * FROM %s WHERE " + searchField + searchString +";")) {
            loader.loader(1);

            while (foundResultSet.next()) {
                Map<String, Object> animalValues = new HashMap<>();
                animalValues.put("type", foundResultSet.getString("type"));
                animalValues.put("name", foundResultSet.getString("name"));
                animalValues.put("age", foundResultSet.getInt("age"));
                animalValues.put("weight", foundResultSet.getFloat("weight"));
                animalValues.put("color", foundResultSet.getString("color"));
                animalValues.put("id", foundResultSet.getInt("id"));

                String type = animalValues.get("type").toString();

                //создаём экземпляр дочернего класса через фабрику
                AbstractAnimal createdAnimal = animalFactory.create(
                        AnimalTypesData.valueOf(type.toUpperCase()),
                        animalValues.get("name").toString(),
                        Integer.parseInt(animalValues.get("age").toString()),
                        Float.parseFloat(animalValues.get("weight").toString()),
                        animalValues.get("color").toString(),
                        Integer.parseInt(animalValues.get("id").toString())
                );
                animalList.setListOfFoundAnimalsAnimals(createdAnimal);
            }
        }
        animalList.printTableListAnimals(animalList.getListOfFoundAnimals(), searchField);

    }

    public void deleteAnimal() {
        //Создаём экземпляр таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        Funcs miscFuncs = new Funcs();
        Loader loader = new Loader();

        String input = miscFuncs.inputWithRegexValidate(
                "^[0-9 ,]+$",
                "Введите id записи, которую хотите удалить. Либо перечислите id через запятую для массового удаления.",
                "Некорректные значения id"
        );
        if (input == null) return;  //выход в главное меню через 'cancel'

        String[] ids = input.split(",");

        StringBuilder deletedList = new StringBuilder();

        loader.loader(0);

        Arrays.stream(ids)
                .map(key -> key.trim()) //собираем со срезанными пробелами
                .filter(key -> !key.isEmpty()) //выкидываем пустые значения id
                .mapToInt(key -> Integer.parseInt(key)) // пересобираем с преобразованием стрингов в инты
                .forEach(thisId -> { //гоним цикл
                    try (ResultSet animalById = animalTable.selectWhereId(thisId)) {
                        //проверяем наличие такого id в базе
                        if (animalById.next()) {

                            //удаляем из базы
                            animalTable.deleteWhereId(thisId);
                            deletedList.append("Животное с id #").append(thisId)
                                    .append(" успешно удалено.\n");

                        } else {
                            deletedList.append("Несуществующий id #").append(thisId)
                                    .append(". Удаление невозможно.\n");
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
        loader.loader(1);
        System.out.print(deletedList);

    }


}
