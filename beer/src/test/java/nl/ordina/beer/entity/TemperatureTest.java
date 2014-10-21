
package nl.ordina.beer.entity;

import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TemperatureTest {

    @Test
    public void test_lower_than() {
        assertTrue(new Temperature(20, CELSIUS).isLowerThan(new Temperature(25, CELSIUS)));
    }
}
