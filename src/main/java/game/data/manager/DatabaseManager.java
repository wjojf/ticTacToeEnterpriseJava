package game.data.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String dsn = "jdbc:postgresql://0.0.0.0:5432/test";
    private static final String username = "postgres";
    private static final String  password = "postgres";

    public DatabaseManager() throws SQLException {
        initTables();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dsn, username, password);
    }

    private void initTables() throws SQLException {
        Connection conn = getConnection();

        Statement st = conn.createStatement();

        st.execute(
                "CREATE TABLE IF NOT EXISTS users(" +
                        "id SERIAL primary key not null," +
                        "username varchar(255) unique not null," +
                        "password varchar(255) not null," +
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
