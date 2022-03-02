package ru.job4j.ood.isp.pizza;

public interface Pizza {

    /*
     * Интерфейс Pizza поддерживает большую функциональность,
     * тем самым представляет из себя "жирный интерфейс"
     */
    void bake();

    void addSauce();

    void addMeatIngredients();

    void addVegetableIngredients();
}
