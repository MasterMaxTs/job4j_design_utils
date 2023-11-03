package searcher;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Главный класс приложения
 */
public class FileSearcher {

    private static final String MASK = "mask";
    private static final String NAME = "name";
    private static final String REGEX = "regex";
    private static final String TXT_FILE_EXTENSION = "txt";
    private static final String DOC_FILE_EXTENSION = "doc";
    private static final String RTF_FILE_EXTENSION = "rtf";
    private static final int POS = 0;

    public void searchFiles(Path root, String condition, String typeSearch,
                            Path out) throws IOException {
        switch (typeSearch) {
            case MASK:
                writeResult(
                        Search.search(
                                root,
                                p -> {
                                    String regex = condition.replaceAll(
                                            "\\.", "[.]"
                                    ).replaceAll(
                                            "\\*", ".*"

                                    ).replaceAll(
                                            "\\?", "."
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
                                    return p.toFile().getName().split("\\.")[POS].equals(condition);
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
                            + " Usage java -jar target/file_searcher.jar "
                            + "-d=directory to start searching "
                            + "-c=mask, file name, or regular expression "
                            + "-t=search type. Value of [mask,name,regex]"
                            + "-o=path to the file with found results. "
                            + "File extension values [txt, doc, rtf]"
            );
        }
        ArgsName names = ArgsName.of(args);
        String rootDirectory = names.get("d");
        String searchType = names.get("t");
        String targetFileExtension = names.get("o").split("\\.")[POS + 1];
        File file = new File(rootDirectory);
        if (!file.isDirectory() || !file.exists()) {
            throw new IllegalArgumentException(
                    "Directory not found!" + "Inter correct value in key <-d>");
        }
        List<String> listExpectedTypes = List.of(MASK, NAME, REGEX);
        if (!listExpectedTypes.contains(searchType)) {
            throw new IllegalArgumentException(
                    "Incorrect value of argument -t!\n"
                            + "Enter one of the values [mask, name, regex] in"
                            + " key <-t>");
        }
        List<String> listExpectedExtensions =
                List.of(TXT_FILE_EXTENSION, DOC_FILE_EXTENSION, RTF_FILE_EXTENSION);
        if (!listExpectedExtensions.contains(targetFileExtension)) {
            throw new IllegalArgumentException(
                    "Incorrect value of argument -o!\n"
                            + "Enter one of the target file extension values "
                            + "[txt, doc, rtf]");
        }
        return names;
    }

    public static void main(String[] args) throws IOException {
        FileSearcher searcher = new FileSearcher();
        ArgsName jvm = searcher.validate(args);
        Path directory = Paths.get(jvm.get("d"));
        String condition = jvm.get("c");
        String typeSearch = jvm.get("t");
        Path out = Paths.get(jvm.get("o"));
        searcher.searchFiles(directory, condition, typeSearch, out);
    }
}
