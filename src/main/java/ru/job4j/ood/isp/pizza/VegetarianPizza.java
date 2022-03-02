package ru.job4j.ood.isp.pizza;

public class VegetarianPizza implements Pizza {

    @Override
    public void bake() {
        System.out.println("Baking...");
    }

    @Override
    public void addSauce() {
        System.out.print("Add sauce...");
    }

    /*
     * Сущность класса VegetarianPizza вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public void addMeatIngredients() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addVegetableIngredients() {
        System.out.println("Add vegetable ingredients");
    }
}
