
package nl.ordina.brewery.manual.boundary;

import java.time.Duration;
import javax.enterprise.event.Event;
import nl.ordina.brewery.entity.MonitoredEvent;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.temperature.Temperature;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.LITER;
import nl.ordina.brewery.entity.ingredient.Ingredients;
import nl.ordina.brewery.entity.ingredient.Water;

@RunWith(MockitoJUnitRunner.class)
public class IngredientResourceTest {

    @Mock
    private Event<MonitoredEvent> event;
    
    private IngredientResource sut;
    
    @Before
    public void setup() {
        sut = new IngredientResource();
        sut.kettle = new Kettle("TestKettle") {

            @Override
            public void fireTemperatureReachedEvent(Temperature goal) {}

            @Override
            public void fireTemperatureChangingEvent(Temperature goal) {}

            @Override
            public void fireIngredientAddedEvent(Ingredient ingredient) {}

            @Override
            public void fireTemperatureReadingEvent(Temperature temperature) {}

            @Override
            public void fireWaitEvent(Duration duration) {}

            @Override
            public void fireWaitCompletedEvent() {}
            
        };
    }
    
    @Test 
    public void add_ingredient_to_kettle() {
        sut.add(someIngredientWithVolume(300, LITER));
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(300, LITER)));
    }

    @Test
    public void delete_should_empty_kettle() {
        sut.add(someIngredientWithVolume(300, LITER));
        sut.delete();
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(0, LITER)));
    }
    
    private IngredientJson someIngredientWithVolume(int value, Volume.VolumeUnit unit) {
        IngredientJson.VolumeJson volume = new IngredientJson.VolumeJson();
        volume.value = value;
        volume.unit = unit.name();
        IngredientJson ingredient = new IngredientJson();
        ingredient.ingredient = IngredientType.WATER;
        ingredient.volume = volume;
        return ingredient;
    }
}
