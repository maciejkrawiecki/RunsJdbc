package maciejkrawiecki.daoimpl;

import maciejkrawiecki.dao.RunDao;
import maciejkrawiecki.entity.Run;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RunDaoImplTest {

    @Test
    void save() {

        // given

        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        run.setId(14);
        run.setName("first run");
        run.setPlace("krakow");
        run.setMembersLimit(100);

        // when

        try {
            runDao.save(run);
            Run testRun = runDao.getBy(14);

            // then

            assertEquals(run.getId(),testRun.getId());
            assertEquals(run.getName(),testRun.getName());
            assertEquals(run.getPlace(),testRun.getPlace());
            assertEquals(run.getMembersLimit(),run.getMembersLimit());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assertNull(runDao.getBy(99));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {

        // given

        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        run.setId(12);
        run.setName("second run");
        run.setPlace("sanok");
        run.setMembersLimit(100);

        Run runUpdate = new Run();

        runUpdate.setId(12);
        runUpdate.setName("updated run");
        runUpdate.setPlace("wroclaw");
        runUpdate.setMembersLimit(1000);

        try {
            runDao.save(run);

            // when

            runDao.update(runUpdate);
            Run runGetUpdated = runDao.getBy(12);

            // then

            assertEquals(runUpdate.getId(),runGetUpdated.getId());
            assertEquals(runUpdate.getName(),runGetUpdated.getName());
            assertEquals(runUpdate.getPlace(),runGetUpdated.getPlace());
            assertEquals(runUpdate.getMembersLimit(),runGetUpdated.getMembersLimit());

        } catch (SQLException e) {
            e.printStackTrace();
        }

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