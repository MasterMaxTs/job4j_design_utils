package ru.job4j.ood.ocp.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFileRecorderReader {
    private final String url;

    public TextFileRecorderReader(String url) {
        this.url = url;
    }

    /*
    * Нарушен принцип OCP - параметр метода не является абстракцией, а
    * представляет реализацию интерфейса List<>
    */
    public void record(ArrayList<String> data) {
        String text = String.join(" ", data);
        try {
            Files.writeString(Path.of(url), text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Нарушен принцип OCP - тип возвращаемого значения метода не является
     * абстракцией, а представляет реализацию интерфейса List<>
     */
    public ArrayList<String> read(String url) {
        ArrayList<String> rsl = new ArrayList<>();
        try {
            String text = Files.readString(Path.of(url));
            rsl.addAll(Arrays.asList(text.split(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
             

