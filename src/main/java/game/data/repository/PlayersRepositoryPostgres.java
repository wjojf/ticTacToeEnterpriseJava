package game.data.repository;

import game.domain.repository.IPlayersRepository;
import game.domain.models.Player;
import game.domain.models.PlayerLoginInput;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class PlayersRepositoryPostgres implements IPlayersRepository {

    private static final String dsn = "jdbc:postgresql://0.0.0.0:5432/test";
    public static final String username = "postgres";
    public static final String  password = "postgres";

    public PlayersRepositoryPostgres() throws SQLException {
        initTables();
    }

    public boolean isUserExists(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT user_id FROM users WHERE username=?");

        st.setString(1   , loginInput.username);

        ResultSet stResult = st.executeQuery();

        return stResult.first();
    }

    public boolean isPasswordMatched(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT password FROM users WHERE username=? AND password=?");
        st.setString(1, loginInput.username);
        st.setString(2, loginInput.password);

        ResultSet stResult = st.executeQuery();
        return stResult.first();
    }

    public void createNewUser(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?, ?);"
        );
        st.setString(1, loginInput.username);
        st.setString(2, loginInput.password);

        st.executeUpdate();
        conn.commit();
    }
    
    public Player getPlayerByUsername(String username) throws SQLException, InvalidKeyException {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement(
                "SELECT id, username FROM users WHERE username = ?"
        );

        st.setString(1, username);

        ResultSet rs = st.executeQuery();

        if (!rs.first()) {
            throw new InvalidKeyException("user not found.");
        }

        Integer id = Integer.valueOf(rs.getString("id"));

        return new Player(id, username);
        
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dsn, username, password);
    }

    private static void initTables() throws SQLException {
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
                  "user_id int primary key not null," +
                  "foreign key (user_id) references users(id)," +
                  "games_won int not null default 0," +
                  "games_draw int not null default 0," +
                  "games_lost int not null default 0" +
                  ")"
        );

        conn.commit();

    }

}
