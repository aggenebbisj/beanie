
package nl.ordina.beer.brewing.entity;

import static nl.ordina.beer.entity.EntityBuilder.defaultIngredient;
import nl.ordina.beer.entity.Kettle;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EmptyKettleTest {

    private EmptyKettle sut = new EmptyKettle();
    
    @Test
    public void should_change_temperature_of_kettle() {
        Kettle kettle = new Kettle();
        kettle.addIngredient(defaultIngredient());
        assertFalse(kettle.isEmpty());
        sut.executeFor(kettle);
        assertTrue(kettle.isEmpty());
    }
}
