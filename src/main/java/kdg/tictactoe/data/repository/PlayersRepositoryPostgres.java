package kdg.tictactoe.data.repository;

import kdg.tictactoe.data.manager.DatabaseManager;
import kdg.tictactoe.domain.repository.IPlayersRepository;
import kdg.tictactoe.domain.models.Player;
import kdg.tictactoe.domain.models.PlayerLoginInput;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class PlayersRepositoryPostgres implements IPlayersRepository {

    private static final String dsn = "jdbc:postgresql://0.0.0.0:5432/test";
    public static final String username = "postgres";
    public static final String  password = "postgres";

    private final DatabaseManager databaseManager;

    public PlayersRepositoryPostgres() throws SQLException {
        this.databaseManager = new DatabaseManager();
    }

    public boolean isUserExists(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT id FROM users WHERE username=?");

        st.setString(1   , loginInput.username);

        ResultSet stResult = st.executeQuery();

        return stResult.next();
    }

    public boolean isPasswordMatched(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT password FROM users WHERE username=? AND password=?");
        st.setString(1, loginInput.username);
        st.setString(2, loginInput.password);

        ResultSet stResult = st.executeQuery();
        return stResult.next();
    }

    public void createNewUser(PlayerLoginInput loginInput) throws SQLException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?, ?);"
        );
        st.setString(1, loginInput.username);
        st.setString(2, loginInput.password);

        st.executeUpdate();
        conn.commit();
    }
    
    public Player getPlayerByUsername(String username) throws SQLException, InvalidKeyException {
        Connection conn =  this.databaseManager.getConnection();

        PreparedStatement st = conn.prepareStatement(
                "SELECT id, username FROM users WHERE username = ?"
        );

        st.setString(1, username);

        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            throw new InvalidKeyException("user not found.");
        }

        Integer id = Integer.valueOf(rs.getString("id"));

        return new Player(id, username);
        
    }

}
