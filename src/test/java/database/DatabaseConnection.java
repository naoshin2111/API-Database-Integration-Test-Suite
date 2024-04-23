package database;

import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static config.DatabaseConfig.*;

@Slf4j
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(getDatabaseUrl(), getUserName(), getPassword());
            }
        } catch (SQLException e) {
            log.error("Failed to establish database connection.", e);
            throw new RuntimeException("Error establishing database connection", e);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Could not close database connection.", e);
            throw new RuntimeException("Error closing database connection", e);
        }
    }
}
