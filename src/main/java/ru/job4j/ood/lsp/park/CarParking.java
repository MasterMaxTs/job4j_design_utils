package ru.job4j.ood.lsp.park;

import java.util.*;

public class CarParking extends Parking {

    private final int carPlaces;
    private final int truckPlaces;
    private Map<Integer, Vehicle> parkedPlaces = new HashMap<>();
    private static final int POS = 0;

    public CarParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    List<Integer> getFreeParkedPlaces(int start, int end) {
        List<Integer> fpp = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (parkedPlaces.get(i) == null) {
                fpp.add(i);
            }
        }
        return fpp;
    }

    @Override
    public boolean park(Vehicle vehicle, int cell) {
        int size = vehicle.getSize();
        int validCell = accept(size, cell);
        if (validCell != -1) {
            if (size > Car.getDefaultSize()) {
                for (int i = 0; i < size; i++) {
                    parkedPlaces.put(validCell + i, vehicle);
                }
            } else {
                parkedPlaces.put(validCell, vehicle);
            }
            return true;
        }
        System.out.println("Parking No free spaces");
        return false;
    }

    @Override
    public int accept(int size, int cell) {
        List<Integer> freeCarPlaces = getFreeParkedPlaces(0, carPlaces);
        List<Integer> freeTruckPlaces = getFreeParkedPlaces(carPlaces,
                carPlaces + truckPlaces);
        if (size != Car.getDefaultSize()) {
            int freeCell = validateCell(freeTruckPlaces, cell);
            if (freeCell == -1) {
                freeCell = validateCell(freeCarPlaces, cell);
                if (freeCell != -1 && freeCarPlaces.size() >= size) {
                    int count = 1;
                    for (int i = 0; i < (freeCarPlaces.size() - 1) - size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (freeCarPlaces.get(i + j + 1) == 1 + freeCarPlaces.get(i + j)) {
                                count += 1;
                                if (count == size) {
                                    return i;
                                }
                            }
                        }
                    }
                } else {
                    freeCell = -1;
                }
            }
            return freeCell;
        }
        return validateCell(freeCarPlaces, cell);
    }

    private int validateCell(List<Integer> freePlaces, int cell) {
        if (!freePlaces.isEmpty()) {
            return freePlaces.contains(cell) ? cell : freePlaces.get(POS);
        }
        return -1;
    }

    @Override
    public Vehicle pulledOut(int cell) {
        Vehicle vehicle = parkedPlaces.get(cell);
        int vehicleSize = vehicle.getSize();
        if (vehicleSize > 1 && cell < carPlaces) {
            for (int j = cell; j < cell + vehicleSize; j++) {
                parkedPlaces.remove(j);
            }
        } else if (vehicleSize > 1 && cell > carPlaces) {
            parkedPlaces.remove(cell);
        }
        return parkedPlaces.remove(cell);
    }

    public Map<Integer, Vehicle> getParkedVehicle() {
        return new HashMap<>(parkedPlaces);
    }

    void setParkedPlaces(Map<Integer, Vehicle> parkedPlaces) {
        this.parkedPlaces = new HashMap<>(parkedPlaces);
    }
}
