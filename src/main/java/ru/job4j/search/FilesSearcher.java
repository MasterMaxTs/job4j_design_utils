package ru.job4j.search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;

public class FilesSearcher {
    private static final String MASK = "mask";
    private static final String NAME = "name";
    private static final String REGEX = "regex";
    private static final String GLOB = "glob:";
    private static final int POSITION = 0;

    public void searchFiles(Path root, String condition, String typeSearch,
                            Path out) throws IOException {
        if (typeSearch.equals(NAME)) {
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
        } else {
        writeResult(
                Search.search(
                        root,
                        p -> {
                            String syntax;
                            if (typeSearch.equals(MASK)) {
                                syntax = GLOB;
                            } else {
                                syntax = REGEX.concat(":");
                            }
                            PathMatcher matcher =
                                    FileSystems.getDefault().getPathMatcher(syntax.concat(condition));
                            return matcher.matches(p);
                        }
                ),
                out);
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
        List<String> types = List.of(MASK, NAME, REGEX);
        ArgsName names = ArgsName.of(args);
        String type = names.get("t");
        if (!types.contains(type)) {
            throw  new IllegalArgumentException(
                    "Incorrect value of argument -t!\n"
                            + "Enter one of the values [mask, name, regex]"
            );
        }
        return ArgsName.of(args);
    }

    public static void main(String[] args) throws IOException {
        FilesSearcher searcher = new FilesSearcher();
        ArgsName jvm = searcher.validate(args);
        Path directory = Paths.get(jvm.get("d"));
        String condition = jvm.get("n");
        String typeSearch = jvm.get("t");
        Path out = Paths.get(jvm.get("o"));
        searcher.searchFiles(directory, condition, typeSearch, out);
    }
}
