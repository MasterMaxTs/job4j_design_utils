package ru.job4j.ood.isp.draw;

public class Square implements Shape {

    /*
     * Сущность класса Square вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public void drawCircle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRectangle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawSquare() {
        System.out.println("Draw square");
    }
}
