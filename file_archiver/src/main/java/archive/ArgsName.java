package archive;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Хранилище именованных аргументов командной строки
 */
public class ArgsName {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("Error in <%s> key: invalid key name on command line!", key
                    ));
        }
        return values.get(key);
    }

    public void parse(String[] args) {
        values = Stream.of(args)
                .map(str -> str.split("="))
                .collect(
                        Collectors.toMap(
                                arr -> {
                                    String key = arr[0];
                                    if (arr.length != 2) {
                                        throw new IllegalArgumentException(
                                                String.format("Error in <%s> key: the key must have value!", key
                                                ));
                                    }
                                    return key.substring(1);
                                },
                                arr -> arr[1],
                                (v1, v2) -> {
                                    throw new IllegalArgumentException(
                                            "A duplicate key was detected on the command line! "
                                                    + "The name of the keys on the command line must be unique!"
                                    ); }
                        ));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
