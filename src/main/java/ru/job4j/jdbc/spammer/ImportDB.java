package ru.job4j.jdbc.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(dump)
        )) {
            String line;
            final int POS = 0;
            while ((line = br.readLine()) != null) {
               users.add(new User(
                       line.split(";")[POS],
                       line.split(";")[POS + 1])
               );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty(ConnectKeys.DRIVER.key));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty(ConnectKeys.URL.key),
                cfg.getProperty(ConnectKeys.LOGIN.key),
                cfg.getProperty(ConnectKeys.PASSWORD.key)
        )) {
            for (User user
                    : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users (name, email) VALUES (?, ?)"
                )) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                    System.out.println("Data has been saved in spammer db");
                }
            }
        }

    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    enum ConnectKeys {
        DRIVER("jdbc.driver"),
        URL("jdbc.url"),
        LOGIN("jdbc.username"),
        PASSWORD("jdbc.password");

        private final String key;

        ConnectKeys(String key) {
            this.key = key;
        }
    }

    public static void main(String[] args)
                    throws IOException, SQLException, ClassNotFoundException {
        Properties cfg = new Properties();
        try (FileInputStream fis =
                     new FileInputStream(
                             "./src/main/java/ru/job4j/jdbc/spammer/app.properties")) {
            cfg.load(fis);
        }
        ImportDB idb = new ImportDB(cfg, "./src/main/java/ru/job4j/jdbc/spammer/dump.txt");
        idb.save(idb.load());
    }
}

