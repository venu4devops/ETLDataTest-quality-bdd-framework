package config;

import utils.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            String url = ConfigReader.getProperty("db.url");
            String user = ConfigReader.getProperty("db.user");
            String password = ConfigReader.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }
}