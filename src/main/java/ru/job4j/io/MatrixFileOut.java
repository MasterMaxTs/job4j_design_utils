package ru.job4j.io;

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

    static class Matrix {
        public static String showTable(int size) {
            int[][] table = new int[size][size];
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    table[i][j] = (i + 1) * (j + 1);
                    stringBuilder.append("  ").append(table[i][j]);
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
