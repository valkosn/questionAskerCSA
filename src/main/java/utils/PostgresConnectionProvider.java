package utils;

import service.ISettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Valko Serhii on 16-Sep-16.
 */
public class PostgresConnectionProvider implements ConnectionProvider {
    private Connection connection;

    public PostgresConnectionProvider(ISettings settings) {
        //jdbc:postgresql://127.0.0.1:5432/asker?user=postgres&password=21RhWiI_3820
        String fullURL = System.getenv("JDBC_URL_TO_DB");

        try {
            Class.forName(settings.getValue("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(fullURL);
        } catch (SQLException e) {
            try {
                this.connection = DriverManager.getConnection(settings.getValue("jdbc.url"), settings.getValue("jdbc.login"), settings.getValue("jdbc.password"));
            } catch (SQLException e1) {
                throw new RuntimeException("Couldn't get connection", e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class not found", e);
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
