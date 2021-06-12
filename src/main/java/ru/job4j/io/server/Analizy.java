package ru.job4j.io.server;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    static final String[] SERVER_STATUS = {"400", "500"};

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))
        ) {
            List<String> list =
                    read.lines()
                            .map(s -> s.split(" "))
                            .flatMap(Arrays::stream)
                            .collect(Collectors.toList());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size() - 1; i++) {
                if (i % 2 == 0
                        && (list.get(i).equals(SERVER_STATUS[0])
                            || list.get(i).equals(SERVER_STATUS[1]))
                ) {
                    stringBuilder.append(list.get(++i).concat(";"));
                    ++i;
                    if (list.get(i).equals(SERVER_STATUS[0])
                            || list.get(i).equals(SERVER_STATUS[1])) {
                        ++i;
                    } else {
                        stringBuilder.append(list.get(++i).concat(";")).append(System.lineSeparator());
                    }
                }
            }
            writer.write(stringBuilder.toString());
            System.out.println("File has been written in '" + target + " '");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        String source = "./src/main/java/ru/job4j/io/server/server.log";
        String target = "./src/main/java/ru/job4j/io/server/unavailable.scv";
        analizy.unavailable(source, target);
    }
}
