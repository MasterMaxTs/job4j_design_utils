package ru.job4j.ood.lsp.park;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CarParkingTest {
    private CarParking carParking;

    @Before
    public void whenSetUp() {
        int carPlaces = 4;
        int truckPlaces = 2;
        Vehicle firstCar = new Car();
        Vehicle secondCar = new Car();
        Vehicle firstTruck = new Truck(2);
        Vehicle secondTruck = new Truck(3);
        Vehicle thirdTruck = new Truck(2);
        carParking = new CarParking(carPlaces, truckPlaces);
        carParking.setParkedPlaces(
                new HashMap<>(
                        Map.of(
                                0, firstCar,
                                1, secondCar,
                                2, firstTruck,
                                3, firstTruck,
                                4, secondTruck,
                                5, thirdTruck
                        )
                )
        );
    }

    @Test
    public void whenParkCarAndThereAreFreePlacesInCarsParkingLotThanTrue() {
        carParking.pulledOut(1);
        assertTrue(carParking.park(new Car(), 1));
    }

    @Test
    public void whenParkTruckAndThereAreFreePlacesInTrucksParkingLotThanTrue() {
        carParking.pulledOut(5);
        assertTrue(carParking.park(new Truck(2), 5));
    }

    @Test
    public void whenParkCarAndTruckAndThereAreNotFreePlacesInCarsAndTrucksParkingLot() {
        assertFalse(carParking.park(new Car(), 3));
        assertFalse(carParking.park(new Truck(3), 4));
    }

    @Test
    public void whenParkTruckWithCorrectSizeOnFreeSerialPlacesInCarsParkingLotThanTrue() {
        carParking.pulledOut(2);
        assertTrue(carParking.park(new Truck(2), 2));
    }

    @Test
    public void whenParkTruckWithBigSizeOnFreeSerialPlacesInCarsParkingLotThanFalse() {
        carParking.pulledOut(0);
        carParking.pulledOut(1);
        assertFalse(carParking.park(new Truck(3), 0));
    }

    @Test
    public void whenParkTruckOnFreeNotSerialPlacesInCarsParkingLotThanFalse() {
        carParking.pulledOut(0);
        carParking.pulledOut(2);
        assertTrue(carParking.park(new Truck(3), 0));
    }
}
