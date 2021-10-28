package ru.job4j.search;

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
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
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
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
