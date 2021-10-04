package ru.job4j.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * Данный класс позволяет из потока данных вычленить только числа.
 */
public class ScannerEx1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                                 "1 2 3",
                                         "4 5 6",
                                         "7 8 9"
            );
        Scanner scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}
