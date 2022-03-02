package ru.job4j.ood.isp.report;

import java.util.List;
import java.util.stream.Collectors;

public class StringReport implements ReportGenerator {

    @Override
    public String generate(List<Integer> data) {
        return data
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /*
     * Сущность класса StringReport вынуждена реализовывать
     * неподдерживаемую функциональность, что является
     * нарушением принципа ISP
     */
    @Override
    public String generateJSON(Object obj) {
        throw new UnsupportedOperationException();
    }
}
