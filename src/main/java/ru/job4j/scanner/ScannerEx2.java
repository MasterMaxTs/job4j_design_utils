package ru.job4j.scanner;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Класс позволяет из потока данных вычленить почты, которые разделы между собой “, ”
 */
public class ScannerEx2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes())).useDelimiter(",");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
