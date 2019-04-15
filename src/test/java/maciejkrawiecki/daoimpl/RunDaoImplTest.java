package maciejkrawiecki.daoimpl;

import maciejkrawiecki.dao.RunDao;
import maciejkrawiecki.entity.Run;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {

    @Test
    void save() {

        // given

        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        run.setId(1);
        run.setName("first run");
        run.setPlace("krakow");
        run.setMembersLimit(100);

        // when

        try {
            runDao.save(run);
            Run testRun = runDao.getBy(1);

            // then

            assertEquals(run.getId(),testRun.getId());
            assertEquals(run.getName(),testRun.getName());
            assertEquals(run.getPlace(),testRun.getPlace());
            assertEquals(run.getMembersLimit(),run.getMembersLimit());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getBy() {
    }

    @Test
    void getAll() {
    }
}