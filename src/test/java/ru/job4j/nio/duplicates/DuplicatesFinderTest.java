package ru.job4j.nio.duplicates;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.nio.duplicates.DuplicatesFinder.DuplicatesVisitor;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenDuplicatesAreExist() throws IOException {
        Path tempDirPath = folder.newFolder("folder").toPath();
        Path filePath1 = tempDirPath.resolve("file1.txt");
        Path filePath2 = tempDirPath.resolve("file2.txt");
        Path nestedDirPath1 = tempDirPath.resolve("nfolder1");
        Files.createDirectory(nestedDirPath1);
        Path filePath3 = nestedDirPath1.resolve("file3.txt");
        Path filePath4 = nestedDirPath1.resolve("file2.txt");
        Path nestedDirPath2 = nestedDirPath1.resolve("nfolder2");
        Files.createDirectory(nestedDirPath2);
        Path filePath5 = nestedDirPath2.resolve("file4.txt");
        Path filePath6 = nestedDirPath2.resolve("file1.txt");
        List<Path> filesPath = List.of(
                filePath1, filePath2, filePath3, filePath4, filePath5, filePath6
        );
        for (Path fp
                : filesPath) {
            Files.write(fp, "Hello".getBytes());
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