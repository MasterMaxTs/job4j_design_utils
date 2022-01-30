package ru.job4j.ood.srp.showroom;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CarShowroom implements ManageShowroom {

    /*
     * Нарушен принцип SRP - DIP: класс высшего уровня имеет зависимость
     * от класса нисшего уровня, а не от абстракции (интерфейса).
     */
    private final Map<Models, List<Car>> cars;
    private final String address;
    private final String telephone;

    public CarShowroom(Map<Models, List<Car>> cars, String address, String telephone) {
        this.cars = cars;
        this.address = address;
        this.telephone = telephone;
    }

    @Override
    public void purchase(Car car, int count) {

    }

    @Override
    public int sales(Calendar date) {
        return 0;
    }

    @Override
    public int getRemainder(Car car, Calendar date) {
        return 0;
    }

    @Override
    public void showHighestDemandModels() {

    }
}
