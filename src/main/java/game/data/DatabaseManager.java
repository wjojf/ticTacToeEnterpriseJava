package game.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DatabaseManager {

    private static final String dsn = "jdbc:postgresql://0.0.0.0:5432/test";
    public static final String username = "postgres";
    public static final String  password = "postgres";

    public static void testConnection() {
        try {
            Connection conn = DriverManager.getConnection(dsn, username, password);
            System.out.println("PostgresSQL Database connected!");
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

}
