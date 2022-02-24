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
    private static final Calendar now = Store.current;

    @Before
    public void whenSetUp() {
        products = List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        ),
                        new GregorianCalendar(
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        80.5,
                        15
                ),
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 3
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        45.0,
                        5
                ),
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 21
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        ),
                        240.7,
                        10
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 1
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) - 5
                        ),
                        100,
                        20
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
                        "Bread",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 3
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        45.0,
                        5
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 1
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) - 5
                        ),
                        80,
                        20
                )
        ));
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(1).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToWarehouse() {
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) + 21
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        ),
                        240.7,
                        10
                )
        ));
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(0).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToTrash() {
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        ),
                        new GregorianCalendar(
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        80.5,
                        15
                )
        ));
        ControlQuality.distribute(products, stores);
        List<Food> rsl = stores.get(2).get();
        assertEquals(expected, rsl);
    }
}