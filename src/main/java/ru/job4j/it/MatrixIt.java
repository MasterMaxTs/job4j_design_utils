package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс демонстрирует создание итератора для двумерного массива int[][]
 * @version 2.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Реализация метода hasNext() интерфейса Iterator<Integer>.
     * В методе проверяется наличие пустых вложенных массивов
     * @return возвращает условие, подтверждающее наличие текущего элемента
     */
    @Override
    public boolean hasNext() {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].length == 0) {
                count++;
            }
        }
        return count != data.length && row < data.length;
    }

    /**
     * Реализация метода next() интерфейса Iterator<Integer>
     * @return возвращает текущий элемент двумерного массива.
     * Если следующий элемент null, то такой элемент пропускается и
     * курсор переводится на следующий not null элемент.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column > data[row].length - 1) {
            column = 0;
            row++;
            while (data[row].length == 0) {
                row++;
            }
        }
        return data[row][column++];
    }
}
