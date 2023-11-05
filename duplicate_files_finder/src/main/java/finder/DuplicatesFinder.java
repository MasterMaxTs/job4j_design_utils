package finder;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Главный класс приложения
 */
public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesFinder finder = new DuplicatesFinder();
        ArgsName jvm = finder.validate(args);
        Path sourceDir = Paths.get(jvm.get("d"));
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(sourceDir, duplicatesVisitor);
        List<FileProperty> rsl = duplicatesVisitor.getDuplicateFiles();
        finder.showResults(rsl, sourceDir);
    }

    private void showResults(List<FileProperty> duplicateFiles, Path directory) {
        System.out.println("\n*************\n--Searching " + duplicateFiles.size()
                + " duplicate file(s) in directory: " + directory.toAbsolutePath());
        duplicateFiles.forEach(System.out::println);
    }

    public ArgsName validate(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Incorrect entry of arguments!\n"
                            + " Usage java -jar target/duplicate_files_finder.jar "
                            + "-d=directory to start searching "
            );
        }
        ArgsName names = ArgsName.of(args);
        String sourceDir = names.get("d");
        File file = new File(sourceDir);
        if (!file.isDirectory() || !file.exists()) {
            throw new IllegalArgumentException(
                    "Directory not found! " + "Inter correct value in key <-d>");
        }
        return names;
    }

    /**
     * Вспомогательный класс, реализующий логику поиска, в виде хранилища
     * найденных файлов
     */
    static class DuplicatesVisitor extends SimpleFileVisitor<Path> {

        private final Set<FileProperty> uniqueFiles = new HashSet<>();
        private final List<FileProperty> duplicateFiles = new ArrayList<>();

        public List<FileProperty> getDuplicateFiles() {
            return duplicateFiles;
        }

        @Override
        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs)
                                                                        throws IOException {
            FileProperty fileProperty = new FileProperty(
                    filePath.toFile().length(), filePath.toFile().getName()
            );
            fileProperty.setPath(filePath);
            if (!uniqueFiles.add(fileProperty)) {
                duplicateFiles.add(fileProperty);
            }
            System.out.println("Scanning:\n" + filePath.toAbsolutePath());
            return super.visitFile(filePath, attrs);
        }
    }
}
