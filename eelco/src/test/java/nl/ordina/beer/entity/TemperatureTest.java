package nl.ordina.beer.entity;

import org.junit.Test;

import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static org.junit.Assert.assertTrue;

public class TemperatureTest {

    @Test
    public void test_lower_than() {
        assertTrue(new Temperature(20, CELSIUS).isLowerThan(new Temperature(25, CELSIUS)));
    }
}
