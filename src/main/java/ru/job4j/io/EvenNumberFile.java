package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    static final String S0 = "чётное число";
    static final String S1 = "нечётное число";

    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int r;
            while ((r = fileInputStream.read()) != -1) {
                stringBuilder.append((char) r);
            }
            String[] text = stringBuilder.toString().split(System.lineSeparator());
            for (String s
                    : text) {
                if (Integer.parseInt(s) % 2 == 0) {
                    System.out.println(s + " - " + S0);
                } else {
                    System.out.println(s + " - " + S1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
