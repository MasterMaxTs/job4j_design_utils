package ru.job4j.ood.isp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JsonReport implements ReportGenerator {

    /*
     * Сущность класса JsonReport вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public String generate(List<Integer> data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String generateJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
