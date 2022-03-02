package ru.job4j.ood.isp.draw;

public class Rectangle implements Shape {

    /*
     * Сущность класса Rectangle вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public void drawCircle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRectangle() {
        System.out.println("Draw rectangle");
    }


    /*
     * Сущность класса Rectangle вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public void drawSquare() {
        throw new UnsupportedOperationException();
    }
}
