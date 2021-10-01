package ru.job4j.io.archive;

import ru.job4j.io.namedargs.ArgsName;
import ru.job4j.nio.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
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

    public static ArgsName validate(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar pack.jar -d=DIRECTORY_FOR_ZIP -e=EXCLUDE FILES -o=TARGET_PATH_ZIP");
        }
        ArgsName names = ArgsName.of(args);
        Path sourceDirectory = Paths.get(names.get("d"));
        if (!sourceDirectory.toFile().exists()) {
            throw new FileNotFoundException(
                    String.format("Source directory %s is not exist!", sourceDirectory.toAbsolutePath()));
        }
        return names;
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = validate(args);
        Path root = Paths.get(jvm.get("d"));
        Path target = Paths.get(jvm.get("o"));
        List<File> filesToPackage = Search.search(root, f -> !f.toFile().getName().endsWith(jvm.get("e"))
        ).stream()
                .map(Path::toFile).collect(Collectors.toList());
        filesToPackage.forEach(System.out::println);
        new Zip().packFiles(filesToPackage, target.toFile());
    }
}




