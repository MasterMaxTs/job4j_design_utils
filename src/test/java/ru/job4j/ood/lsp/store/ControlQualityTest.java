package ru.job4j.ood.lsp.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {

    private static final Calendar NOW = Store.current;
    private List<Store> stores;
    private ControlQuality controlQuality;

    @Before
    public void whenSetUp() {
        List<Food> products = List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        80.5,
                        15
                ),
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 3
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        45.0,
                        5
                ),
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 21
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        240.7,
                        10
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 1
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 5
                        ),
                        100,
                        20
                ));
        stores = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        controlQuality = new ControlQuality(products, stores);
    }

    @Test
    public void whenDistributeProductsToShop() {
        List<Food> expected = new ArrayList<>(List.of(
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 3
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        45.0,
                        5
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 1
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 5
                        ),
                        80,
                        20
                )
        ));
        List<Food> rsl = stores.get(1).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToWarehouse() {
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 21
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        240.7,
                        10
                )
        ));
        List<Food> rsl = stores.get(0).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenDistributeProductsToTrash() {
        List<Food> expected = new ArrayList<>(List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        80.5,
                        15
                )
        ));
        List<Food> rsl = stores.get(2).get();
        assertEquals(expected, rsl);
    }

    @Test
    public void whenRestoreProductsToStores() {
        List<Food> expectedFoodsToWarehouse = new ArrayList<>(List.of(
                new DairyProducts(
                        "Cheese",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 21
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        240.7,
                        10
                )
        ));
        List<Food> expectedFoodsToShop = new ArrayList<>(List.of(
                new BakeryProducts(
                        "Bread",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 3
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        45.0,
                        5
                ),
                new BakeryProducts(
                        "Pretzel",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) + 1
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 5
                        ),
                        80,
                        20
                )
        ));
        List<Food> expectedFoodsToTrash = new ArrayList<>(List.of(
                new DairyProducts(
                        "Milk",
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH)
                        ),
                        new GregorianCalendar(
                                NOW.get(Calendar.YEAR),
                                NOW.get(Calendar.MONTH),
                                NOW.get(Calendar.DAY_OF_MONTH) - 1
                        ),
                        80.5,
                        15
                )
        ));
        controlQuality.resort(stores);
        assertThat(stores.get(0).get(), is(expectedFoodsToWarehouse));
        assertThat(stores.get(1).get(), is(expectedFoodsToShop));
        assertThat(stores.get(2).get(), is(expectedFoodsToTrash));
    }
}