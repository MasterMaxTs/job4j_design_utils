package finder;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Модель данных свойства файла
 */
public class FileProperty {

    private final Long size;
    private final String name;
    private Path path;

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public FileProperty(Long size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return Objects.equals(size, that.size) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return "FileProperty {"
                + "size=" + size
                + ", name='" + name + "'"
                + ", path='" + path.toAbsolutePath() + "'"
                + "}";
    }
}
