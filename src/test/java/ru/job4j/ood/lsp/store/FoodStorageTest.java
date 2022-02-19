package ru.job4j.ood.lsp.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FoodStorageTest {

    private static Calendar current;
    private List<Food> products;
    private Store store;
    private Strategy strategy;
    private ControlQuality controlQuality;

    @Before
    public void whenSetUp() {
        current = Calendar.getInstance();
        products = List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 19),
                        new GregorianCalendar(2021, Calendar.DECEMBER, 30),
                        80.5,
                        15
                ),
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 14),
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 10),
                        45.0,
                        5
                ),
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(2022, Calendar.NOVEMBER, 28),
                        new GregorianCalendar(2022, Calendar.JANUARY, 3),
                        240.7,
                        10
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 20),
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 17),
                        100,
                        20
                ),
                new DairyProducts(
                    "Yogurt",
                    new GregorianCalendar(2022, Calendar.MARCH, 1),
                    new GregorianCalendar(2022, Calendar.FEBRUARY, 15),
                    50,
                    10
        ));
    }

    @Test
    public void whenDistributeProductsToShop() {
        store = new Shop();
        strategy = new ShopControlQuality(store);
        controlQuality = new ControlQuality(strategy);
        controlQuality.execute(products, current);
        List<Food> expected = new ArrayList<>(List.of(
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 20),
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 17),
                        80,
                        20
                ),
                new DairyProducts(
                        "Yogurt",
                        new GregorianCalendar(2022, Calendar.MARCH, 1),
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 15),
                        50,
                        10
                ))
        );
        List<Food> rsl = store.get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToWarehouse() {
        store = new Warehouse();
        strategy = new WhControlQuality(store);
        controlQuality = new ControlQuality(strategy);
        controlQuality.execute(products, current);
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(2022, Calendar.NOVEMBER, 28),
                        new GregorianCalendar(2022, Calendar.JANUARY, 3),
                        240.7,
                        10
                ))
        );
        List<Food> rsl = store.get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToTrash() {
        store = new Trash();
        strategy = new TrashControl(store);
        controlQuality = new ControlQuality(strategy);
        controlQuality.execute(products, current);
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 19),
                        new GregorianCalendar(2021, Calendar.DECEMBER, 30),
                        80.5,
                        15
                ),
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 14),
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 10),
                        45.0,
                        5
                ))
        );
        List<Food> rsl = store.get();
        assertEquals(expected, rsl);
    }
}