
package nl.ordina.beer.brewing.entity;

import java.time.Duration;
import javax.json.Json;
import javax.websocket.EncodeException;
import nl.ordina.beer.brewing.entity.ChangeTemperature.Encoder;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.defaultKettle;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperature;
import static nl.ordina.beer.entity.EntityBuilder.defaultTemperatureIncrement;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTemperatureTest {

    private static final Duration NO_DELAY = Duration.ZERO;
    
    private ChangeTemperature sut = new ChangeTemperature(defaultTemperature(), NO_DELAY);
    
    @Test
    public void should_change_temperature_of_kettle_with_small_increment() {
        Kettle kettle = new Kettle();
        Temperature startingTemperature = kettle.getTemperature();
        assertThat(startingTemperature, is(not(defaultTemperature())));
        sut.executeFor(kettle);
        assertThat(kettle.getTemperature(), is(startingTemperature.plus(defaultTemperatureIncrement())));
    }
    
    @Test
    public void test_json_marshalling() throws EncodeException {
        Kettle kettle = defaultKettle();
        sut.executeFor(kettle);
        final Temperature temperature = defaultTemperature();
        String expected = Json.createObjectBuilder()
                .add("event", "temperature changed")
                .add("temperature",
                        Json.createObjectBuilder()
                        .add("scale", kettle.getTemperature().getUnit().name())
                        .add("value", kettle.getTemperature().getValue())
                        .build())
                .build().toString();
        assertThat(new Encoder().encode(sut), is(expected));
    }
}
