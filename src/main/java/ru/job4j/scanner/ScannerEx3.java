package ru.job4j.scanner;

import java.io.*;
import java.util.Scanner;

/**
 * Класс позволяет прочитать числа в шестнадцатеричном виде и вывести в десятичном
 */
public class ScannerEx3 {
    public static void main(String[] args) throws IOException {
        var data = "A 1B FF 110";
        var tempFile = File.createTempFile("data", null);
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(tempFile)
        )) {
            bos.write(data.getBytes());
        }
        try (Scanner scanner = new Scanner(tempFile).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
        tempFile.deleteOnExit();
    }
}
