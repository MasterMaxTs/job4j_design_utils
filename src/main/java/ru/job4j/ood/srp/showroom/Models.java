package ru.job4j.ood.srp.showroom;

public enum Models {

    K2("Rio"),
    K4("Optima"),
    K3("Ceed");

    private final String model;

    Models(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}


