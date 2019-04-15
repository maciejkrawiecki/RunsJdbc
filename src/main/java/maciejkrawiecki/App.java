package maciejkrawiecki;

import maciejkrawiecki.util.JdbcUtils;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
       Connection connection = JdbcUtils.getInstance().getConnection();
    }
}
