package db.tables;

import app.Main;

import java.util.List;

public interface ITable {
    void create(List<String> column);
    void drop();
    default void switchUpdateStatus(boolean bool){
        //для многопользовательской программы логика должна быть совсем иной.
        //может, будет лучше идея, поэтому пусть в отдельном методе лежит
        Main.ifUpdated = bool;
    }
}
