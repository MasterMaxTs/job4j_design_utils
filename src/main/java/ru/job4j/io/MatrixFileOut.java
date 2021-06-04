package ru.job4j.io;

import ru.job4j.array.Matrix;

import java.io.FileOutputStream;
import java.io.IOException;

public class MatrixFileOut {

    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Multiply_table.txt")) {
            fileOutputStream.write(Matrix.showTable(9).getBytes());
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
