package ru.job4j.jdbc;

import ru.job4j.io.config.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private static String path;
    private final static String URL = "url";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    public String getDataForConnection(String query) {
        Config config = new Config(path);
        config.load();
        return config.value(query);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                getDataForConnection(URL),
                getDataForConnection(LOGIN),
                getDataForConnection(PASSWORD)
        );
    }

    public void showConnectionInfo() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        path = "./src/main/java/ru/job4j/jdbc/app.properties";
       new ConnectionDemo().showConnectionInfo();
    }
}
