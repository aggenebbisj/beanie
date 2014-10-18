
package nl.ordina.brewery.manual.boundary;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.MonitoringEvent;
import nl.ordina.brewery.entity.capacity.Volume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.enterprise.event.Event;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class IngredientResourceTest {

    @Mock
    private Event<MonitoringEvent> event;
    
    private IngredientResource sut;
    
    @Before
    public void setup() {
        sut = new IngredientResource();
        sut.kettle = new Kettle();
        sut.kettle.setEvent(event);
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
    
    @Test
    public void delete_should_empty_kettle() {
        sut.delete();
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(0, Volume.VolumeUnit.LITER)));
    }
}
