package ru.job4j.jdbc;

import ru.job4j.io.config.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private final String path;
    private final static String URL = "url";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    public ConnectionDemo(String path) {
        this.path = path;
    }

    public String getDataForConnection(String query) {
        Config config = new Config(path);
        config.load();
        return config.value(query);
    }

    public void showDbMetaData(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());
    }

    public void showConnectionInfo() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(
                getDataForConnection(URL),
                getDataForConnection(LOGIN),
                getDataForConnection(PASSWORD)
        )) {
            showDbMetaData(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ConnectionDemo cd = new ConnectionDemo(
                "./src/main/java/ru/job4j/jdbc/app.properties");
        cd.showConnectionInfo();
    }
}
