package ru.job4j.ood.dip.connection;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PATH = "";
    private Connection connection;

    /*Создание объекта класса инициализирует конкретную реализацию подключения,
    * что является нарушением принципов DIP, OCP  */
    public ConnectionDB() throws SQLException {
        init();
    }

    private void init() throws SQLException {
        try {
            Class.forName(getDataConnection(DRIVER));
            connection = DriverManager.getConnection(
                    getDataConnection(URL),
                    getDataConnection(LOGIN),
                    getDataConnection(PASSWORD));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getDataConnection(String query) {
        Properties properties = new Properties();
        try (Reader in = new FileReader(PATH)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(query);
    }
}
