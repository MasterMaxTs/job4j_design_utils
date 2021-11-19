package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;
    private final static String DRIVER = "driver";
    private final static String URL = "url";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    public TableEditor(Properties properties)
                                    throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty(DRIVER));
        connection = DriverManager.getConnection(
                properties.getProperty(URL),
                properties.getProperty(LOGIN),
                properties.getProperty(PASSWORD)
        );
    }

    public void modifyTable(String tableName, String sql) throws SQLException {
        try (Connection connection = this.connection) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.println("operation completed successfully");
                System.out.println(
                        getTableScheme(connection, tableName)
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();", tableName);
        modifyTable(tableName, sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "DROP TABLE " + tableName;
        modifyTable(tableName, sql);
    }

    public void addColumn(String tableName, String columnName, String type)
                                                        throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s add COLUMN %s %s;", tableName, columnName, type
        );
        modifyTable(tableName, sql);
    }
    public void dropColumn(String tableName, String columnName)
                                                        throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName
        );
        modifyTable(tableName, sql);
    }
    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        modifyTable(tableName, sql);
    }

    public static String getTableScheme(Connection connection, String tableName)
                                                            throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String pathToProperty = "./src/main/java/ru/job4j/jdbc/app.properties";
        try (Reader in = new FileReader(pathToProperty)) {
            Properties properties = new Properties();
            properties.load(in);
            TableEditor te = new TableEditor(properties);
            String tableName = "demo_table";
            /*Testing TableEditor*/
            /*te.createTable(tableName);*/
            /*te.addColumn(tableName, "id", "serial primary key");*/
            /*te.addColumn(tableName, "first_name", "varchar(40)");*/
            /*te.addColumn(tableName, "second_name", "varchar(80)");*/
            /*te.renameColumn(tableName, "second_name", "surname");*/
            /*te.addColumn(tableName, "gender", "char(1)");*/
            /*te.dropColumn(tableName, "gender");*/
            /*te.dropTable(tableName);*/
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
