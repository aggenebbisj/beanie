package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.temperature.Temperature;
import org.junit.Test;

import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TemperatureTest {
    
    @Test
    public void test_temperatuur_verhogen_met_zelfde_schaal() {
        Temperature sut = new Temperature(10, CELSIUS);
        Temperature result = sut.plus(new Temperature(20, CELSIUS));
        assertThat(result, is(new Temperature(30, CELSIUS)));
    }
    
}
