package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public interface Strategy {

    void redistribute(List<Food> products, Calendar current);

    default double delta(Food product, Calendar currentDate) {
        long expireDateInMillis = product.getExpiryDate().getTime().getTime();
        long createDateInMillis = product.getCreateDate().getTime().getTime();
        long currentDateInMillis = currentDate.getTime().getTime();
        long delta1 = expireDateInMillis - currentDateInMillis;
        long delta2 = expireDateInMillis - createDateInMillis;
        return (double) delta1 / delta2;
    }
}
