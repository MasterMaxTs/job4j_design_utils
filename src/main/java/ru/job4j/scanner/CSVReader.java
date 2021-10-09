package ru.job4j.scanner;

import ru.job4j.io.namedargs.ArgsName;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String fileName = argsName.get("path");
        Path path = Paths.get(fileName);
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        String header = String.join(delimiter, filter);
        String ls = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        builder.append(header).append(ls);
        try (Scanner scanner = new Scanner(path, Charset.forName("WINDOWS-1251"))) {
            String firstLine = scanner.nextLine();
            List<String> columns = Arrays.asList(firstLine.split(delimiter));
            int[] indexes = new int[filter.length];
            for (int i = 0; i < filter.length; i++) {
                int index = columns.indexOf(filter[i]);
                if (index == -1) {
                    throw new NoSuchElementException("Filter is not correct!");
                }
                indexes[i] = index;
            }
            String[] strings = new String[indexes.length];
            String rsl;
            while (scanner.hasNextLine()) {
                String[] nextLine = scanner.nextLine().split(delimiter);
                for (int i = 0; i < indexes.length; i++) {
                    strings[i] = nextLine[indexes[i]];
                }
                    rsl = String.join(delimiter, strings);
                    builder.append(rsl).append(ls);
            }
        }
        if (out.equals("stdout")) {
            System.out.print(builder);
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
            pw.print(builder);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar target/csvReader.jar -path=pathTofile -delimiter=delimiter"
                    + "-out=stdout or pathToOutFile -filter=filter");
        }
        ArgsName jvm = ArgsName.of(args);
        handle(jvm);
    }
}
