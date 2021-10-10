package ru.job4j.nio.duplicates;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.nio.duplicates.DuplicatesFinder.DuplicatesVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DuplicatesFinderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenDuplicatesAreExist() throws IOException {
        Path tempDirPath = folder.newFolder("folder").toPath();
        Path filePath1 = tempDirPath.resolve("file1.txt");
        Path filePath2 = tempDirPath.resolve("file2.txt");
        Files.createFile(filePath1);
        Files.createFile(filePath2);
        Path nestedDirPath1 = tempDirPath.resolve("nfolder1");
        Path filePath3 = nestedDirPath1.resolve("file3.txt");
        Path filePath4 = nestedDirPath1.resolve("file2.txt");
        Files.createDirectory(nestedDirPath1);
        Files.createFile(filePath3);
        Files.createFile(filePath4);
        Path nestedDirPath2 = nestedDirPath1.resolve("nfolder2");
        Path filePath5 = nestedDirPath2.resolve("file4.txt");
        Path filePath6 = nestedDirPath2.resolve("file1.txt");
        Files.createDirectory(nestedDirPath2);
        Files.createFile(filePath5);
        Files.createFile(filePath6);
        List<Path> filesPath = List.of(
                filePath1, filePath2, filePath3, filePath4, filePath5, filePath6
        );
        for (Path fp
                : filesPath) {
            Files.writeString(fp, "Hello");
        }
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(tempDirPath, duplicatesVisitor);
        List<FileProperty> rsl = duplicatesVisitor.getDuplicateFiles();
        List<FileProperty> expected = List.of(
                new FileProperty(5L, "file2.txt"),
                new FileProperty(5L, "file1.txt")
        );
        assertThat(rsl, is(expected));
    }
}