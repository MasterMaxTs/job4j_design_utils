package ru.job4j.io.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String readPath;
    private final String writePath;
    private final Map<String, String> values = new HashMap<>();

    public Config(String readPath, String writePath) {
        this.readPath = readPath;
        this.writePath = writePath;
    }

    public void load() {
        Map<String, String> hm = new HashMap<>();
        try (BufferedReader read = new BufferedReader(
                new FileReader(readPath));
             BufferedWriter out = new BufferedWriter(
                     new FileWriter(writePath)
             )
        ) {
            hm = read.lines()
                        .filter(str -> !str.equals("") && !str.startsWith("#"))
                        .collect(Collectors.toMap(
                                k -> {
                                        int index = k.indexOf("=");
                                        if (index == -1) {
                                            throw new IllegalArgumentException();
                                        }
                                        String key = k.substring(0, index);
                                        if (key.equals("")) {
                                            throw new IllegalArgumentException();
                                        }
                                        return key;
                                     },
                                v -> {
                                        int index = v.indexOf("=");
                                        String value = v.substring(index + 1);
                                        if (value.equals("")) {
                                            throw new IllegalArgumentException();
                                        }
                                        return value;
                                     }
                                )
                        );
            values.putAll(hm);
            if (values.isEmpty()) {
                throw new IllegalArgumentException();
            }
            for (String key
                    : values.keySet()) {
                out.write(key + "=" + values.get(key));
                out.write(System.lineSeparator());
            }
            System.out.println("File has been written in " + writePath);
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
                new FileReader(this.readPath)
        )) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config(
                "./src/main/java/ru/job4j/io/config/app.properties",
                "./data/pair_without_comment.properties"
                );
        config.load();
    }
}
