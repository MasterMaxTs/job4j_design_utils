package ru.job4j.ood.lsp.park;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CarParkingTest {
    private CarParking carParking;

    @Ignore
    @Before
    public void whenSetUp() {
        int carPlaces = 4;
        int truckPlaces = 2;
        carParking = new CarParking(carPlaces, truckPlaces);
        carParking.setParkedPlaces(
                Map.of(
                        1, new Car(1),
                        2, new Car(1),
                        3, new Car(1),
                        4, new Car(1),
                        5, new Truck(3),
                        6, new Truck(2)
                )
        );
    }

    @Ignore
    @Test
    public void whenParkCarAndThereAreFreePlacesInCarsParkingLotThanTrue() {
        carParking.pulledOut(3);
        assertTrue(carParking.park(new Car(1)));
    }

    @Ignore
    @Test
    public void whenParkTruckAndThereAreFreePlacesInTrucksParkingLotThanTrue() {
        carParking.pulledOut(5);
        assertTrue(carParking.park(new Truck(2)));
    }

    @Ignore
    @Test
    public void whenParkCarAndTruckAndThereAreNotFreePlacesInCarsAndTrucksParkingLot() {
        assertFalse(carParking.park(new Car(1)));
        assertFalse(carParking.park(new Truck(3)));
    }

    @Ignore
    @Test
    public void whenParkTruckWithCorrectSizeOnFreeSerialPlacesInCarsParkingLotThanTrue() {
        carParking.pulledOut(2);
        carParking.pulledOut(3);
        assertTrue(carParking.park(new Truck(2)));
    }

    @Ignore
    @Test
    public void whenParkTruckWithBigSizeOnFreeSerialPlacesInCarsParkingLotThanFalse() {
        carParking.pulledOut(2);
        carParking.pulledOut(3);
        assertTrue(carParking.park(new Truck(3)));
    }

    @Ignore
    @Test
    public void whenParkTruckOnFreeNotSerialPlacesInCarsParkingLotThanFalse() {
        carParking.pulledOut(1);
        carParking.pulledOut(3);
        carParking.pulledOut(4);
        assertTrue(carParking.park(new Truck(3)));
    }
}
