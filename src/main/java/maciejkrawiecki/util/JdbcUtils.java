package maciejkrawiecki.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static JdbcUtils instance = new JdbcUtils();
    private Connection connection;

    private JdbcUtils (){

        String connectionStr = "jdbc:mysql://localhost:3306/Runs";
        Properties prop= new Properties();
        prop.put("password","password");
        prop.put("user","newuser");

        try {
            connection =  DriverManager.getConnection(connectionStr,prop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtils getInstance(){
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
