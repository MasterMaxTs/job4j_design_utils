package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgsName {
    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public void parse(String[] args) {
        values = Stream.of(args)
                .map(str -> str.split("="))
                .collect(
                        Collectors.toMap(
                        k -> {
                                if (k.length != 2) {
                                    throw new IllegalArgumentException();
                                }
                                String key = k[0];
                                return key.substring(1);
                             },
                        v -> v[1]
                )
        );
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
