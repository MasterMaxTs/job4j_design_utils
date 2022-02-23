package ru.job4j.ood.lsp.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FoodStorageTest {

    private List<Food> products;
    private List<Store> stores;

    @Before
    public void whenSetUp() {
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
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 25),
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
        stores = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
    }

    @Test
    public void whenDistributeProductsToShop() {
        List<Food> expected = new ArrayList<>(List.of(
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 25),
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
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(1).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToWarehouse() {
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(2022, Calendar.NOVEMBER, 28),
                        new GregorianCalendar(2022, Calendar.JANUARY, 3),
                        240.7,
                        10
                ))
        );
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(0).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToTrash() {
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
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(2).get();
        assertEquals(expected, rsl);
    }
}