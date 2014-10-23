
package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.brewing.entity.ChangeTemperature;
import java.time.Duration;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperature;
import nl.ordina.beer.entity.Kettle;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ChangeTemperatureTest {

    private static final Duration NO_DELAY = Duration.ZERO;
    
    private ChangeTemperature sut = new ChangeTemperature(defaultTemperature(), NO_DELAY);
    
    @Test
    public void should_change_temperature_of_kettle() {
        Kettle kettle = new Kettle();
        assertThat(kettle.getTemperature(), is(not(defaultTemperature())));
        sut.executeFor(kettle);
        assertThat(kettle.getTemperature(), is(defaultTemperature()));
    }
}
