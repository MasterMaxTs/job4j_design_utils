package ru.job4j.io.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG =
            LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte by = 35;
        short sh = 20000;
        int it = 20212021;
        float fl = 9.1f;
        double pi = 3.14159d;
        long bil = 1_000_000_000L;
        boolean cond = true;
        char lit = 'a';
        LOG.debug("It's byte - {}, "
                    + "it's short - {}, "
                    + "it's integer - {} ,"
                    + "it's float - {} ,"
                    + "it's double - {} ,"
                    + "it's long - {} ,"
                    + "it's boolean - {} ,"
                    + "it's char - {} ", by, sh, it, fl, pi, bil, cond, lit
        );
    }
}