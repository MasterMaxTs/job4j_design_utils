package ru.job4j.ood.srp.showroom;

import java.util.Calendar;

public interface ManageShowroom {

    /*
     * Нарушен принцип SRP - ISP: из объявленных сигнатур методов
     * в реализующих интерфейс классах есть не используемые
     */
    void purchase(Car car, int count);
    int sales(Calendar date);
    int getRemainder(Car car, Calendar date);
    void showHighestDemandModels();
}
