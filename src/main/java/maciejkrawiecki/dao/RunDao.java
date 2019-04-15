package maciejkrawiecki.dao;

import maciejkrawiecki.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {

    void save (Run member) throws SQLException;
    void update (Run member) throws SQLException;
    void delete (Integer id) throws SQLException;
    Run getBy (Integer id) throws SQLException;
    List<Run> getAll() throws SQLException;
}
