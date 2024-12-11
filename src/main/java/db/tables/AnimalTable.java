package db.tables;

import animals.AbsAnimal;
import animals.AnimalList;
import animals.InputAnimal;
import data.AnimalTypesData;
import db.MySQLConnect;
import factory.FactoryAnimal;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTable extends AbsTable{
    private static final String NAME="animals";
    public AnimalTable() {
        super(NAME);
    }

    public void write(AbsAnimal animal, String type){
        dbConnect.execute(String.format("INSERT INTO %s (id, color, name, weight, type, age)"
                + "VALUES(NULL, '%s', '%s', '%s', '%s', '%s')" ,
                NAME,
                animal.getColor(),
                animal.getName(),
                animal.getWeight(),
                type.toUpperCase(),
                animal.getAge()
        ));
    }

    public AnimalList read() throws SQLException {
        //создаем список животных
        AnimalList listAnimals = new AnimalList();
        //Создаём экземпляр класса InputAnimal для вызова метода inputAnimals(), создающего новое животное
        InputAnimal inputAnimal = new InputAnimal();

        //ArrayList<AbsAnimal> listAnimals = new ArrayList<>();
        ResultSet resultSet;
        dbConnect = new MySQLConnect();
        resultSet = this.dbConnect.executeQuery(String.format("SELECT * FROM %s;", NAME));
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String color = resultSet.getString("color");
            String name = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            String type = resultSet.getString("type");
            int age = resultSet.getInt("age");



           //AbsAnimal animal = new AbsAnimal(id, color, name, weight, type, age);

            //вызываем метод, возвращающий созданное по введённым параметрам животное
            //AbsAnimal createdAnimal = inputAnimal.inputAnimal();
            //listAnimals.add(animal);
            //создаём экземпляр фабрики, чтоб избавиться от объявления статиком всего класса
            FactoryAnimal factoryAnimal = new FactoryAnimal();
            //создаём экземпляр дочернего класса через фабрику и отправляем его обратно в main() в listAnimals.
            AbsAnimal createdAnimal = factoryAnimal.create(
                    AnimalTypesData.valueOf(type.toUpperCase()), name, age, weight, color);
            listAnimals.setListAnimals(createdAnimal);
        }
        return listAnimals;
    }
}
