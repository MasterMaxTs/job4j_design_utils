package ru.job4j.ood.isp.draw;

public class Circle implements Shape {

    @Override
    public void drawCircle() {
        System.out.println("Draw circle");
    }

    /*
     * Сущность класса Circle вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public void drawRectangle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawSquare() {
        throw new UnsupportedOperationException();
    }
}
