package ru.job4j.ood.isp.pizza;

public class MeatPizza implements Pizza {

    @Override
    public void bake() {
        System.out.println("Baking...");
    }

    @Override
    public void addSauce() {
        System.out.print("Add sauce...");
    }

    @Override
    public void addMeatIngredients() {
        System.out.println("Add meat ingredients");
    }

    @Override
    public void addVegetableIngredients() {
        System.out.println("Add vegetable ingredients");
    }
}
