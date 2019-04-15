package maciejkrawiecki.daoimpl;

import maciejkrawiecki.dao.RunDao;
import maciejkrawiecki.entity.Run;
import maciejkrawiecki.util.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RunDaoImpl implements RunDao {

    private PreparedStatement prepareStatement (String sql) throws SQLException{
        return JdbcUtils
               .getInstance()
               .getConnection()
                .prepareStatement(sql);
    }

    private Run createRun(ResultSet resultSet) throws SQLException{
        Run run = new Run();
        run.setId(resultSet.getInt("id"));
        run.setName(resultSet.getString("name"));
        run.setPlace(resultSet.getString("place"));
        run.setMembersLimit(resultSet.getInt("members_limit"));

        return run;
    }

    public void save(Run run) throws SQLException {
        String sql = "INSERT INTO runs (id, name, place, members_limit) VALUES (?,?,?,?)";

        PreparedStatement statement = prepareStatement(sql);

        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setString(3,run.getPlace());
        statement.setInt(4,run.getMembersLimit());

        statement.executeUpdate();

    }

    public void update(Run run) throws SQLException {
        String sql = "UPDATE runs SET name = ?, place = ?, members_limit = ? WHERE id = ?";

        PreparedStatement statement = prepareStatement(sql);

        statement.setString(1,run.getName());
        statement.setString(2,run.getPlace());
        statement.setInt(3,run.getMembersLimit());
        statement.setInt(4,run.getId());

        statement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {

    }


    public Run getBy(Integer id) throws SQLException {
        String sql = "SELECT * FROM runs WHERE id = ?";
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(sql);

        statement.setInt(1,id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) return  createRun(resultSet);
        return null;
    }

    public List<Run> getAll() throws SQLException {
        return null;
    }
}
