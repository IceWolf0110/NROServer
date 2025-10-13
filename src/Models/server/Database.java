package Models.server;

import helper.DragonHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author MRBLUE
 */
public class Database {

    private final Connection conn;
    private static final Object LOCK = new Object();
    private static final String URL_FORMAT = "jdbc:mariadb://%s/%s";

    private Database(String host, String database, String user, String password) throws SQLException {
        synchronized (LOCK) {
            this.conn = DriverManager.getConnection(String.format(URL_FORMAT, host, database), user, password);
        }
    }

    public static Database create() throws SQLException {
        var host = DragonHelper.optionGetValue("database", "host");

        var database = DragonHelper.optionGetValue("database", "database");

        var user = DragonHelper.optionGetValue("database", "user");

        var password = DragonHelper.optionGetValue("database", "password");


        return new Database(host, database, user, password);
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
