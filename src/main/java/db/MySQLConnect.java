package db;

import settings.Settings;
import java.sql.*;
import java.util.Map;

public class MySQLConnect implements IDBConnect {
    private static Connection connection = null;
    private static Statement statement = null;
    //считываем настройки подключения из файла
    private Settings settings = new Settings();
    private Map<String,String> confData = settings.getSettings();

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
   // @Override
    public void close()  {
        if (statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
