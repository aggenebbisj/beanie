package nl.ordina.beer.brewing.boundary;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import nl.ordina.beer.boundary.DurationXmlAdapter;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import nl.ordina.beer.entity.Volume;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;
import nl.ordina.beer.brewing.control.Brewer;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrewerResourceTest {

    @InjectMocks
    private BrewerResource sut;
    
    @Mock
    private Brewer brewer;
    
    @Mock
    private Logger logger;
    
    @Test
    public void post_should_trigger_brewer_to_add_to_kettle() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.add(ingredient);
        Mockito.verify(brewer).addIngredient(ingredient);
    }
    
    @Test
    public void get_should_retrieve_list_of_ingredients() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        Mockito.when(brewer.getIngredients()).thenReturn(Arrays.asList(ingredient));
        List<Ingredient> result = sut.get();
        assertThat(result, is(Arrays.asList(ingredient)));
    }
    
    @Test
    public void delete_should_trigger_brewer_to_empty_kettle() {
        sut.delete();
        Mockito.verify(brewer).emptyKettle();
    }
    
    @Test
    public void put_should_trigger_brewer_to_change_temperature() {
        Temperature goal = new Temperature(50, CELSIUS);
        sut.putTemperature(goal);
        Mockito.verify(brewer).changeTemperatureTo(goal);
    }
    
    @Test
    public void put_should_trigger_brewer_to_lock_kettle() {
        sut.durationAdapter = new DurationXmlAdapter();
        sut.lock("{ \"duration\":\"PT30M\" }");
        Mockito.verify(brewer).lockKettle(Duration.of(30, MINUTES));
    }
}
