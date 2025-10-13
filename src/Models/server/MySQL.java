package Models.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MRBLUE
 */
public class MySQL {

    private final Connection conn;
    private static final Object LOCK = new Object();
    private static final String URL_FORMAT = "jdbc:mariadb://%s/%s";

    private MySQL(String host, String database, String user, String pass) throws SQLException {
        synchronized (LOCK) {
            this.conn = DriverManager.getConnection(String.format(URL_FORMAT, host, database), user, pass);
        }
    }

    public static MySQL create() throws SQLException {
        return new MySQL("localhost", "ngocrongz", "root", "01102000");
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
