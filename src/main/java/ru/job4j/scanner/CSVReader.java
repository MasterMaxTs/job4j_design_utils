package ru.job4j.scanner;

import ru.job4j.io.namedargs.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public void handle(ArgsName argsName) throws Exception {
        String fileName = argsName.get("path");
        Path path = Paths.get(fileName);
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        try (Scanner scanner = new Scanner(path, Charset.forName("WINDOWS-1251"))) {
            String firstLine = scanner.nextLine();
            List<String> columns = Arrays.asList(firstLine.split(delimiter));
            int[] position = getIndexes(columns, filter);
            String result = getReader(delimiter, filter, position, scanner);
            showResult(out, result);
        }
    }

    public int[] getIndexes(List<String> columns, String[] filter) {
        int[] indexes = new int[filter.length];
            for (int i = 0; i < filter.length; i++) {
                int index = columns.indexOf(filter[i]);
                if (index == -1) {
                    throw new NoSuchElementException("Filter is not correct!");
                }
                indexes[i] = index;
            }
            return indexes;
    }

    public String getReader(String delimiter, String[] filter, int[] position, Scanner sc) {
        StringBuilder builder = new StringBuilder();
        String header = String.join(delimiter, filter);
        String ls = System.lineSeparator();
        builder.append(header).append(ls);
        String[] strings = new String[position.length];
        String rsl;
        while (sc.hasNextLine()) {
            String[] nextLine = sc.nextLine().split(delimiter);
            for (int i = 0; i < position.length; i++) {
                strings[i] = nextLine[position[i]];
            }
            rsl = String.join(delimiter, strings);
            builder.append(rsl).append(ls);
        }
        return builder.toString();
    }

    public void showResult(String out, String result) {
        if (out.equals("stdout")) {
            System.out.println(result);
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
            pw.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArgsName validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar target/csvReader.jar -path=pathTofile -delimiter=delimiter"
                    + "-out=stdout or pathToOutFile -filter=filter");
        }
        return ArgsName.of(args);
    }

    public static void main(String[] args) throws Exception {
        CSVReader csvReader = new CSVReader();
        ArgsName jvm = csvReader.validate(args);
        csvReader.handle(jvm);
    }
}
