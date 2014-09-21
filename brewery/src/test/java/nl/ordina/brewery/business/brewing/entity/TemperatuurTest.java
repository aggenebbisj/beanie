package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Temperatuur.Schaal.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TemperatuurTest {
    
    @Test
    public void test_temperatuur_verhogen_met_zelfde_schaal() {
        Temperatuur sut = new Temperatuur(10, CELSIUS);
        
        Temperatuur result = sut.plus(new Temperatuur(20, CELSIUS));
                
        assertThat(result, is(new Temperatuur(30, CELSIUS)));
    }
    
}
