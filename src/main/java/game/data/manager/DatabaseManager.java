package game.data.manager;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {

    private static final Dotenv dotEnv = Dotenv.load();
    private static final String dsn = dotEnv.get("JDBC_POSTGRES_URL");
    private static final String username = dotEnv.get("JDBC_POSTGRES_DB_USERNAME");
    private static final String  password = dotEnv.get("JDBC_POSTGRES_DB_PASSWORD");

    public DatabaseManager() throws SQLException {
        System.out.println("Initializing Database Connection...");
        initTables();
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(dsn, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

    private void initTables() throws SQLException {
        Connection conn = getConnection();

        Statement st = conn.createStatement();

        st.execute(
                "CREATE TABLE IF NOT EXISTS users(" +
                        "id SERIAL primary key not null," +
                        "username varchar(255) unique not null," +
                        "password varchar(255) not null" +
                        ")"
        );

        st.execute(
                "CREATE TABLE IF NOT EXISTS users_games_stats(" +
                        "user_id int primary key unique not null," +
                        "foreign key (user_id) references users(id)," +
                        "games_won int not null default 0," +
                        "games_draw int not null default 0," +
                        "games_lost int not null default 0" +
                        ")"
        );

        conn.commit();

    }

}
