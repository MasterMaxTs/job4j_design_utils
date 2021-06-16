package ru.job4j.io.config;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(
                new FileReader(path))
        ) {
            values = read.lines()
                    .filter(l -> !l.equals("") && !l.startsWith("#"))
                    .map(str -> str.split("="))
                    .collect(
                            Collectors.toMap(
                                            k -> {
                                                    if (k.length < 2) {
                                                        throw new IllegalArgumentException(
                                                                "Incorrect data in file: " + path
                                                        );
                                                    }
                                                    return k[0];
                                            },
                                            v -> v[1]
                            )
                    );
            if (values.isEmpty()) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(
                new FileReader(this.path)
        )) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public void showData() {
        System.out.println("Config has been written!");
        for (Map.Entry<String, String> entry
                : values.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Config config = new Config("./src/main/java/ru/job4j/io/config/app.properties");
        config.load();
        config.showData();
    }
}
