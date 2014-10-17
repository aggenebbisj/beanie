
package nl.ordina.brewery.manual.boundary;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.capacity.Volume;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IngredientResourceTest {

    @Test
    public void delete_should_empty_kettle() {
        IngredientResource sut = new IngredientResource();
        sut.kettle = new Kettle();
        
        sut.delete();
        
        assertThat(sut.kettle.getIngredients().getVolume(), is(new Volume(0, Volume.VolumeUnit.LITER)));
    }
}
