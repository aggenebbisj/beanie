
package nl.ordina.beer.manualbrewing.control;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Arrays;
import java.util.Collections;
import javax.enterprise.event.Event;
import nl.ordina.beer.control.IngredientAddedEvent;
import nl.ordina.beer.control.KitchenTimer;
import nl.ordina.beer.control.TemperatureController;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import nl.ordina.beer.entity.Volume;
import static nl.ordina.beer.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

    @InjectMocks
    private Brewer sut;
    
    private final Kettle kettle = new Kettle();
    
    @Mock
    private Event<IngredientAddedEvent> ingredientAddedEvent;
    
    @Mock 
    private TemperatureController thermostat;
    
    @Mock
    private KitchenTimer kitchenTimer;
    
    @Before
    public void setup() {
        sut.kettle = kettle;
    }
    
    @Test
    public void adding_ingredient_should_add_ingredient_to_kettle() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.addIngredient(ingredient);
        assertThat(sut.getIngredients(), is(Arrays.asList(ingredient)));
    }
    
    @Test
    public void adding_ingredient_should_fire_event() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.addIngredient(ingredient);
        Mockito.verify(ingredientAddedEvent).fire(new IngredientAddedEvent(ingredient, kettle));
    }
    
    @Test
    public void should_be_able_to_retrieve_added_ingredients() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.addIngredient(ingredient);
        assertThat(sut.getIngredients(), is(Arrays.asList(ingredient)));
    }
  
    @Test(expected = IllegalArgumentException.class)
    public void adding_too_much_volume_to_kettle_should_throw_exception() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.kettle = new Kettle(new Volume(100, LITER));
        sut.addIngredient(ingredient);
    }
    
    @Test
    public void emptying_kettle_should_throw_away_ingredients() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.addIngredient(ingredient);
        sut.emptyKettle();
        assertThat(sut.getIngredients(), is(Collections.emptyList()));
    }
    
    @Test
    public void should_change_the_temperature_via_the_thermostat() {
        Temperature goal = new Temperature(50, CELSIUS);
        sut.changeTemperatureTo(goal);
        Mockito.verify(thermostat).changeTemperature(goal, kettle);
    }
    
    @Test
    public void should_be_able_to_lock_the_kettle() {
        sut.lockKettle(Duration.of(30, MINUTES));
        assertTrue(sut.kettle.isLocked());
    }
    
    @Test
    public void locking_the_kettle_should_be_done_via_kitchentimer() {
        sut.lockKettle(Duration.of(30, MINUTES));
        Mockito.verify(kitchenTimer).setFor(Duration.of(30, MINUTES), kettle);
    }
    
    @Test(expected = IllegalStateException.class)
    public void should_not_be_able_to_add_ingredient_if_kettle_is_locked() {
        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
        sut.kettle.lock();
        sut.addIngredient(ingredient);
    }
}
