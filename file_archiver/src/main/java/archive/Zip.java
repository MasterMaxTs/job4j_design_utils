package archive;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Главный класс приложения
 */
public class Zip {

    public static ArgsName validate(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar target/file_archiver.jar "
                    + "-d=DIRECTORY_FOR_ZIP -e=EXCLUDE_FILE_EXTENSION -o=TARGET_ZIP_FILE");
        }
        ArgsName names = ArgsName.of(args);
        Path sourceDirectory = Paths.get(names.get("d"));
        String fileExtension = names.get("e");
        String targetFile = names.get("o");
        if (!sourceDirectory.toFile().exists()) {
            throw new FileNotFoundException(
                    String.format(
                            "Error in <-d> key: source directory %s is not exist!",
                            sourceDirectory.toAbsolutePath())
            );
        }
        if (!fileExtension.startsWith(".")) {
            throw new IllegalArgumentException(
                    "Error in <-e> key: the file extension must begin with a dot!"
            );
        }
        if (!targetFile.endsWith("zip")) {
            throw new IllegalArgumentException(
                    "Error in <-o> key: the output archive file must have a zip extension!"
            );
        }
        return names;
    }

    public void packFiles(List<File> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File f
                    : sources) {
                zip.putNextEntry(new ZipEntry(f.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(f))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = validate(args);
        Path root = Paths.get(jvm.get("d"));
        Path target = Paths.get(jvm.get("o"));
        List<File> filesToPackage = Search.search(
                root, f -> !f.toFile().getName().endsWith(jvm.get("e"))
                ).stream()
                .map(Path::toFile).collect(Collectors.toList());
        filesToPackage.forEach(System.out::println);
        new Zip().packFiles(filesToPackage, target.toFile());
    }
}
