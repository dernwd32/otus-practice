package db.tables;

import animals.AbsAnimal;
import animals.AnimalList;
import data.AnimalTypesData;
import factory.FactoryAnimal;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTable extends AbsTable{
    private static final String TABLE_NAME = "animals";
    public AnimalTable() {
        super(TABLE_NAME);
    }


    public void insert(AbsAnimal animal, String type){
        dbConnect.execute(String.format("INSERT INTO %s (id, color, name, weight, type, age)"
                + "VALUES(NULL, '%s', '%s', '%s', '%s', '%s')" ,
                TABLE_NAME,
                animal.getColor(),
                animal.getName(),
                animal.getWeight(),
                type.toUpperCase(),
                animal.getAge()
        ));
        switchUpdateStatus(true);
    }

    public void update(String color, String name, float weight, String type, int age, int id) {
        String query = String.format("UPDATE %s SET " +
                        "color = '%s', name = '%s', weight = '%s', type = '%s', " +
                        "age = '%s' WHERE id = %s;" ,
                TABLE_NAME, color, name, weight, type, age,  id
        );

       // System.out.println(query);
        dbConnect.execute(query);

        switchUpdateStatus(true);
    }

    public void delete(int id) {
        String query = String.format( "DELETE FROM %s WHERE id = " + id +";" , TABLE_NAME,  id );
        // System.out.println(query);
        dbConnect.execute(query);

        switchUpdateStatus(true);
    }


    public AnimalList read() throws SQLException {
        //создаем список животных
        AnimalList listAnimals = new AnimalList();

        try (ResultSet resultSet = dbConnect.executeQuery(String.format("SELECT * FROM %s;", TABLE_NAME))) {
            while (resultSet.next()) {

                //создаём экземпляр фабрики
                FactoryAnimal factoryAnimal = new FactoryAnimal();
                //создаём экземпляр дочернего класса через фабрику
                AbsAnimal createdAnimal = factoryAnimal.create(
                        AnimalTypesData.valueOf(resultSet.getString("type").toUpperCase()),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getFloat("weight"),
                        resultSet.getString("color"),
                        resultSet.getInt("id")
                );

                //загружаем животного в список животных
                listAnimals.setListAnimals(createdAnimal);
            }
        }

        switchUpdateStatus(false);
        return listAnimals;
    }

}
