package ru.job4j.search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearcher {
    private static final String MASK = "mask";
    private static final String NAME = "name";
    private static final String REGEX = "regex";
    private static final int POSITION = 0;

    public void searchFiles(Path root, String condition, String typeSearch,
                            Path out) throws IOException {
        switch (typeSearch) {
            case MASK:
                writeResult(
                        Search.search(
                                root,
                                p -> {
                                    String regex = condition.replaceAll(
                                            "\\*", "[^\":<>|]*"
                                            )
                                            .replaceAll(
                                                    "\\?", "[^\":<>|]"
                                            ).replaceAll(
                                                    "\\.", "\\\\."
                                            );
                                    Pattern pattern = Pattern.compile(regex);
                                    Matcher matcher =
                                            pattern.matcher(p.toFile().getName());
                                    return matcher.matches();
                                }
                        ),
                        out
                );
                break;
            case NAME:
                writeResult(
                    Search.search(
                            root,
                            p -> {
                                if (condition.contains(".")) {
                                    return p.toFile().getName().equals(condition);
                                }
                                return p.toFile().getName().split("\\.")[POSITION].equals(condition);
                            }
                    ),
                    out
            );
                break;
            default:
                writeResult(
                        Search.search(
                                root,
                                p -> {
                                    Pattern pattern = Pattern.compile(condition);
                                    Matcher matcher =
                                            pattern.matcher(p.toFile().getName());
                                    return matcher.matches();
                                }
                        ),
                        out
                );
                break;
        }
    }

    public void writeResult(List<Path> files, Path out) throws IOException {
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out.toFile()))
                )) {
            files.forEach(pw::println);
        }
        System.out.println("Find " + files.size() + " file(s). "
                + "Result has been written in file " + out.toAbsolutePath());
    }

    public ArgsName validate(String[] args) {
        final int POS = 1;
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Incorrect entry of arguments!\n"
                            + " Usage java -jar target/find.jar "
                            + "-d=directory to start searching "
                            + "-n=mask, file name, or regular expression "
                            + "-t=search type. Value of [mask,name,regex]"
                            + "-o=path to the file with found results"
            );
        }
        File file = new File(args[0].split("=")[POS]);
        if (!file.isDirectory() || !file.exists()) {
            throw new IllegalArgumentException(
                    "Directory not found!" + "Inter correct value in key <-d>");
        }
        String type = args[2].split("=")[POS];
        List<String> listTypes = List.of(MASK, NAME, REGEX);
        if (!listTypes.contains(type)) {
            throw  new IllegalArgumentException(
                    "Incorrect value of argument -t!\n"
                            + "Enter one of the values [mask, name, regex] in"
                            + " key <-t>");
        }
        return ArgsName.of(args);
    }

    public static void main(String[] args) throws IOException {
        FileSearcher searcher = new FileSearcher();
        ArgsName jvm = searcher.validate(args);
        Path directory = Paths.get(jvm.get("d"));
        String condition = jvm.get("n");
        String typeSearch = jvm.get("t");
        Path out = Paths.get(jvm.get("o"));
        searcher.searchFiles(directory, condition, typeSearch, out);
    }
}
