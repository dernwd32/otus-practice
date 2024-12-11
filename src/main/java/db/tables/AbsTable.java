package db.tables;

import db.IDBConnect;
import db.MySQLConnect;

import java.sql.ResultSet;
import java.util.List;

public class AbsTable implements ITable{
    protected IDBConnect dbConnect;
    private String tableName;

    public AbsTable(String tableName) {
        dbConnect = new MySQLConnect();
        this.tableName = tableName;
    }

    @Override
    public void create(List<String> columns) {
        delete();
        dbConnect.execute(String.format("CREATE TABLE %s(%s);", tableName, String.join(",", columns)));
    }

    @Override
    public void delete() {
        dbConnect.execute(String.format("DROP TABLE  if exists %s;", tableName));
    }

    public ResultSet selectAll() {
        return  dbConnect.executeQuery(String.format("SELECT * FROM %s;", tableName));
    }

}
