package ru.job4j.ood.srp.showroom;

import java.util.List;

public class Car {
    private final String model;
    private final double enginePower;
    private final double engineVolume;
    private final String transmission;
    private final List<String> equipment;
    private final long price;

    public Car(String model,
               double enginePower,
               double engineVolume,
               String transmission,
               List<String> equipment,
               long price) {
        this.model = model;
        this.enginePower = enginePower;
        this.engineVolume = engineVolume;
        this.transmission = transmission;
        this.equipment = equipment;
        this.price = price;
    }

    /*
     * Нарушен принцип SRP: метод добаляет классу дополнительную
     * ответственность в дополнение к основной (модель данных)
     *  */
    public void showEngineInfo() {
        System.out.printf("Engine volume: %s;%n engine power: %s;%n",
                engineVolume, enginePower);
    }


    public String getModel() {
        return model;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public String getTransmission() {
        return transmission;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public long getPrice() {
        return price;
    }
}
