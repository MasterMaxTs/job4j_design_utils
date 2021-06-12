package ru.job4j.io.config;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(
                new FileReader(path));
        ) {
            List<String> list = read.lines().filter(str -> !str.equals("") && !str.startsWith("#"))
                    .map(s -> s.split("=")).flatMap(Arrays::stream).collect(Collectors.toList());
            if (list.isEmpty() || list.size() % 2 != 0) {
                throw new IllegalArgumentException();
            }
            String key;
            String value;
            for (int i = 0; i < list.size() - 1; i++) {
                key = list.get(i);
                ++i;
                value = list.get(i);
                values.put(key, value);
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

    public static void main(String[] args) {
        Config config = new Config("./src/main/java/ru/job4j/io/config/app.properties");
        config.load();
    }
}
