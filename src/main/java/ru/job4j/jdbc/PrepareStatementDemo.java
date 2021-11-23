package ru.job4j.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemo {

    private Connection connection;
    private static String path = "";
    private static String tableName = "";

    enum ConnectKeys {
        DRIVER("driver"), URL("url"), LOGIN("login"), PASSWORD("password");
        private final String key;

        ConnectKeys(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public PrepareStatementDemo()
            throws SQLException, FileNotFoundException, ClassNotFoundException {
        initConnection();
    }

    public void initConnection()
            throws FileNotFoundException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        try (Reader in = new FileReader(PrepareStatementDemo.path)) {
            properties.load(in);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Class.forName(properties.getProperty(ConnectKeys.DRIVER.getKey()));
        connection = DriverManager.getConnection(
            properties.getProperty(ConnectKeys.URL.getKey()),
            properties.getProperty(ConnectKeys.LOGIN.getKey()),
            properties.getProperty(ConnectKeys.PASSWORD.getKey())
        );
    }

    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                   String.format("CREATE TABLE IF NOT EXISTS %s("
                                   + "id SERIAL PRIMARY KEY,"
                                   + "name VARCHAR(40),"
                                   + "population INT)",
                           PrepareStatementDemo.tableName
            ));
        }
    }

    public void insert(City city) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
              String.format("INSERT INTO %s (name, population) VALUES (?, ?)",
                      PrepareStatementDemo.tableName
              )
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                String.format(
                        "UPDATE %s SET name = ? , population = ? WHERE id = ?",
                        PrepareStatementDemo.tableName)
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
               String.format("DELETE FROM %s WHERE id = ?",
                       PrepareStatementDemo.tableName
        ))) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM %s",
                        PrepareStatementDemo.tableName)
        )) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cities.add(
                            new City(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getInt("population")
                            )
                    );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(cities);
        return cities;
    }

    public City insertWithId(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                String.format("INSERT INTO %s (name, population) VALUES"
                        + "(? , ?)", PrepareStatementDemo.tableName),
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt("id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }

    public static void main(String[] args)
            throws SQLException, FileNotFoundException, ClassNotFoundException {
        path = "./src/main/java/ru/job4j/jdbc/app.properties";
        tableName = "cities";
        PrepareStatementDemo psd = new PrepareStatementDemo();
        psd.initConnection();
        /*psd.insert(new City(3, "Belarus", 17_000_000));*/
        /*psd.update(new City(2, "Kazahstan", 68_000_000));*/
        psd.findAll();
        /*psd.insertWithId(new City(5, "Armeniya", 15_000_000));*/
        /*psd.delete(3);*/
    }

}
