package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TriggerTest {

    @Test
    public void test() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }
}
