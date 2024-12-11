package db.tables;

import java.util.List;

public interface ITable {
    void create(List<String> column);
    void delete();
}
