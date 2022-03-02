package ru.job4j.ood.isp.report;

import java.util.List;

public interface ReportGenerator {

    /*
     * Интерфейс ReportGenerator поддерживает большую функциональность,
     * тем самым представляет из себя "жирный интерфейс"
     */
    String generate(List<Integer> data);

    String generateJSON(Object obj);
}
