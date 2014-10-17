
package nl.ordina.brewery.manual.boundary;

import java.time.Duration;
import javax.enterprise.event.Event;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.MonitoredEvent;
import nl.ordina.brewery.entity.YeOldeKettle;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.temperature.Temperature;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        IngredientJson.VolumeJson volume = new IngredientJson.VolumeJson();
        volume.value = 300;
        volume.unit = Volume.VolumeUnit.LITER.name();
        
        IngredientJson ingredient = new IngredientJson();
        ingredient.ingredient = IngredientType.WATER;
        ingredient.volume = volume;
        
        sut.add(ingredient);
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(300, Volume.VolumeUnit.LITER)));
    }
    
    @Ignore
    @Test
    public void delete_should_empty_kettle() {
        sut.delete();
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(0, Volume.VolumeUnit.LITER)));
    }
}
