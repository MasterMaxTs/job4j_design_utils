package ru.job4j.ood.lsp.park;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarParking extends Parking {

    private final int carPlaces;
    private final int truckPlaces;
    private Map<Integer, Vehicle> parkedPlaces = new HashMap<>();

    public CarParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean rsl = false;
        if (accept(vehicle.getSize())) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean accept(int size) {
        return false;
    }

    @Override
    public Vehicle pulledOut(int i) {
        return null;
    }

    public List<Vehicle> getParkedVehicle() {
        return new ArrayList<>(parkedPlaces.values());
    }

    public void setParkedPlaces(Map<Integer, Vehicle> parkedPlaces) {
        this.parkedPlaces = parkedPlaces;
    }
}
