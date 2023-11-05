package reader.csv;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Главный класс приложения
 */
public class CsvReader {

    private static final String PATH = "ptf";
    private static final String DELIMITER = "del";
    private static final String OUT = "out";
    private static final String FILTER = "fil";
    private static final String CHARSET_NAME = "WINDOWS-1251";
    private static final String STDOUT = "stdout";
    private static final int POS = 0;

    public void handle(ArgsName argsName) throws Exception {
        Path path = Paths.get(argsName.get(PATH));
        String delimiter = argsName.get(DELIMITER);
        String[] filter = argsName.get(FILTER).split(",");
        String out = argsName.get(OUT);
        try (Scanner scanner =
                     new Scanner(path, Charset.forName(CHARSET_NAME))) {
            String firstLine = scanner.nextLine();
            List<String> columns = Arrays.asList(firstLine.split(delimiter));
            int[] positions = getIndexes(columns, filter);
            String result = getReader(delimiter, filter, positions, scanner);
            showResult(out, result);
        }
    }

    private int[] getIndexes(List<String> columns, String[] filter) {
        if (columns.size() == 1
                && columns.get(POS).length() > filter[POS].length()) {
            throw new IllegalArgumentException(
                    "Incorrect value in parameter -del= !"
                            + " Delimiter is not correct!"
            );
        }
        int[] indexes = new int[filter.length];
            for (int i = 0; i < filter.length; i++) {
                int index = columns.indexOf(filter[i]);
                if (index == -1) {
                    throw new NoSuchElementException(
                            "Incorrect value in parameter -fil= !"
                                    + " Filter is not correct!");
                }
                indexes[i] = index;
            }
            return indexes;
    }

    private String getReader(String delimiter,
                             String[] filter,
                             int[] positions,
                             Scanner sc) {
        StringBuilder builder = new StringBuilder();
        String header = String.join(delimiter, filter);
        String ls = System.lineSeparator();
        builder.append(header).append(ls);
        String[] strings = new String[positions.length];
        String rsl;
        while (sc.hasNextLine()) {
            String[] stringsLine = sc.nextLine().split(delimiter);
            for (int i = 0; i < positions.length; i++) {
                strings[i] = stringsLine[positions[i]];
            }
            rsl = String.join(delimiter, strings);
            builder.append(rsl).append(ls);
        }
        return builder.toString();
    }

    private void showResult(String out, String result) {
        if (out.equals(STDOUT)) {
            System.out.println(result);
            return;
        }
        try (PrintWriter pw = new PrintWriter(
                                    new BufferedWriter(new FileWriter(out)))) {
            pw.print(result);
            System.out.println("Result has been written to target file >>> " + out);
        } catch (IOException e) {
            System.out.println("Incorrect value in parameter -out= !"
                                + " Get correct path to write result in file");
            e.printStackTrace();
        }
    }

    public ArgsName validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar target/csvReader.jar -ptf=path to source file"
                    + " -del=data delimiter -out='stdout' or path to target file"
                    + " -fil=filter");
        }
        ArgsName names = ArgsName.of(args);
        String out = names.get(OUT);
        if (!out.equals(STDOUT)
                && !(out.contains("/") || out.contains("\\"))) {
            throw new IllegalArgumentException(
                            "Incorrect value in parameter -out= !"
                            + " Get -out='stdout' for output in console"
                            + " or use 'path' to write result in file!"
            );
        }
        return names;
    }

    public static void main(String[] args) throws Exception {
        CsvReader csvReader = new CsvReader();
        ArgsName jvm = csvReader.validate(args);
        csvReader.handle(jvm);
    }
}
