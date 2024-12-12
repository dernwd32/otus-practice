package db;

import settings.Settings;
import java.sql.*;
import java.util.Map;

public class MySQLConnect implements IDBConnect {
    private Connection connection;
    private Statement statement;
    //считываем настройки подключения из файла
    private Settings confReader = new Settings();
    private Map<String,String> confData = confReader.getSettings();

    //открытие подключения к бд
    private void open(){

        //открываем подключение
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        confData.get("url"),
                        confData.get("username"),
                        confData.get("password")
                );
            }
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void execute(String sqlRequest) {
        open();
        try {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet executeQuery(String sqlRequest) {
        open();
        try {
            return statement.executeQuery(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
