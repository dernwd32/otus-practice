package db.tables;

import db.IDBConnect;
import db.MySQLConnect;

import java.sql.ResultSet;
import java.util.List;

abstract class AbstractTable implements ITable{
    protected IDBConnect dbConnect;
    private String tableName;

    public AbstractTable(String tableName) {
        dbConnect = new MySQLConnect();
        this.tableName = tableName;
    }

    @Override
    public void create(List<String> columns) {
        drop();
        dbConnect.execute(String.format("CREATE TABLE %s(%s);", tableName, String.join(",", columns)));
    }

    @Override
    public void drop() {
        dbConnect.execute(String.format("DROP TABLE  if exists %s;", tableName));
    }

    @Override
    public ResultSet selectAll() {
        return  dbConnect.executeQuery(String.format("SELECT * FROM %s;", tableName));
    }
    @Override
    public ResultSet selectWhereId(int id) {
        return dbConnect.executeQuery(String.format("SELECT * FROM %s WHERE id = " + id + ";", tableName));
    }
    @Override
    public ResultSet selectQ(String query) {
        return dbConnect.executeQuery(String.format(query, tableName));
    }
}
