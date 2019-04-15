package maciejkrawiecki.dao;

import maciejkrawiecki.entity.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

    void save (Member member) throws SQLException;
    void update (Member member) throws SQLException;
    void delete (Integer id) throws SQLException;
    Member getBy (Integer id) throws SQLException;
    List<Member> getAll() throws SQLException;
}
