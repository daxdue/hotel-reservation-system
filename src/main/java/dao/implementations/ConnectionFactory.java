package dao.implementations;

import enums.SourceType;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static final String URL_POSTGRES = "jdbc:postgresql://127.0.0.1:5432/hotelsystem";
    public static final String USER_POSTGRES = "postgres";
    public static final String PASS_POSTGRES = "qzwxecasd123";


    public static Connection getConnection(SourceType sourceType) {

        Connection connection = null;
        switch (sourceType) {
            case POSTGRES:
                try {
                    DriverManager.registerDriver(new Driver());
                    connection = DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASS_POSTGRES);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return connection;
    }

}
