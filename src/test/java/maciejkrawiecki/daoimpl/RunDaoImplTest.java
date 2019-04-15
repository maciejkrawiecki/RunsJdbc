package maciejkrawiecki.daoimpl;

import maciejkrawiecki.dao.RunDao;
import maciejkrawiecki.entity.Run;
import maciejkrawiecki.util.JdbcUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RunDaoImplTest {

    @Before
    void truncateTable() {
        String sql = "TRUNCATE TABLE runs";
        try {
            Statement statement = JdbcUtils
                    .getInstance()
                    .getConnection()
                    .createStatement();

            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

            assertEquals(run.getId(), testRun.getId());
            assertEquals(run.getName(), testRun.getName());
            assertEquals(run.getPlace(), testRun.getPlace());
            assertEquals(run.getMembersLimit(), run.getMembersLimit());

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

        run.setId(2);
        run.setName("second run");
        run.setPlace("sanok");
        run.setMembersLimit(100);

        Run runUpdate = new Run();

        runUpdate.setId(2);
        runUpdate.setName("updated run");
        runUpdate.setPlace("wroclaw");
        runUpdate.setMembersLimit(1000);

        try {
            runDao.save(run);

            // when

            runDao.update(runUpdate);
            Run runGetUpdated = runDao.getBy(2);

            // then

            assertEquals(runUpdate.getId(), runGetUpdated.getId());
            assertEquals(runUpdate.getName(), runGetUpdated.getName());
            assertEquals(runUpdate.getPlace(), runGetUpdated.getPlace());
            assertEquals(runUpdate.getMembersLimit(), runGetUpdated.getMembersLimit());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void delete() {

        // given

        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        run.setId(3);
        run.setName("second run");
        run.setPlace("sanok");
        run.setMembersLimit(100);

        try {
            runDao.save(run);

            // when

            runDao.delete(3);
            Run deleted = runDao.getBy(3);

            // then

            assertNull(deleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBy() {

        // given

        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        run.setId(4);
        run.setName("run");
        run.setPlace("sanok");
        run.setMembersLimit(100);

        try {
            runDao.save(run);

            // when

            Run runTest = runDao.getBy(4);

            // then

            assertEquals(run.getId(), runTest.getId());
            assertEquals(run.getName(), runTest.getName());
            assertEquals(run.getPlace(), runTest.getPlace());
            assertEquals(run.getMembersLimit(), runTest.getMembersLimit());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {

        // given

        List<Run> runs = new ArrayList<>();

        RunDao runDao = new RunDaoImpl();

        IntStream.range(0, 5).forEach(i -> {
            Run run = new Run();
            run.setId(i + 5);
            run.setName("name " + i);
            run.setMembersLimit(100);
            run.setPlace("place");
            runs.add(run);
            try {
                runDao.save(run);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // when

        try {
            List<Run> runsTest = runDao.getAll();

            // then

            assertEquals(runsTest.get(0).getName(), runs.get(0).getName());
            assertEquals(runsTest.get(1).getName(), runs.get(1).getName());
            assertEquals(runsTest.get(2).getName(), runs.get(2).getName());
            assertEquals(runsTest.get(3).getName(), runs.get(3).getName());
            assertEquals(runsTest.get(4).getName(), runs.get(4).getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}